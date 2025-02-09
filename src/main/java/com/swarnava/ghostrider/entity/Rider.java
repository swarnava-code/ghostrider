package com.swarnava.ghostrider.entity;

import com.swarnava.ghostrider.enume.RiderAvailability;
import com.swarnava.ghostrider.model.Coordinates;
import jakarta.persistence.*;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String city;
    private int postalCode;
    private Coordinates coordinates;

    @Enumerated(EnumType.STRING)
    private RiderAvailability riderAvailability;
}
