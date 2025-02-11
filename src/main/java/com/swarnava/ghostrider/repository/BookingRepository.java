package com.swarnava.ghostrider.repository;

import com.swarnava.ghostrider.entity.Booking;
import com.swarnava.ghostrider.enume.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    Optional<Booking> findByUserIdAndRideStatus(String userId, RideStatus rideStatus);
    Optional<Booking> findByAssignedRiderIdAndRideStatus(String assignedRiderId, RideStatus rideStatus);
    List<Booking> findByRideStatus(RideStatus rideStatus);

    @Query("SELECT r FROM Booking r " +
            "WHERE r.userId=:userId " +
            "AND (r.rideStatus='PENDING' OR r.rideStatus='ACCEPTED') " +
            "ORDER BY r.createdOn DESC LIMIT 1")
    Optional<Booking> findRecentBooking(@Param("userId") String userId);

    @Query("SELECT r FROM Booking r " +
            "WHERE r.userId=:userId " +
            "ORDER BY r.createdOn DESC LIMIT 1")
    Optional<Booking> findLastBooking(@Param("userId") String userId);
}
