package com.swarnava.ghostrider.repository;

import com.swarnava.ghostrider.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, String> {

    @Query(value = "SELECT * FROM rider r " +
            "WHERE r.current_location->>'city'=:city", nativeQuery = true)
    List<Rider> findByCityAndAvailability(@Param("city") String city);

    @Query(value = "SELECT * FROM rider r " +
            "WHERE r.current_location->>'postalCode'=:postalCode", nativeQuery = true)
    List<Rider> findByPostalCodeAndAvailability(@Param("postalCode") String postalCode);
}
