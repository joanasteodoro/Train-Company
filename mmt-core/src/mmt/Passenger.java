package mmt;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * class Passenger: Description of a passenger
 * A passenger has an identifier, a name and a category
 */

public class Passenger implements Serializable{
	private int id;
	private String name;
	private PassengerState category = new NormalPassenger(this);
	private int numItineraries;
	private double paidValue;
	private LocalTime accumulatedTime = LocalTime.parse("00:00");

	public Passenger(String[] fields){
		this.id = Integer.parseInt(fields[1]);
		this.name = fields[2];
	}
	public Passenger(int id, String name){
		this.id = id;
		this.name = name;
	}

	public int getId(){return this.id; }
	public String getName(){ return this.name; }
	public PassengerState getCategory(){ return this.category; }
	public int getNumItineraries(){ return this.numItineraries; }
	public double paidValue(){ return this.paidValue; }
	public LocalTime accumulatedTime(){ return this.accumulatedTime; }

	protected void setState(PassengerState category){ this.category = category;}

	public void setName(String name){ this.name = name; }

	@Override
	public String toString(){
		return this.id + "|" + this.name + "|" + this.category + "|" + this.numItineraries + "|" + String.format("%.02f", this.paidValue) + "|" + this.accumulatedTime;
	}
}