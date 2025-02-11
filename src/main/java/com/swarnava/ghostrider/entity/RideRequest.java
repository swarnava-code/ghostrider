package com.swarnava.ghostrider.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.JsonNode;
import com.swarnava.ghostrider.enume.RideStatus;
import com.swarnava.ghostrider.model.Location;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown JSON properties
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)  // Define JSONB type
public class RideRequest {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;

    private String userId;

    @JdbcTypeCode(SqlTypes.JSON)

//    @Type(type = "jsonb")

    @Column(columnDefinition = "jsonb")
    private JsonNode pickupLocation;//Location

//    @JdbcTypeCode((SqlTypes.JSON))

//    @Type(type = "jsonb")

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode destination;//Location

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private String assignedRiderId;


//    private String pickupCity;
//    private Integer pickupPostalCode;
//    private Coordinates pickupCoordinates;
//    private String pickupAddress;
//
//    private String destinationCity;
//    private Integer destinationPostalCode;
//    private Coordinates destinationCoordinates;
//    private String destinationAddress;

}
