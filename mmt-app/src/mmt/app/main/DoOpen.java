package mmt.app.main;

import mmt.TicketOffice;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * §3.1.1. Open existing document.
 */
public class DoOpen extends Command<TicketOffice> {

  private Input<String> filename;
  /**
   * @param receiver
   */
  public DoOpen(TicketOffice receiver) {
    super(Label.OPEN, receiver);
    this.filename = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    try {
      _receiver.load(this.filename.value());
    } catch (FileNotFoundException fnfe) {
      _display.popup(Message.fileNotFound());
    } catch (ClassNotFoundException | IOException e) {
      //l shouldn't happen in a controlled test setup
      e.printStackTrace();
    }
  }

}
