package org.booking.entities;

import java.util.List;

public class User {

	private String userId;

	private String username;

	private String hashPassword;

	private String password;

	private List<Ticket> ticketsBooked;

	public User(String username, String password, String hashPassword, List<Ticket> ticketsBooked, String userId) {
		this.userId = userId;
		this.username = username;
		this.hashPassword = hashPassword;
		this.password = password;
		this.ticketsBooked = ticketsBooked;
	}

	public Object getName() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTicketsBooked(List<Ticket> ticketsBooked) {
		this.ticketsBooked = ticketsBooked;
	}

	public List<Ticket> getTicketsBooked() {
		return ticketsBooked;
	}
}
