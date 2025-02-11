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
public class Location implements Serializable {
    private String city;
    private Integer postalCode;
    private Coordinates coordinates;
    private String address;
}
