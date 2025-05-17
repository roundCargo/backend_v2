package samhap.roundcargo_v2.domain.cargo.model;

import jakarta.persistence.*;
import lombok.*;
import samhap.roundcargo_v2.domain.common.model.BaseEntity;
import samhap.roundcargo_v2.domain.common.model.Enum.CargoStatus;
import samhap.roundcargo_v2.domain.factory.model.Factory;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cargo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer length;

    private Integer width;

    private Integer height;

    private Integer weight;

    private String price;

    private String destAddr;

    private String destPhone;

    private LocalDateTime loadTime;

    private LocalDateTime unloadTime;

    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @ManyToOne
    @JoinColumn(name = "src_id")
    private Factory srcFactory;
}
