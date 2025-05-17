package samhap.roundcargo_v2.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samhap.roundcargo_v2.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
