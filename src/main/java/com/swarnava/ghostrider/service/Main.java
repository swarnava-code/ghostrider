package com.swarnava.ghostrider.service;

import com.swarnava.ghostrider.enume.VehicleType;
import com.swarnava.ghostrider.model.Coordinates;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        calcDist(12.940409, 77.624401, 12.910738, 77.608726);
        calcDist(12.919357, 77.607251, 12.933106, 77.609984);
        calcDist(12.917557, 77.586409, 12.917341, 77.598505); // 1.5 km

        calcDist(12.916411, 77.623462, 12.850065, 77.671395); // 13-15km

        calculateDistance(12.916411, 77.623462, 12.850065, 77.671395);
        calculateDistance(12.917557, 77.586409, 12.917341, 77.598505); // 1.5 km
    }

    /**
     * Google API
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @param vehicleType
     * @return map of distance and time for 2-3 possible roots Map<Distance(KM), Time(Minutes)>
     */
    static Map<Float, Float> findDistanceAndTimeByRoad(double lat1, double lon1, double lat2, double lon2,
                                                       VehicleType vehicleType) {
        // TODO -- Call Google Map API
        return Map.of(0.260f, 4.4f); // 260 m - 4 min
    }

    /**
     * @return
     * @source dist. formula
     */
    static void calcDist(double c1x, double c1y, double c2x, double c2y) {
//        Coordinates c1 = new Coordinates();
//        Coordinates c2 = new Coordinates();
//        c1.x = c1x;
//        c1.y = c1y;
//        c2.x = c2x;
//        c2.y = c2y;
        double cx = c1x - c2x;
        double cy = c1y - c2y;
        double dist = Math.sqrt((cx * cx) + (cy * cy));
        System.out.println(150 * dist);
    }

    /**
     * @param c1
     * @param c2
     * @return
     * @source dist. formula
     */
    static double calcDist(Coordinates c1, Coordinates c2) {
        c1.setLatitude(c1.getLatitude());
        c1.setLongitude(c2.getLatitude());
        c2.setLatitude(c2.getLatitude());
        c2.setLongitude(c2.getLatitude());
        double cx = Math.abs(c1.getLatitude() - c2.getLatitude());
        double cy = Math.abs(c1.getLongitude() - c2.getLongitude());
        double dist = Math.sqrt((cx * cx) + (cy * cy));
        System.out.println(150 * dist);
        return 150 * dist;
    }

    /**
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     * @source chatgpt
     */
    static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double EARTH_RADIUS_KM = 6371; // Radius of Earth in KM
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        System.out.println(EARTH_RADIUS_KM * c);
        return (EARTH_RADIUS_KM * c);
    }
}



