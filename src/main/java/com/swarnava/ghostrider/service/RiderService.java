package com.swarnava.ghostrider.service;

import com.swarnava.ghostrider.entity.RideRequest;
import com.swarnava.ghostrider.enume.RideStatus;
import com.swarnava.ghostrider.exception.AlreadyActiveRidingException;
import com.swarnava.ghostrider.repository.RideRequestRepository;
import com.swarnava.ghostrider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiderService {
    @Autowired
    RiderRepository riderRepository;
    @Autowired
    RideRequestRepository rideRequestRepository;

    public RideRequest requestRide(String userId, String pickupLocation, String destination) {
        Optional<RideRequest> optionalRideRequest = rideRequestRepository.findByUserIdAndRideStatus(userId, RideStatus.PENDING);
        if (optionalRideRequest.isPresent()) {
            RideRequest rideRequest = optionalRideRequest.get();
            throw new AlreadyActiveRidingException(
                    String.format("%s is already PENDING", userId),
                    rideRequest.toString()
            );
        }
        RideRequest rideRequest = new RideRequest(null, userId, pickupLocation, destination, RideStatus.PENDING,
                null);
        return rideRequestRepository.save(rideRequest);
    }

}
