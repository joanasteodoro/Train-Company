package mmt;
import java.io.Serializable;

/**
 * class FrequentPassenger: Description of a possible passenger state
 */

public class FrequentPassenger extends PassengerState{
	public FrequentPassenger(Passenger p){ super(p);}

	@Override
	public String toString(){
		return "FREQUENTE";
	}
}