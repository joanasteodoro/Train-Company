package mmt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Collections;
import java.util.Collection;
import mmt.exceptions.BadDateSpecificationException;
import mmt.exceptions.BadTimeSpecificationException;
import mmt.exceptions.ImportFileException;
import mmt.exceptions.InvalidPassengerNameException;
import mmt.exceptions.MissingFileAssociationException;
import mmt.exceptions.NoSuchPassengerIdException;
import mmt.exceptions.NoSuchServiceIdException;
import mmt.exceptions.NoSuchStationNameException;
import mmt.exceptions.NoSuchItineraryChoiceException;
import mmt.exceptions.NonUniquePassengerNameException;


/**
 * Façade for handling persistence and other functions.
 */
public class TicketOffice {

  /** The object doing most of the actual work. */
  private TrainCompany _trains;
  private String filename = null;

  public TicketOffice(){_trains = new TrainCompany();}

  public String getFileName(){ return this.filename; }

  public void reset() throws NoSuchServiceIdException, NoSuchStationNameException {
    this.filename = null;
    TrainCompany _newTrains = new TrainCompany();
    for(Service s : _trains.getServices()){
      _newTrains.addService(s.getFields());
    }
    _trains = _newTrains;
  }

  public void save(String filename) throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
    out.writeObject(_trains);
    out.close();
    this.filename = filename;
  }

  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    _trains = (TrainCompany) ois.readObject();
    ois.close();
    this.filename = filename;
  }

  public void importFile(String datafile) throws ImportFileException{
    _trains.importFile(datafile);
  }

  public void addPassenger(String name) throws NonUniquePassengerNameException{ _trains.addPassenger(name);}

  public Passenger getPassenger(int id) throws NoSuchPassengerIdException {return _trains.getPassenger(id);}
  public Collection<Passenger> getPassengers(){return _trains.getPassengers();}

  public void addService(String[] fields)throws NoSuchServiceIdException {_trains.addService(fields);}
  
  public Service getService(int id) throws NoSuchServiceIdException{return _trains.getService(id); }
  public Collection<Service> getServices(){return _trains.getServices();}
   public Collection<Service> getServicesDepartingFromStation(String name) throws NoSuchStationNameException{return _trains.getServicesDepartingFromStation(name);}

  //FIXME complete and implement the itinerary search (and pre-commit store) method
  //public /*FIXME choose return type */ search(int passengerId, String departureStation, String arrivalStation, String departureDate,
                                              //String departureTime) /*FIXME define thrown exceptions */ {
    //FIXME implement method
  //}

  //FIXME complete and implement the itinerary commit method
  //public /*FIXME choose return type */ commitItinerary(int passengerId, int itineraryNumber) /*FIXME define thrown exceptions */ {
    //FIXME implement method
  //}

  //FIXME add methods for passenger registration and passenger name update

  //FIXME add other functions if necessary

}
