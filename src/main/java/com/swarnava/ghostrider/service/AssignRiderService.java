package com.swarnava.ghostrider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swarnava.ghostrider.entity.Booking;
import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.enume.RideStatus;
import com.swarnava.ghostrider.enume.RiderAvailability;
import com.swarnava.ghostrider.exception.NoPendingRequestFoundException;
import com.swarnava.ghostrider.exception.RiderNotAvailableException;
import com.swarnava.ghostrider.model.Coordinates;
import com.swarnava.ghostrider.model.Location;
import com.swarnava.ghostrider.repository.BookingRepository;
import com.swarnava.ghostrider.repository.RiderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AssignRiderService {
    @Autowired
    RiderRepository riderRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RiderService riderService;

    @Autowired
    ObjectMapper objectMapper;

    @Async
    @Transactional
    public CompletableFuture<Void> startSearchingForRider(String userId) throws JsonProcessingException {
        Optional<Booking> optionalRideRequest = bookingRepository.findByUserIdAndRideStatus(userId, RideStatus.PENDING);
        if (optionalRideRequest.isPresent()) {
            Booking booking = optionalRideRequest.get();

            Rider rider = findNearestAvailableRider(booking);
            if (rider == null) {
                booking.setRideStatus(RideStatus.RIDER_NOT_AVAILABLE);
                bookingRepository.save(booking);
                log.error("rider not available at this moment for the user {}", userId);
                throw new RiderNotAvailableException("rider not available at this moment", "rider not found");
            }

            booking.setRideStatus(RideStatus.ACCEPTED);
            booking.setAssignedRiderId(rider.getId());
            bookingRepository.save(booking);
            rider.setRiderAvailability(RiderAvailability.BUSY);
            riderRepository.save(rider);
        } else {
            log.error("no pending request found for the user {}", userId);
            throw new NoPendingRequestFoundException("no pending request found for the user",
                    String.format("userId:%s", userId));
        }
        return CompletableFuture.completedFuture(null);
    }

    public Rider findNearestAvailableRider(Booking booking) throws JsonProcessingException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Location pickupLocation = objectMapper.treeToValue(booking.getPickupLocation(), Location.class);
        List<Rider> riders = null;
        try {
            riders = riderService.findByPostalCode(pickupLocation.getPostalCode());
        } catch (Exception e) {
            log.error("not found available rider at your area");
        }

        if (riders != null && riders.size() > 0) {
            double minDist = Double.MAX_VALUE;
            Rider selectedRider = null;
            for (Rider rider : riders) {
                Location riderLocation = objectMapper.treeToValue(booking.getPickupLocation(), Location.class);
                Coordinates riderCoordinates = riderLocation.getCoordinates();
                double dist = Main.calcDist(riderCoordinates, pickupLocation.getCoordinates());
                if (minDist > dist) {
                    minDist = dist;
                    selectedRider = rider;
                }
            }
            log.info("found available nearest rider at same postal code {}", selectedRider);
            return selectedRider;
        } else {
            // find by city and assign
            try {
                riders = riderService.findByCity(pickupLocation.getCity());
                log.info("found available rider at same city");
            } catch (Exception e) {
                log.error("not found available rider at same city");
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
