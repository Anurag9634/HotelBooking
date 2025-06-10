package com.example.hotelBooking.entities;


import com.example.hotelBooking.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    private String name;

    @ElementCollection(fetch = FetchType.LAZY) //want to store list or set of simple value but
    @Enumerated(EnumType.STRING)          // not want to create the other entity and set up m to m relation
    private Set<Role> roles;

}

