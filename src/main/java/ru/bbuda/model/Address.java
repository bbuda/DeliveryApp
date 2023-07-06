package ru.bbuda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "x", nullable = false)
    private Double lat;

    @Column(name = "y", nullable = false)
    private Double lon;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Client> clients;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private DeliveryCenter deliveryCenter;
}
