package com.farmweb.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;

    @Column(unique = true, nullable = false)
    private String customerEmail;

    @Column(unique = true, nullable = false)
    private String customerPhone;

    private String customerAddress;

    @JsonIgnore
    private String customerPassword;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<History> transactions;
}
