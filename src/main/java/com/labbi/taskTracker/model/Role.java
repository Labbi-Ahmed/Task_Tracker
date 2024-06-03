package com.labbi.taskTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Role name is required")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
