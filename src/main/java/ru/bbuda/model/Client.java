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
@Table(name = "client")
public class Client extends PersonDetails{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "src", cascade = CascadeType.ALL)
    private List<Parcel> sentPackages;

    @OneToMany(mappedBy = "dest", cascade = CascadeType.ALL)
    private List<Parcel> expectedPackages;
}
