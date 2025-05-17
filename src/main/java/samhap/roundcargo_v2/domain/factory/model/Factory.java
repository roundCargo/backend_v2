package samhap.roundcargo_v2.domain.factory.model;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;
import samhap.roundcargo_v2.domain.common.model.Account;
import samhap.roundcargo_v2.domain.common.model.BaseEntity;
import samhap.roundcargo_v2.domain.common.model.Enum.Role;
import samhap.roundcargo_v2.domain.common.model.Enum.Status;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String address;

    @Column(columnDefinition = "geography(Point,4326)")
    private Point location;

    private String bin;

    private String phone1;

    private String phone2;

    private String caution;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
