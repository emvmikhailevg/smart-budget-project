package ru.emelianov.smartbudgetproject.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.emelianov.smartbudgetproject.dto.UserDTO;
import ru.emelianov.smartbudgetproject.service.UserService;

@Controller
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return "login";
        }

        userService.register(userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());
        return "redirect:/authenticate";
    }

}
