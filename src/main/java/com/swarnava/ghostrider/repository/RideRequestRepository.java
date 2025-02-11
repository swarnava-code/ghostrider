package com.swarnava.ghostrider.repository;

import com.swarnava.ghostrider.entity.RideRequest;
import com.swarnava.ghostrider.enume.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RideRequestRepository extends JpaRepository<RideRequest, String> {

    Optional<RideRequest> findByUserIdAndRideStatus(String userId, RideStatus rideStatus);

    List<RideRequest> findByRideStatus(RideStatus rideStatus);

    @Query("SELECT r FROM RideRequest r " +
            "WHERE r.userId=:userId " +
            "AND (r.rideStatus='PENDING' OR r.rideStatus='ACCEPTED') " +
            "ORDER BY r.createdOn DESC LIMIT 1")
    Optional<RideRequest> findRecentBooking(@Param("userId") String userId);
}
