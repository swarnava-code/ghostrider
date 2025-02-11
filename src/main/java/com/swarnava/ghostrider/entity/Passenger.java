package com.swarnava.ghostrider.entity;

import com.swarnava.ghostrider.enume.PassengerStatus;
import jakarta.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private PassengerStatus status;

}
