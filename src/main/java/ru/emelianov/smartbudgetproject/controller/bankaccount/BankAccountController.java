package ru.emelianov.smartbudgetproject.controller.bankaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.emelianov.smartbudgetproject.database.model.User;
import ru.emelianov.smartbudgetproject.database.repository.BankAccountRepository;
import ru.emelianov.smartbudgetproject.database.repository.UserRepository;
import ru.emelianov.smartbudgetproject.dto.BankAccountDTO;
import ru.emelianov.smartbudgetproject.service.BankAccountService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bank-account")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    @GetMapping
    public String getAllAccounts(Model model, Principal user) {
        User currentUser = userRepository.findByUsername(user.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        List<BankAccountDTO> accounts = bankAccountService.findAllByUserId(currentUser.getId());
        model.addAttribute("accounts", accounts);
        model.addAttribute("userId", currentUser.getId());
        return "bank-account-management";
    }

    @PostMapping("/add")
    public String addAccount(@RequestParam String nameAdd,
                             @RequestParam BigDecimal amountAdd,
                             @RequestParam String colorAdd,
                             Principal user) {
        User currentUser = userRepository.findByUsername(user.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        bankAccountService.add(currentUser, nameAdd, amountAdd, colorAdd);
        return "redirect:/bank-account";
    }

    @PostMapping("/transfer")
    public String transferAmount(@RequestParam Long fromAccountIdTransfer,
                                 @RequestParam Long toAccountIdTransfer,
                                 @RequestParam BigDecimal amountTransfer) {
        bankAccountService.transferAmountBetweenAccounts(fromAccountIdTransfer, toAccountIdTransfer, amountTransfer);
        return "redirect:/bank-account";
    }

    @PostMapping("/edit")
    public String editAccount(@RequestParam Long accountIdEdit,
                              @RequestParam String nameEdit,
                              @RequestParam BigDecimal amountEdit,
                              @RequestParam String colorEdit) {
        bankAccountService.editBankAccount(accountIdEdit, nameEdit, colorEdit);
        return "redirect:/bank-account";
    }

    @PostMapping("/delete")
    public String deleteAccount(@RequestParam Long accountIdDelete) {
        bankAccountService.delete(accountIdDelete);
        return "redirect:/bank-account";
    }

}
