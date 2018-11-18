package mmt.app.main;

import mmt.TicketOffice;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import mmt.exceptions.NoSuchServiceIdException;
import mmt.exceptions.NoSuchStationNameException;


/**
 * §3.1.1. Reset the ticket office.
 */
public class DoReset extends Command<TicketOffice> {

  /**
   * @param receiver
   */
  public DoReset(TicketOffice receiver) {//throws NoSuchServiceException {
    super(Label.RESET, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try{
      _receiver.reset();
    }catch(NoSuchServiceIdException e){e.printStackTrace();}
    catch(NoSuchStationNameException e){}
  }

}
