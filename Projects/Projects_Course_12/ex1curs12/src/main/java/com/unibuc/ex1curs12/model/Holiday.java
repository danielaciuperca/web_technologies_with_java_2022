package com.unibuc.ex1curs12.model;

import javax.persistence.*;

@Entity
@Table(name="holiday")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String accommodation;

	private int duration;

	private String transportation;

	private double price;

	@ManyToOne
	@JoinColumn(name="destination_id")
	private Destination destination;

	public Holiday() {
	}

	public Holiday(String accommodation, int duration, String transportation, double price, Destination destination) {
		this.accommodation = accommodation;
		this.duration = duration;
		this.transportation = transportation;
		this.price = price;
		this.destination = destination;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "id=" + id +
				", accommodation=" + accommodation +
				", duration=" + duration +
				", transportation=" + transportation +
				", price=" + price;
	}
}
