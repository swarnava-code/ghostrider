package com.swarnava.ghostrider.dto;

import com.swarnava.ghostrider.enume.RiderAvailability;
import com.swarnava.ghostrider.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDTO implements Serializable {
    private String name;
    private Location currentLocation;
    private RiderAvailability riderAvailability;
}
