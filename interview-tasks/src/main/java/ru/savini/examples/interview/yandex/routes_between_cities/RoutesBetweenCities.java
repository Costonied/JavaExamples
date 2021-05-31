package ru.savini.examples.interview.yandex.routes_between_cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Решение работает, но не укладывается в отведенные 64 Мб,
 * нужно тюнить
 */
public class RoutesBetweenCities {
    private static int citiesCount;
    private static int maxDistance;
    private static int departureCityIndex;
    private static int arrivalCityIndex;
    private static City departureCity;
    private static City arrivalCity;
    private static List<City> cities;
    private static int currentCount;
    private static int minPath;

    public static void main(String[] args) throws IOException, InterruptedException {
        minPath = Integer.MAX_VALUE;
        currentCount = -1;
        readSourceDataFromFile();
        findCitiesSatellitesForAllCities();
        exploreCity(departureCity);
        if (minPath == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            Thread.sleep(300000);
            System.out.println(minPath);
        }
    }

    private static void readSourceDataFromFile() throws IOException {
        try (FileReader fileReader = new FileReader("input.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            citiesCount = Integer.parseInt(bufferedReader.readLine());
            cities = new ArrayList<>();
            for (int i = 0; i < citiesCount; i++) {
                cities.add(new City(i, bufferedReader.readLine()));
            }
            maxDistance = Integer.parseInt(bufferedReader.readLine());
            String routesBetween = bufferedReader.readLine();
            departureCityIndex = Integer.parseInt(routesBetween.split(" ")[0]) - 1;
            arrivalCityIndex = Integer.parseInt(routesBetween.split(" ")[1]) - 1;
            departureCity = cities.get(departureCityIndex);
            arrivalCity = cities.get(arrivalCityIndex);
            arrivalCity.isArrivalCity = true;
        }
    }
    
    private static void findCitiesSatellitesForAllCities() {
        for (City mainCity : cities) {
            for (City candidateSatelliteCity : cities) {
                if (isCitySatellite(mainCity, candidateSatelliteCity)) {
                    mainCity.addSatellite(candidateSatelliteCity);
                }
            }
        }
    }
    
    private static boolean isCitySatellite(City mainCity, City candidateSatellite) {
        int distanceBetweenCities = computeDistanceBetweenCities(mainCity, candidateSatellite);
        return distanceBetweenCities > 0 && distanceBetweenCities <= maxDistance;
    }

    private static int computeDistanceBetweenCities(City cityA, City cityB) {
        return Math.abs(cityA.x - cityB.x) + Math.abs(cityA.y - cityB.y);
    }

    private static boolean exploreCity(City city) {
        if ((currentCount + 1) >= minPath) {
            return false;
        }

        city.isExploring = true;
        ++currentCount;

        if (isArrivalCityInSatellites(city)) {
            if (++currentCount < minPath) {
                minPath = currentCount;
            }
            --currentCount;
            city.isExploring = false;
            return true;
        }

        for (City satellite : city.satellites) {
            if (!satellite.isExploring) {
                if (exploreCity(satellite)) {
                    city.isExploring = false;
                    --currentCount;
                    break;
                }
            }
        }

        city.isExploring = false;
        --currentCount;
        return false;
    }

    private static boolean isArrivalCityInSatellites(City city) {
        for (City satellite : city.satellites) {
            if (satellite.isArrivalCity) {
                return true;
            }
        }
        return false;
    }

    static class City {
        private int index;
        private int x;
        private int y;
        List<City> satellites;
        private boolean isExploring = false;
        private boolean isArrivalCity = false;

        public City(int index, String coordinates) {
            satellites = new ArrayList<>();
            this.x = Integer.parseInt(coordinates.split(" ")[0]);
            this.y = Integer.parseInt(coordinates.split(" ")[1]);
        }

        public void addSatellite(City satelliteCity) {
            satellites.add(satelliteCity);
        }
    }
}
