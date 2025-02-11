package com.swarnava.ghostrider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swarnava.ghostrider.dto.RideRequestDTO;
import com.swarnava.ghostrider.entity.RideRequest;
import com.swarnava.ghostrider.enume.RideStatus;
import com.swarnava.ghostrider.exception.AlreadyActiveRidingException;
import com.swarnava.ghostrider.repository.RideRequestRepository;
import com.swarnava.ghostrider.repository.RiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class RiderService {
    @Autowired
    RiderRepository riderRepository;

    @Autowired
    RideRequestRepository rideRequestRepository;

    @Autowired
    AssignRiderService assignRiderService;

    @Autowired
    ObjectMapper objectMapper;

    public RideRequest requestRide(RideRequestDTO rideRequestDTO) throws IOException {

        Optional<RideRequest> optionalRideRequest = rideRequestRepository.findByUserIdAndRideStatus(
                rideRequestDTO.getUserId(), RideStatus.PENDING);
        if (optionalRideRequest.isPresent()) {
            RideRequest rideRequest = optionalRideRequest.get();
            throw new AlreadyActiveRidingException(
                    String.format("%s is already PENDING", rideRequest.getUserId()),
                    rideRequest.toString()
            );
        }
        log.debug("saving request... for {}",rideRequestDTO.getUserId());
                String pickupJsonString="", destinationJsonString="";
        try {
            pickupJsonString = objectMapper.writeValueAsString(rideRequestDTO.getPickupLocation());
            destinationJsonString = objectMapper.writeValueAsString(rideRequestDTO.getDestination());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        objectMapper.readTree(pickupJsonString);
        RideRequest rideRequest = new RideRequest(rideRequestDTO.getUserId(),
                objectMapper.readTree(pickupJsonString), objectMapper.readTree(destinationJsonString), RideStatus.PENDING);
        RideRequest request = rideRequestRepository.save(rideRequest);
        log.debug("startSearchingForRider... for {}",rideRequestDTO.getUserId());
        assignRiderService.startSearchingForRider(rideRequestDTO.getUserId()); // this will run in bg
        return request;
    }

    public Optional<RideRequest> findRecentBooking(String userId) {
        return rideRequestRepository.findRecentBooking(userId);
    }

}
