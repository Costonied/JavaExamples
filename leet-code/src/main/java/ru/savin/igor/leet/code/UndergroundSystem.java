package ru.savin.igor.leet.code;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {

  private short tripCounter = 0;
  private Map<Integer, Trip> activeTrips;
  private Map<String, Map<String, AvgTime>> tripsBetweenStations;

  public static void main(String[] args) {
    UndergroundSystem undergroundSystem = new UndergroundSystem();

    undergroundSystem.checkIn(45, "Leyton", 3);
    undergroundSystem.checkOut(45, "Waterloo", 15);
    System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
  }

  public UndergroundSystem() {
    activeTrips = new HashMap<>();
    tripsBetweenStations = new HashMap<>();
  }

  public void checkIn(int id, String stationName, int t) {
    Trip trip = new Trip(id, stationName, t);
    activeTrips.put(id, trip);
  }

  public void checkOut(int id, String stationName, int t) {

    Trip currentTrip = activeTrips.get(id);

    currentTrip.setEndStationName(stationName);
    currentTrip.setEndTime(t);

    Map<String, AvgTime> stationTrips = tripsBetweenStations.get(currentTrip.startStationName);

    if (stationTrips == null) {
      stationTrips = new HashMap<>();
      AvgTime avgTime = new AvgTime((short) 1, currentTrip.endTime - currentTrip.startTime);
      stationTrips.put(currentTrip.endStationName, avgTime);
      tripsBetweenStations.put(currentTrip.startStationName, stationTrips);
    } else {
      AvgTime avgTime = stationTrips.get(currentTrip.endStationName);
      if (avgTime == null) {
        avgTime = new AvgTime((short) 1, currentTrip.endTime - currentTrip.startTime);
        stationTrips.put(currentTrip.endStationName, avgTime);
      } else {
        avgTime.increase(currentTrip.endTime - currentTrip.startTime);
      }
    }

    activeTrips.remove(id);
  }

  public double getAverageTime(String startStation, String endStation) {
    AvgTime avgTime = tripsBetweenStations.get(startStation).get(endStation);
    return avgTime.getAvgTime();
  }

  private class Trip {

    short id;
    int customerId;
    private String startStationName;
    private String endStationName;
    private int startTime;
    private int endTime;

    public Trip(int customerId, String startStationName, int startTime) {
      this.id = tripCounter++;
      this.customerId = customerId;
      this.startStationName = startStationName;
      this.startTime = startTime;
    }

    public void setEndStationName(String endStationName) {
      this.endStationName = endStationName;
    }

    public void setEndTime(int endTime) {
      this.endTime = endTime;
    }
  }

  private class AvgTime {

    short count;
    int totalAmount;

    public AvgTime(short count, int totalAmount) {
      this.count = count;
      this.totalAmount = totalAmount;
    }

    public double getAvgTime() {
      return ((double) totalAmount) / count;
    }

    public void increase(int time) {
      count++;
      totalAmount += time;
    }
  }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */