package samhap.roundcargo_v2.domain.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samhap.roundcargo_v2.domain.common.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
