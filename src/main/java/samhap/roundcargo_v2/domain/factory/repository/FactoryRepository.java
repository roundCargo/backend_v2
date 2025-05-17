package samhap.roundcargo_v2.domain.factory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samhap.roundcargo_v2.domain.factory.model.Factory;

public interface FactoryRepository extends JpaRepository<Factory, Long> {
}
