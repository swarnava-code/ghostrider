package com.swarnava.ghostrider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coordinates implements Serializable {
    private Double latitude;
    private Double longitude;
}
