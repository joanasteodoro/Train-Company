package mmt.app.passenger;

import mmt.TicketOffice;
import mmt.Passenger;
import mmt.exceptions.NoSuchPassengerIdException;
import mmt.app.exceptions.NoSuchPassengerException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * §3.3.2. Show specific passenger.
 */
public class DoShowPassengerById extends Command<TicketOffice> {
  //private Passenger p;
  private Input<Integer> id;

  /**
   * @param receiver
   */
  public DoShowPassengerById(TicketOffice receiver) {
    super(Label.SHOW_PASSENGER_BY_ID, receiver);
    this.id = _form.addIntegerInput(Message.requestPassengerId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      Passenger p = _receiver.getPassenger(this.id.value());
      _display.addLine(p.toString());
      _display.display();
      } catch(NoSuchPassengerIdException e ){throw new NoSuchPassengerException(this.id.value());
    }
  }

}
