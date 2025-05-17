package samhap.roundcargo_v2.domain.user.model;

import jakarta.persistence.*;
import lombok.*;
import samhap.roundcargo_v2.domain.common.model.Account;
import samhap.roundcargo_v2.domain.common.model.BaseEntity;
import samhap.roundcargo_v2.domain.common.model.Enum.Role;
import samhap.roundcargo_v2.domain.common.model.Enum.Status;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    private String id; // 외부 로그인 기반 (카카오 ID)

    private String name;

    private String phoneNumber;

    private String businessNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}