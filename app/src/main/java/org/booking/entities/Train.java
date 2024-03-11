package org.booking.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Train {

	private String trainId;

	//	private String name;
	private int trainNumber;

	//	private String duration;
	private List<List<Integer>> seats;

	//	private String origin;
	//	private String destination;
	private Map<String, Date> trainRoute;

	private List<String> stations;

	public Train(String trainId, int trainNumber, List<List<Integer>> seats, Map<String, Date> trainRoute,
			List<String> stations) {
		this.trainId = trainId;
		this.trainNumber = trainNumber;
		this.seats = seats;
		this.trainRoute = trainRoute;
		this.stations = stations;
	}

	public Train() {
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public List<List<Integer>> getSeats() {
		return this.seats;
	}

	public void setSeats(int row, int col,List<List<Integer>> seats) {
		seats.get(row).set(col, 1);
		this.seats = seats;
	}

	public Map<String, Date> getTrainRoute() {
		return this.trainRoute;
	}

	public void setTrainRoute(Map<String, Date> trainRoute) {
		this.trainRoute = trainRoute;
	}

	public List<String> getStations() {
		return stations;
	}

	public void setStations(List<String> stations) {
		this.stations = stations;
	}

	public String getTrainInfo() {
		return String.format("Train ID: %s & Train No: %s ", trainId, trainNumber);
	}

}
