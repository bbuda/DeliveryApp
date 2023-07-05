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

    @Column(name = "weight_gram")
    private Long weightGram;

    @ManyToOne
    @JoinColumn(name = "src_client_id", referencedColumnName = "id")
    private Client src;

    @ManyToOne
    @JoinColumn(name = "dest_client_id", referencedColumnName = "id")
    private Client dest;

    @Column(name = "express")
    private Boolean isExpress;


}
