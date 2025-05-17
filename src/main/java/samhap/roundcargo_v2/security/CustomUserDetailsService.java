package samhap.roundcargo_v2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import samhap.roundcargo_v2.domain.factory.repository.FactoryRepository;
import samhap.roundcargo_v2.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService {

    private final UserRepository userRepository;
    private final FactoryRepository factoryRepository;

    public CustomUserDetails loadUserByIdAndRole(Long id, String role) {
        if (role.equals("USER")) {
            return userRepository.findById(id)
                    .map(CustomUserDetails::fromUser)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } else if (role.equals("FACTORY")) {
            return factoryRepository.findById(id)
                    .map(CustomUserDetails::fromFactory)
                    .orElseThrow(() -> new RuntimeException("Factory not found"));
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
