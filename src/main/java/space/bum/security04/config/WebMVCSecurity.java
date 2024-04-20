package space.bum.security04.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebMVCSecurity {

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user1")
            .password("{noop}user1Pass")
            .authorities("ROLE_USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
      return (web -> web.ignoring().requestMatchers(
          new AntPathRequestMatcher("/resources/**")));
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(auth -> auth.anyRequest()
          .authenticated())
          .formLogin(withDefaults())
          .httpBasic(withDefaults());
        return http.build();
    }
}