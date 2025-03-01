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
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;

    private String vendorName;
    private String vendorAddress;

    @Column(unique = true, nullable = false)
    private String vendorEmail;

    @Column(unique = true, nullable = false)
    private String vendorPhone;

    @JsonIgnore
    private String vendorPassword;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<History> transactions;
}
