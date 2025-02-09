package com.swarnava.ghostrider.repository;

import com.swarnava.ghostrider.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, String> {
    List<Rider> findByCity(String city);
    List<Rider> findByPostalCode(String postalCode);
}
