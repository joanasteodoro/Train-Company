package mmt.app.passenger;

import mmt.TicketOffice;
import mmt.Passenger;
import mmt.app.exceptions.BadPassengerNameException;
import mmt.app.exceptions.DuplicatePassengerNameException;
import mmt.app.exceptions.NoSuchPassengerException;
import mmt.exceptions.InvalidPassengerNameException;
import mmt.exceptions.NoSuchPassengerIdException;
import mmt.exceptions.NonUniquePassengerNameException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * §3.3.4. Change passenger name.
 */
public class DoChangerPassengerName extends Command<TicketOffice> {
  private Input<Integer> id;
  private Input<String> name;

  /**
   * @param receiver
   */
  public DoChangerPassengerName(TicketOffice receiver) {
    super(Label.CHANGE_PASSENGER_NAME, receiver);
    this.id = _form.addIntegerInput(Message.requestPassengerId());
    this.name = _form.addStringInput(Message.requestPassengerName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    Passenger p;
    try{
      p = _receiver.getPassenger(this.id.value());
    }catch(NoSuchPassengerIdException e ){throw new NoSuchPassengerException(this.id.value());}
    p.setName(this.name.value());
  }
}
