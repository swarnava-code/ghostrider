package com.swarnava.ghostrider.service;

class Main {
    public static void main(String[] args) {
        calcDist(12.940409, 77.624401, 12.910738, 77.608726);
        calcDist(12.919357, 77.607251   ,   12.933106, 77.609984);
        calcDist(12.917557, 77.586409 ,  12.917341, 77.598505); // 1.5 km
        
        calcDist( 12.916411, 77.623462  ,  12.850065, 77.671395 ); // 13-15km
        
        calculateDistance( 12.916411, 77.623462  ,  12.850065, 77.671395 ); 
        calculateDistance(12.917557, 77.586409 ,  12.917341, 77.598505); // 1.5 km
    }
    static void calcDist(double c1x, double c1y, double c2x, double c2y){
        Coordinates c1 = new Coordinates();
        Coordinates c2 = new Coordinates();
        c1.x = c1x;
        c1.y = c1y;
        c2.x = c2x;
        c2.y = c2y;
        double cx = c1.x-c2.x;
        double cy = c1.y-c2.y;
        double dist = Math.sqrt((cx*cx)+(cy*cy));
        System.out.println(150*dist);
    }
    
    static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
          final double EARTH_RADIUS_KM = 6371; // Radius of Earth in KM
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        System.out.println(EARTH_RADIUS_KM*c);
        return (EARTH_RADIUS_KM * c);
    }
}

class Coordinates {
    double x;
    double y;
}

