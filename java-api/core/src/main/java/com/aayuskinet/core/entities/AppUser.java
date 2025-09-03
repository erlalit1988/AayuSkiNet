package com.aayuskinet.core.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@lombok.EqualsAndHashCode(callSuper = true)
public class AppUser extends BaseEntity {

    private String username; // Corresponds to UserName in IdentityUser
    private String firstName;
    private String lastName;
    private String email;
    private String password; // This will be hashed

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
