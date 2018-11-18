package mmt;

import mmt.exceptions.BadDateSpecificationException;
import mmt.exceptions.BadEntryException;
import mmt.exceptions.BadTimeSpecificationException;
import mmt.exceptions.InvalidPassengerNameException;
import mmt.exceptions.NoSuchDepartureException;
import mmt.exceptions.NoSuchPassengerIdException;
import mmt.exceptions.NoSuchServiceIdException;
import mmt.exceptions.NoSuchStationNameException;
import mmt.exceptions.NoSuchItineraryChoiceException;
import mmt.exceptions.NonUniquePassengerNameException;
import mmt.exceptions.ImportFileException;


import java.util.Collections;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.FileNotFoundException;

/**
 * A train company has schedules (services) for its trains and passengers that
 * acquire itineraries based on those schedules.
 */
public class TrainCompany implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  private Map<Integer, Passenger> passengers = new TreeMap<Integer, Passenger>();
  private int passengerCounterId = 0;
  private Map<Integer, Service> services = new TreeMap<Integer, Service>();
  private List<Service> servicesDepartingFromStation;


  /**  
   * importFile method: imports a file and reads it . Puts the info in an Array of Strings
   * @param receiver
   */

  public void importFile(String filename) throws ImportFileException {
    try{
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      
      while ((line = reader.readLine()) != null){
        String[] fields = line.split("\\|");
          registerFromFields(fields);
      }
      reader.close();
    }
    catch(FileNotFoundException e){throw new ImportFileException();}
    catch(NonUniquePassengerNameException e) {throw new ImportFileException();}
    catch(NoSuchServiceIdException e) {throw new ImportFileException();}      
    catch(IOException e){throw new ImportFileException();}
  }


  /**  
   * registerFromFields method: creates the instances according to the first field of fields
   * @param fields
   */

  public void registerFromFields(String[] fields) throws ImportFileException, NoSuchServiceIdException, NonUniquePassengerNameException{
    String nameOfClass = fields[0];
    if(nameOfClass.equals("SERVICE")){
      addService(fields);
    }
    else if(nameOfClass.equals("PASSENGER")){
      addPassenger(fields[1]);
    }
    else if(nameOfClass.equals("ITINERARY")){
      //IMPLEMENTAR
    }
  }


  /**  
   * searchPassenger method: searches a passenger throw this name
   * @param name
   */

  public void searchPassenger(String name) throws NonUniquePassengerNameException{
    for(Passenger p : this.passengers.values()){
        if(p.getName().equals(name)) throw new NonUniquePassengerNameException(name);
    }
  }


  /**  
   * addPassenger method: Creates an instance of Passenger, adds a passenger on a tree (passengers) and increments passengerCounterId whenever one is added
   * @param name
   */

  public void addPassenger(String name)throws NonUniquePassengerNameException{
    searchPassenger(name);
    Passenger passenger = new Passenger(passengerCounterId, name);
    this.passengers.put(passengerCounterId, passenger);
    passengerCounterId++;
  }


  /**  
   * getPassenger method: returns a passenger throw id if exists
   * @param id
   * @return Passenger
   */

  public Passenger getPassenger(int id) throws NoSuchPassengerIdException{
    if(passengers.get(id) == null) throw new NoSuchPassengerIdException(id);
    else return passengers.get(id);
  }


  /**  
   * getPassengers method: returns a Collection of passengers
   * @return Collection<Passenger>
   */

  public Collection<Passenger> getPassengers(){
    return Collections.unmodifiableCollection(passengers.values());
  }


  /**  
   * getService method: returns a service throw id if exists
   * @param id
   * @return Service
   */

  public Service getService(int id) throws NoSuchServiceIdException{
    if (services.get(id) != null){ return services.get(id); }
    else throw new NoSuchServiceIdException(id);
  }


  /**  
   * getServices method: returns a Collection of services
   * @return Collection<Service>
   */

  public Collection<Service> getServices(){
    return Collections.unmodifiableCollection(services.values());
  }


  /**  
   * getServicesDepartingFromStation method: returns a Collection of services departing from a certain station
   * @param name
   * @return Collection<Service>
   */

  public Collection<Service> getServicesDepartingFromStation(String name) throws NoSuchStationNameException{
    int noStationNameFlag = 0;
    this.servicesDepartingFromStation = new ArrayList<Service>();
    for(Service s : this.services.values()){
      if(s.getDepartureStation(0).equals(name)){
        this.servicesDepartingFromStation.add(s);
      }
      for(String station : s.getDepartureStation()){
        if(station.equals(name)){
          noStationNameFlag = 1;
        }
      }
    }
    if(noStationNameFlag == 0){throw new NoSuchStationNameException(name);}
    else{
      Collections.sort(this.servicesDepartingFromStation);
      return this.servicesDepartingFromStation;
    }
  }


  /**  
   * searchService method: searches a service from its id
   * @param id
   */

  public void searchService(int id) throws NoSuchServiceIdException{
    for(Service s : this.services.values()){
        if(s.getId() == id) throw new NoSuchServiceIdException(id);
    }
  }


  /**  
   * addService method: Creates an instance of Service and adds it on a tree (services)
   * @param fields
   */

  public void addService(String[] fields) throws NoSuchServiceIdException {
    searchService(Integer.parseInt(fields[1]));
    Service service = new Service(fields);
    this.services.put(Integer.parseInt(fields[1]), service);
  }
}
