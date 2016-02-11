package cs3500.music.view2;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import cs3500.music.modelTheirs.ANote;

/**
 * Created by alexgomez on 11/9/15.
 */
public interface View {

  /**
   * Creates the view
   */
  void create() throws InvalidMidiDataException, IOException, InterruptedException;

  /**
   * Adds  an ANote
   * @param anote adds this note
   */
  void addNote(ANote anote);

}
