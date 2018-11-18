package mmt.app.main;

import java.io.IOException;
import mmt.TicketOffice;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

/**
 * §3.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<TicketOffice> {
  
  private Input<String> filename;

  /**
   * @param receiver
   */
  public DoSave(TicketOffice receiver) {
    super(Label.SAVE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try{
      if(_receiver.getFileName() != null){
        _receiver.save(_receiver.getFileName());
      }
      else{
        this.filename = _form.addStringInput(Message.newSaveAs()); 
        _form.parse();
        _receiver.save(this.filename.value());
      }
    }catch(IOException e){e.printStackTrace();}
  }
}
