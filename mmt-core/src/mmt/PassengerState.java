package mmt;
import java.io.Serializable;

public abstract class PassengerState implements Serializable{
	protected Passenger passenger;

	public PassengerState(Passenger passenger){ this.passenger = passenger; }

	public String status(){ return "NORMAL";}
}