package dev.ayub.student_management_system.model.dto.Response.auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import studio.casper.oh_angels.config.exceptions.AuthenticationFailedException;
import studio.casper.oh_angels.config.security.JWTService;
import studio.casper.oh_angels.config.security.UserDetails;
import studio.casper.oh_angels.model.dto.request.auth.LoginRequestDTO;
import studio.casper.oh_angels.model.dto.response.auth.LoginResponseDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final Set<String> tokenBlacklist = new HashSet<>();

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return buildLoginResponse(userDetails);
        } catch (BadCredentialsException e) {
            throw new AuthenticationFailedException("Invalid email or password");
        } catch (DisabledException e) {
            throw new AuthenticationFailedException("Account is disabled", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            log.error("Unexpected error during authentication: {}", e.getMessage());
            throw new AuthenticationFailedException("Authentication failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public LoginResponseDTO refreshToken(String refreshToken) {
        try {
            if (tokenBlacklist.contains(refreshToken)) {
                throw new AuthenticationFailedException("Token has been invalidated", HttpStatus.UNAUTHORIZED);
            }

            String email;
            try {
                email = jwtService.extractUsername(refreshToken);
            } catch (Exception e) {
                throw new AuthenticationFailedException("Invalid refresh token", HttpStatus.UNAUTHORIZED);
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                throw new AuthenticationFailedException("No authentication found", HttpStatus.UNAUTHORIZED);
            }

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            try {
                if (!jwtService.isTokenValid(refreshToken, userDetails)) {
                    throw new AuthenticationFailedException("Invalid or expired refresh token", HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception e) {
                throw new AuthenticationFailedException("Token validation failed", HttpStatus.UNAUTHORIZED);
            }

            return buildLoginResponse(userDetails);
        } catch (AuthenticationFailedException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during token refresh: {}", e.getMessage());
            throw new AuthenticationFailedException("Failed to refresh token", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void logout(String token) {
        if (token != null && !token.isEmpty()) {
            tokenBlacklist.add(token);
            SecurityContextHolder.clearContext();
        }
    }

    private LoginResponseDTO buildLoginResponse(UserDetails userDetails) {
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        List<String> roles = authorities.stream()
                .filter(auth -> auth.startsWith("ROLE_"))
                .map(auth -> auth.substring(5))
                .collect(Collectors.toList());

        List<String> permissions = authorities.stream()
                .filter(auth -> !auth.startsWith("ROLE_"))
                .collect(Collectors.toList());

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setUserId(userDetails.getUser().getId());
        responseDTO.setFirstName(userDetails.getUser().getFirstName());
        responseDTO.setLastName(userDetails.getUser().getLastName());
        responseDTO.setEmail(userDetails.getUser().getEmail());
        responseDTO.setToken(accessToken);
        responseDTO.setRefreshToken(refreshToken);
        responseDTO.setRoles(roles);
        responseDTO.setPermissions(permissions);
        responseDTO.setExpiresIn(jwtService.getTokenExpiry() / 1000); // Convert ms to seconds

        return responseDTO;
    }

}