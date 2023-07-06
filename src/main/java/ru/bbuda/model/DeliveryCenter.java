package ru.bbuda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "delivery_center")
public class DeliveryCenter {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "address_name", referencedColumnName = "name", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "deliveryCenter", cascade = CascadeType.ALL)
    private List<Courier> couriers;

    @OneToMany(mappedBy = "srcDeliveryCenter", cascade = CascadeType.ALL)
    private List<Parcel> sentPackages;

    @OneToMany(mappedBy = "destDeliveryCenter", cascade = CascadeType.ALL)
    private List<Parcel> expectedPackages;
}
