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

    @ManyToOne
    @JoinColumn(name = "src_client_id", referencedColumnName = "id", nullable = false)
    private Client srcClient;

    @ManyToOne
    @JoinColumn(name = "dest_client_id", referencedColumnName = "id", nullable = false)
    private Client destClient;

    @Column(name = "express", nullable = false)
    private Boolean isExpress;


    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id", nullable = false)
    private Courier courier;
}
