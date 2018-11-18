package mmt;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * class Service: Description of a service
 * A service has an identifier, several stations and their times
 */

public class Service implements Comparable<Service>, Serializable{

	private int id;
	private double price;
	private List<LocalTime> departureTime = new ArrayList<LocalTime>();
	private List<String> departureStation = new ArrayList<String>();
	private String[] fields;

	public Service(String[] fields){
		int i = 3 ;
		this.id = Integer.parseInt(fields[1]);
		this.price = Float.parseFloat(fields[2]);
		while(i < fields.length){
			this.departureTime.add(LocalTime.parse(fields[i]));
			this.departureStation.add(fields[++i]);
			i++;
		}
		this.fields = fields;
	}

	public int getId(){ return this.id; }
	public double getPrice(){ return this.price; }
	public List<LocalTime> getDepartureTime(){ return this.departureTime; }
	public LocalTime getDepartureTime(int i){ return this.departureTime.get(i); }
	public List<String> getDepartureStation(){ return this.departureStation; }
	public String getDepartureStation(int i){ return this.departureStation.get(i); }
	public String[] getFields(){return this.fields; }

	@Override
	public int compareTo(Service other){
		return this.getDepartureTime(0).compareTo(other.getDepartureTime(0));
	}

	@Override
	public String toString(){
		int i = 0;
		String res = "Serviço #" + this.id + " @ " + String.format("%.02f",this.price);
		for(String s : this.departureStation){
			res += "\n" + this.departureTime.get(i++) + " " + s;
		}
		return res;
	}
}