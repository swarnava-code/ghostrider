package com.swarnava.ghostrider.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.swarnava.ghostrider.enume.Gender;
import com.swarnava.ghostrider.enume.RiderAvailability;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Rider {

    // not allowed to change

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreationTimestamp
    private LocalDateTime memberSince;


    private String phoneNumber;


    // can change later

    private String name;
    private String email;
    private Gender gender;
    private LocalDateTime dob;
    private String emergencyContact;
    private String permanentAddress;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode currentLocation;

    @Enumerated(EnumType.STRING)
    private RiderAvailability riderAvailability;

}
