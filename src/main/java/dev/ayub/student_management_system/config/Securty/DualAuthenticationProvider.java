package dev.ayub.student_management_system.config.Securty;

import dev.ayub.student_management_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import dev.ayub.student_management_system.model.entity.User;

import java.util.Optional;

@Component
@Slf4j
public class DualAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailService userDetailService;

    // Use constructor without @RequiredArgsConstructor to break circular dependency
    public DualAuthenticationProvider(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserDetailService userDetailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        log.debug("Attempting authentication for email: {}", email);

        // Check if the user exists in the user repository
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            log.debug("Found user with email: {}", email);
            User user = userOptional.get();

            // Validate password
            if (passwordEncoder.matches(password, user.getPassword())) {
                log.debug("User password matched for email: {}", email);
                UserDetails userDetails = userDetailService.loadUserByUsername(email);
                return new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
            }
        }

        // If authentication failed
        log.debug("Authentication failed for email: {}", email);
        throw new BadCredentialsException("Invalid email or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}