package org.booking.entities;

import java.util.Date;

public class Ticket {
      private String ticketId;
	  private String userId;
//	  private String journeyTime;
	  private String source;
	  private String destination;
	  private Train train;
	  private Date dateOfTravel;

	public Ticket(String ticketId, String userId, String source, String destination, Train train, Date dateOfTravel) {
		this.ticketId = ticketId;
		this.userId = userId;
		this.source = source;
		this.destination = destination;
		this.train = train;
		this.dateOfTravel = dateOfTravel;
	}

	public String getTicketId() {
		return ticketId;
	}

	public String getUserId() {
		return userId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Date getDateOfTravel() {
		return dateOfTravel;
	}

	public void setDateOfTravel(Date dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
}
