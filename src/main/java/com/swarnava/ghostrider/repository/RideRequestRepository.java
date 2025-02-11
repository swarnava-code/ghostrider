package com.swarnava.ghostrider.repository;

import com.swarnava.ghostrider.entity.RideRequest;
import com.swarnava.ghostrider.enume.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RideRequestRepository extends JpaRepository<RideRequest, String> {
    Optional<RideRequest> findByUserIdAndRideStatus(String userId, RideStatus rideStatus);
    List<RideRequest> findByRideStatus(RideStatus rideStatus);
}
