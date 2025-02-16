package com.swarnava.ghostrider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swarnava.ghostrider.config.JsonMapper;
import com.swarnava.ghostrider.dto.CreateRiderDTO;
import com.swarnava.ghostrider.dto.ModifiableRiderDTO;
import com.swarnava.ghostrider.dto.RiderDTO;
import com.swarnava.ghostrider.dto.RiderRequestMapper;
import com.swarnava.ghostrider.entity.Booking;
import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.enume.RideStatus;
import com.swarnava.ghostrider.enume.RiderAvailability;
import com.swarnava.ghostrider.exception.InvalidUserIdException;
import com.swarnava.ghostrider.repository.BookingRepository;
import com.swarnava.ghostrider.repository.RiderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RiderService {
    @Autowired
    RiderRepository riderRepository;

    @Autowired
    JsonMapper jsonMapper;

    @Autowired
    BookingRepository bookingRepository;

    public Optional<Rider> findRider(String riderId){
        return riderRepository.findById(riderId);
    }

    /**
     * call after signup
     *
     * @return
     */
    public Optional<Rider> saveNewRider(CreateRiderDTO createRiderDTO) throws JsonProcessingException {
        Rider rider = RiderRequestMapper.INSTANCE.toEntity(createRiderDTO);
        return Optional.of(riderRepository.save(rider));
    }

    public Optional<Rider> updateAvailability(String riderId, RiderDTO riderDTO) {
        Optional<Rider> optionalRider = riderRepository.findById(riderId);
        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            if(riderDTO.getRiderAvailability()==RiderAvailability.AVAILABLE)
                rider.setRiderAvailability(RiderAvailability.AVAILABLE);
            else
                rider.setRiderAvailability(RiderAvailability.BREAK);
            return Optional.of(riderRepository.save(rider));
        }
        return optionalRider;
    }

    public Optional<Rider> update(String riderId, ModifiableRiderDTO modifiableRiderDTO) {
        Optional<Rider> optionalRider = riderRepository.findById(riderId);
        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            rider = modifiableRiderDTO.getEntity(rider, modifiableRiderDTO);
            riderRepository.save(rider);
            return Optional.of(rider);
        }
        return optionalRider;
    }

    public Optional<Rider> updateLocation(String riderId, RiderDTO dto) throws JsonProcessingException {
        Optional<Rider> optionalRider = riderRepository.findById(riderId);
        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            rider.setCurrentLocation(jsonMapper.getJsonNode(dto.getCurrentLocation()));
            return Optional.of(riderRepository.save(rider));
        } else {
            log.error("invalid rider id: {}", riderId);
            throw new InvalidUserIdException("invalid rider id", riderId);
        }
    }

    public List<Rider> findByPostalCode(Integer postalCode) {
        return riderRepository.findByPostalCodeAndAvailability(""+postalCode);
    }

    public List<Rider> findByCity(String city) {
        return riderRepository.findByCityAndAvailability(city);
    }

    @Transactional
    public Optional<Booking> completeJourney(String riderId) {
        Optional<Rider> optionalRider = riderRepository.findById(riderId);
        Booking booking = null;
        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            if(rider.getRiderAvailability()==RiderAvailability.BUSY) {
                rider.setRiderAvailability(RiderAvailability.AVAILABLE);
                Optional<Booking> optionalBooking = bookingRepository.findByAssignedRiderIdAndRideStatus(riderId, RideStatus.ACCEPTED);
                if(optionalBooking.isPresent()){
                    booking = optionalBooking.get();
                    booking.setRideStatus(RideStatus.COMPLETED);
                } else {
                    log.error("booking not found for rider: {}", riderId);
                    throw new RuntimeException("booking not found");
                }
                riderRepository.save(rider);
            }
            else {
                log.error("rider is already free: {}", riderId);
                throw new RuntimeException("rider is already free");
            }
        }
        return Optional.ofNullable(booking);
    }

}
