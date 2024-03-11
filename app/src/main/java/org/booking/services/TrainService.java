package org.booking.services;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.booking.entities.Train;

public class TrainService {

	private List<Train> trains;

	private ObjectMapper objectMapper = new ObjectMapper();

	private static final String TRAIN_DB_PATH = "app/src/main/java/org/booking/localdb/trains.json";

	public List<Train> searchTrain(String source, String destination) {
		return trains.stream().filter(train -> {
			return train.getTrainRoute().containsKey(source) && train.getTrainRoute().containsKey(destination);
		}).toList();
	}

	public boolean cancelBooking(String trainId, String ticketId) {
		Optional<Train> train = trains.stream().filter(t -> t.getTrainId().equals(trainId)).findFirst();
		if(train.isPresent()) {
			train.get().getSeats().stream().forEach(seat -> {
				if(seat.contains(ticketId)) {
					seat.set(seat.indexOf(ticketId), 0);
				}
			});
			return true;
		}
		return false;
	}
}
