package mmt.app.service;

import mmt.TicketOffice;
import mmt.Service;
import mmt.exceptions.NoSuchStationNameException;
import mmt.app.exceptions.NoSuchStationException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import java.util.List;
import java.util.ArrayList;

/**
 * 3.2.3 Show services departing from station.
 */
public class DoShowServicesDepartingFromStation extends Command<TicketOffice> {
  private Input<String> stationName;

  /**
   * @param receiver
   */
  public DoShowServicesDepartingFromStation(TicketOffice receiver) {
    super(Label.SHOW_SERVICES_DEPARTING_FROM_STATION, receiver);
    this.stationName = _form.addStringInput(Message.requestStationName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      for(Service s : _receiver.getServicesDepartingFromStation(this.stationName.value())){
        _display.addLine(s.toString());
      }
      _display.display();
    }catch(NoSuchStationNameException e){throw new NoSuchStationException(this.stationName.value());}
  }

}
