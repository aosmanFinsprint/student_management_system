package dev.ayub.student_management_system.config.Securty;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import dev.ayub.student_management_system.model.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final User userDetails;

    public UserDetails(User user) {
        super();
        this.userDetails = user;
    }

    public User getUser() {
        return this.userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userDetails == null) {
            return new ArrayList<>();
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // If no roles/permissions found, add default ROLE_USER
        if (authorities.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
