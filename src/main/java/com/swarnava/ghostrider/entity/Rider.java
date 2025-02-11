package com.swarnava.ghostrider.entity;

import com.swarnava.ghostrider.enume.RiderAvailability;
import com.swarnava.ghostrider.model.Coordinates;
import com.swarnava.ghostrider.model.Location;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Location currentLocation;

    @Enumerated(EnumType.STRING)
    private RiderAvailability riderAvailability;

    private String city;
    private Integer postalCode;
    private Coordinates coordinates;
    private String address;
}
