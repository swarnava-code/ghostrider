package com.swarnava.ghostrider.service;

import com.swarnava.ghostrider.entity.RideRequest;
import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.enume.RideStatus;
import com.swarnava.ghostrider.exception.NoPendingRequestFoundException;
import com.swarnava.ghostrider.repository.RideRequestRepository;
import com.swarnava.ghostrider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AssignRiderService {
    @Autowired
    RiderRepository riderRepository;
    @Autowired
    RideRequestRepository rideRequestRepository;

    @Async
    public CompletableFuture<Void> startSearchingForRider(String userId) {
        Optional<RideRequest> optionalRideRequest = rideRequestRepository.findByUserIdAndRideStatus(userId, RideStatus.PENDING);
        if (optionalRideRequest.isPresent()) {
            RideRequest rideRequest = optionalRideRequest.get();

            Rider rider = findNearestAvailableRider(rideRequest);

            rideRequest.setAssignedRiderId(rider.getId());
            rideRequest.setRideStatus(RideStatus.ACCEPTED);
            rideRequestRepository.save(rideRequest);
        } else {
            throw new NoPendingRequestFoundException("no pending request found for the user",
                    String.format("userId:%s", userId));
        }
        return CompletableFuture.completedFuture(null);
    }

    public Rider findNearestAvailableRider(RideRequest rideRequest) {
//        Passenger passengerCurrLoc = rideRequest.getPickupLocation()
//        riderRepository.findByPostalCode(rideRequest.get)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Rider();
    }
}
