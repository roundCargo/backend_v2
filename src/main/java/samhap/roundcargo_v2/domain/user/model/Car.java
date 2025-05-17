package samhap.roundcargo_v2.domain.user.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carNumber;

    private Integer crubWeight;

    private Integer capacityWeight;

    @ManyToOne
    @JoinColumn(name = "cb_id")
    private CargoBay cargoBay;
}
