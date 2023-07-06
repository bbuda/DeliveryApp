package ru.bbuda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "courier")
public class Courier extends Person {

    @ManyToOne
    @JoinColumn(name = "delivery_center_name", referencedColumnName = "name", nullable = false)
    private DeliveryCenter deliveryCenter;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Parcel> parcels;
}
