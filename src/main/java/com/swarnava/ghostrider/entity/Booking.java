package com.swarnava.ghostrider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.swarnava.ghostrider.enume.RideStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;

    @NonNull
    private String userId;

    @JdbcTypeCode(SqlTypes.JSON)
    @NonNull
    @Column(columnDefinition = "jsonb")
    private JsonNode pickupLocation;//Location

    @JdbcTypeCode(SqlTypes.JSON)
    @NonNull
    @Column(columnDefinition = "jsonb")
    private JsonNode destination;//Location

    @NonNull
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private String assignedRiderId;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date createdOn;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date updatedOn;

}
