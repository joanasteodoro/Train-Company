package mmt.app.service;

import mmt.TicketOffice;
import mmt.Service;
import mmt.exceptions.NoSuchServiceIdException;
import mmt.app.exceptions.NoSuchServiceException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;


/**
 * 3.2.2 Show service by number.
 */
public class DoShowServiceByNumber extends Command<TicketOffice> {

  private Input<Integer> id;

  /**
   * @param receiver
   */
 public DoShowServiceByNumber(TicketOffice receiver) {
    super(Label.SHOW_SERVICE_BY_NUMBER, receiver);
    this.id = _form.addIntegerInput(Message.requestServiceId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      Service s = _receiver.getService(this.id.value());
      _display.addLine(s.toString());
      _display.display();
    }catch (NoSuchServiceIdException e){ throw new NoSuchServiceException(this.id.value());}
  }

}
