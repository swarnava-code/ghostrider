package com.swarnava.ghostrider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import com.swarnava.ghostrider.dto.CustomDateDeserializer;
import com.swarnava.ghostrider.enume.Gender;
import com.swarnava.ghostrider.enume.RiderAvailability;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime memberSince;

    @NotEmpty
    private String phoneNumber;


    // can change later

    @NotEmpty
    private String name;

    @Email
    private String email;

    @NotNull
    private Gender gender;

    @NotNull
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dob;

    @NotEmpty
    private String emergencyContact;

    @NotEmpty
    private String permanentAddress;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode currentLocation;

    @Enumerated(EnumType.STRING)
    private RiderAvailability riderAvailability;

}
