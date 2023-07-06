package ru.bbuda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "courier")
public class Courier extends Person {

    /*TODO посылки*/
    /*TODO центр доставки 1 к 1 */
}
