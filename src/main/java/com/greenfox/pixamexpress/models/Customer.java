package com.greenfox.pixamexpress.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, length = 25)
    private String name;
    @Column(nullable = false, length = 50)
    private String address;
    private String pizza;
}
