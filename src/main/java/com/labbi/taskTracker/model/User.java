package com.labbi.taskTracker.model;


import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;


@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name Not empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name Not empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "email must enter")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Phone number required")
    private String phone;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "Postcode is required")
    private String postcode;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "length at least 8")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private Instant createDate;
    private Instant updateDate;

    private boolean active;
    private String profileImage;


}
