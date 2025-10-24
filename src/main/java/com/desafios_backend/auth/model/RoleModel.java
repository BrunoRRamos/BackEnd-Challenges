package com.desafios_backend.auth.model;

import com.desafios_backend.auth.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "user_roles")
@Entity(name = "Roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoles roleName;
}
