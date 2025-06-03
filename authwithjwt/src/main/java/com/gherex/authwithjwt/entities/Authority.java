package com.gherex.authwithjwt.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    private String username; // necesario para el join

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
