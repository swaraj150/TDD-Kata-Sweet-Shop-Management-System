package com.sweetshop.server.config;

import com.sweetshop.server.entity.User;
import com.sweetshop.server.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                User user=userRepository.findByEmail(username).orElseThrow(()->new EntityNotFoundException("User not found"));
                if (!user.isEnabled()) {
                    log.warn("User account is disabled: {}", username);
                    throw new DisabledException("User account is disabled");
                }
                if (!user.isCredentialsNonExpired()) {
                    log.warn("User credentials are expired: {}", username);
                    throw new CredentialsExpiredException("User credentials are expired");
                }

                if (!user.isAccountNonLocked()) {
                    log.warn("User account is locked: {}", username);
                    throw new LockedException("User account is locked");
                }


                if (!user.isAccountNonExpired()) {
                    log.warn("User account is expired: {}", username);
                    throw new AccountExpiredException("User account is expired");

                }
                log.info("User is valid : {}", username);

                return user;
            }
        };


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
