package mmt;
import java.io.Serializable;

/**
 * class NormalPassenger: Description of a possible passenger state
 */

public class NormalPassenger extends PassengerState{
	public NormalPassenger(Passenger p){ super(p);}
	@Override
	public String toString(){
		return "NORMAL";
	}
}