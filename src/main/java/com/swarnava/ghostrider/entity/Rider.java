package com.swarnava.ghostrider.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.swarnava.ghostrider.enume.RiderAvailability;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String name;


    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode currentLocation;


    @Enumerated(EnumType.STRING)
    private RiderAvailability riderAvailability;

}
