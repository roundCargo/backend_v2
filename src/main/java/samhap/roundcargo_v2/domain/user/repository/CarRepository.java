package samhap.roundcargo_v2.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samhap.roundcargo_v2.domain.user.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
