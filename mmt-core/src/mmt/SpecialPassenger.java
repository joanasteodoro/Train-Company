package mmt;
import java.io.Serializable;

/**
 * class SpecialPassenger: Description of a possible passenger state
 */

public class SpecialPassenger extends PassengerState{
	public SpecialPassenger(Passenger p){ super(p);}
	@Override
	public String toString(){
		return "ESPECIAL";
	}
}