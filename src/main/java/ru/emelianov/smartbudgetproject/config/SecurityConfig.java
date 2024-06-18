package ru.emelianov.smartbudgetproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.emelianov.smartbudgetproject.database.repository.UserRepository;

/**
 * <p>Конфигурация безопасности для приложения</p>
 *
 * <p>Этот класс конфигурирует компоненты безопасности Spring, такие как службы для загрузки пользователей,
 * поставщики аутентификации, менеджеры аутентификации и цепочку фильтров безопасности</p>
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    /**
     * Определяет {@link UserDetailsService}, который загружает пользователя по его имени
     *
     * @return {@link UserDetailsService} для загрузки пользователей
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username ->
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("Пользователь " + username + " не найден"));
    }

    /**
     * Определяет {@link AuthenticationProvider}, который использует {@link DaoAuthenticationProvider}
     *
     * @return {@link AuthenticationProvider} для аутентификации пользователей
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Определяет {@link AuthenticationManager}, который управляет аутентификацией
     *
     * @param config конфигурация аутентификации
     * @return {@link AuthenticationManager}
     * @throws Exception если возникает ошибка конфигурации
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Определяет {@link PasswordEncoder}, который использует {@link BCryptPasswordEncoder}
     *
     * @return {@link PasswordEncoder} для шифрования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Определяет цепочку фильтров безопасности {@link SecurityFilterChain}
     *
     * @param http объект {@link HttpSecurity}
     * @return {@link SecurityFilterChain}
     * @throws Exception если возникает ошибка конфигурации
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/", "/authenticate/**").permitAll()
                                .requestMatchers("/main/**", "/bank-account/**").authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/authenticate")
                                .loginProcessingUrl("/authenticate")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/main", true)
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );

        return http.build();
    }

}
