package com.desafios_backend.auth.model;


import com.desafios_backend.auth.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonProperty("userEmail")
    @Column(name = "userEmail", nullable = false, unique = true)
    private String email;

    @JsonProperty("userPasword")
    @Column(name = "userPasword", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<RoleModel> roles;
}
