package com.swarnava.ghostrider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.swarnava.ghostrider.enume.Gender;
import com.swarnava.ghostrider.enume.RiderAvailability;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, length = 36, updatable = false)
    private String id;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(nullable = false, updatable = false)
    private LocalDateTime memberSince;

    @Column(unique = true, nullable = false, length = 13, updatable = false)
    private String phoneNumber;

    // can change later
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private LocalDate dob;

    @Column(length = 13, nullable = false)
    private String emergencyContact;

    @Column(columnDefinition = "TEXT")
    private String permanentAddress;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode currentLocation;

    @Enumerated(EnumType.STRING)
    private RiderAvailability riderAvailability;

}
