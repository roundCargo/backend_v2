package samhap.roundcargo_v2.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import samhap.roundcargo_v2.domain.factory.model.Factory;
import samhap.roundcargo_v2.domain.user.model.User;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String role;

    public CustomUserDetails(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public static CustomUserDetails fromUser(User user) {
        return new CustomUserDetails(user.getId(), "USER");
    }

    public static CustomUserDetails fromFactory(Factory factory) {
        return new CustomUserDetails(factory.getId(), "FACTORY");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + role);  // Spring Security 권한 형식
    }

    @Override public String getPassword() { return null; }
    @Override public String getUsername() { return id.toString(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

