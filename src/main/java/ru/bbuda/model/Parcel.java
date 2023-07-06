package ru.bbuda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parcel")
public class Parcel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "weight_gram", nullable = false)
    private Long weightGram;

    @Column(name = "express", nullable = false)
    private Boolean isExpress;

    @ManyToOne
    @JoinColumn(name = "src_client_id", referencedColumnName = "id", nullable = false)
    private Client srcClient;

    @ManyToOne
    @JoinColumn(name = "dest_client_id", referencedColumnName = "id", nullable = false)
    private Client destClient;

    @ManyToOne
    @JoinColumn(name = "src_delivery_center_name", referencedColumnName = "name", nullable = false)
    private DeliveryCenter srcDeliveryCenter;

    @ManyToOne
    @JoinColumn(name = "dest_delivery_center_name", referencedColumnName = "name", nullable = false)
    private DeliveryCenter destDeliveryCenter;

    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id", nullable = false)
    private Courier courier;
}
