package com.swarnava.ghostrider.entity;

import com.swarnava.ghostrider.enume.RideStatus;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@ToString
public class RideRequest {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;

    private String userId;
    private String pickupLocation;
    private String destination;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private String assignedRiderId;

    public RideRequest(String id, String userId, String pickupLocation, String destination, RideStatus rideStatus, String assignedRiderId) {
        this.id = id;
        this.userId = userId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.rideStatus = rideStatus;
        this.assignedRiderId = assignedRiderId;
    }
}
