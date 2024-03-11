package org.booking.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.booking.entities.Ticket;
import org.booking.entities.Train;
import org.booking.entities.User;
import org.booking.util.UserServiceUtil;

public class UserBookingService {

	private User user;

	private List<User> userList;

	private static final String USER_PATH = "app/src/main/java/org/booking/localdb/users.json";

	private final ObjectMapper objectMapper = new ObjectMapper();

	public UserBookingService(User user) throws IOException {
		this.user = user;
		loadUsers();
	}

	public UserBookingService() throws IOException {
		loadUsers();
	}

	public boolean bookTicket(Train train, int row, int column) {
		Optional<User> foundUser = userList.stream().filter(myUser -> {
			return myUser.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),
					myUser.getHashPassword());
		}).findFirst();
		TrainService trainService = new TrainService();
		List<List<Integer>> seats = train.getSeats();
		if (foundUser.isPresent()) {
			if (row > 0 && row <= seats.size() && column > 0 && column <= seats.get(row).size()
					&& seats.get(row).get(column) == 0) {
				seats.get(row).set(column, 1);
				train.setSeats(row, column, seats);
				saveUserListToFile();
				return true;
			} else {
				System.out.println("Seat already booked");
				return false;
			}
		} else {
			System.out.println("User not found");
			return false;
		}
	}

	public void loadUsers() throws IOException {
		File users = new File(USER_PATH);
		objectMapper.readValue(users, new TypeReference<List<User>>() {

		});
	}

	public void userLogin(User user) {
		Optional<User> foundUser = userList.stream().filter(myUser -> {
			return myUser.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),
					myUser.getHashPassword());
		}).findFirst();
	}

	public void userSignup(User user) {
		Optional<User> foundUser = userList.stream().filter(myUser -> {
			return myUser.getName().equals(user.getName());
		}).findFirst();
		if (foundUser.isPresent()) {
			System.out.println("User already exists");
		} else {
			user.setHashPassword(UserServiceUtil.hashedPassword(user.getPassword()));
			userList.add(user);
			saveUserListToFile();
		}
	}

	private void saveUserListToFile() {
		try {
			objectMapper.writeValue(new File(USER_PATH), userList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fetchBooking() {
		Optional<User> foundUser = userList.stream().filter(myUser -> {
			return myUser.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),
					myUser.getHashPassword());
		}).findFirst();
		if (foundUser.isPresent()) {
			System.out.println(foundUser.get().getTicketsBooked());
		} else {
			System.out.println("User not found");
		}
	}

	public boolean cancelBooking(String ticketId) {
		Optional<User> foundUser = userList.stream().filter(myUser -> {
			return myUser.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),
					myUser.getHashPassword());
		}).findFirst();
		if (foundUser.isPresent()) {
			List<Ticket> tickets = foundUser.get().getTicketsBooked();
			Optional<Ticket> ticket = tickets.stream().filter(myTicket -> {
				return myTicket.getTicketId().equals(ticketId);
			}).findFirst();

			if (ticket.isPresent()) {
				tickets.remove(ticket.get());
				saveUserListToFile();
				return true;
			} else {
				System.out.println("Ticket not found");
				return false;
			}

		} else {
			System.out.println("User not found");
			return false;
		}
	}

	public static List<Train> searchTrain(String source, String destination) {
		TrainService trainService = new TrainService();
		return trainService.searchTrain(source, destination);
	}
}
