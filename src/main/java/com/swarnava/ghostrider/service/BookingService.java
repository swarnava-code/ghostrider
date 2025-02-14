package com.swarnava.ghostrider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swarnava.ghostrider.dto.BookingDTO;
import com.swarnava.ghostrider.entity.Booking;
import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.enume.RideStatus;
import com.swarnava.ghostrider.enume.RiderAvailability;
import com.swarnava.ghostrider.exception.AlreadyActiveRidingException;
import com.swarnava.ghostrider.repository.BookingRepository;
import com.swarnava.ghostrider.repository.RiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class BookingService {
    @Autowired
    RiderRepository riderRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    AssignRiderService assignRiderService;

    @Autowired
    ObjectMapper objectMapper;

    public Booking requestRide(BookingDTO bookingDTO) throws IOException {
        Optional<Booking> optionalRideRequest = bookingRepository.findByUserIdAndRideStatus(
                bookingDTO.getUserId(), RideStatus.PENDING);
        if (optionalRideRequest.isPresent()) {
            Booking booking = optionalRideRequest.get();
            throw new AlreadyActiveRidingException(
                    String.format("%s is already PENDING", booking.getUserId()),
                    booking.toString()
            );
        }
        log.debug("saving request... for {}", bookingDTO.getUserId());
        String pickupJsonString = "", destinationJsonString = "";
        try {
            pickupJsonString = objectMapper.writeValueAsString(bookingDTO.getPickupLocation());
            destinationJsonString = objectMapper.writeValueAsString(bookingDTO.getDestination());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //objectMapper.readTree(pickupJsonString);
        Booking booking = new Booking(bookingDTO.getUserId(),
                objectMapper.readTree(pickupJsonString), objectMapper.readTree(destinationJsonString), RideStatus.PENDING);
        Booking request = bookingRepository.save(booking);
        log.debug("startSearchingForRider... for {}", bookingDTO.getUserId());
        assignRiderService.startSearchingForRider(bookingDTO.getUserId()); // this will run in bg
        return request;
    }

    public Optional<Booking> findRecentBooking(String userId) {
        return bookingRepository.findRecentBooking(userId);
    }

    public Optional<Booking> findLastBooking(String userId) {
        return bookingRepository.findLastBooking(userId);
    }

    public Optional<Booking> cancelBooking(BookingDTO bookingDTO) {
        Optional<Booking> optionalRideRequest = bookingRepository.findByUserIdAndRideStatus(
                bookingDTO.getUserId(), RideStatus.PENDING);
        if (optionalRideRequest.isPresent()) {
            Booking booking = optionalRideRequest.get();
            booking.setRideStatus(RideStatus.CANCELLED_BY_PASSENGER);
            if (booking.getAssignedRiderId() != null) {
                Optional<Rider> rider = riderRepository.findById(booking.getAssignedRiderId());
                rider.ifPresent(r -> {
                    r.setRiderAvailability(RiderAvailability.AVAILABLE);
                    riderRepository.save(r);
                });
            }
            bookingRepository.save(booking);
        }
        return Optional.of(optionalRideRequest.get());
    }

}
