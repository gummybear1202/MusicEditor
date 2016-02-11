package cs3500.music.modelTheirs;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by alexgomez on 11/1/15.
 */
public interface MusicEditorModel {
  /**
   * Representing a Pitch
   */
  enum Pitch {
    C("C", 0), CSHARP("C#", 1), D("D", 2), DSHARP("D#", 3),
    E("E", 4), F("F", 5),
    FSHARP("F#", 6), G("G", 7),
    GSHARP("G#", 8), A("A", 9), ASHARP("A#", 10), B("B", 11);
    public String value;
    public int order;

    Pitch(String value, int order) {
      this.value = value;
      this.order = order;
    }
  }

  /**
   * Adds a unique note to the piece
   *
   * @param newNote the newNote you would like to add exists
   * @return true if it adds, return false if it doesn't
   */
  boolean addNote(ANote newNote);


  /**
   * @param editedNote  the note you want to edit
   * @param newPitch    the new pitch for the note
   * @param newOctave   the new octave for the note
   * @param newStart    the new start for the note
   * @param newDuration the new duration for the note
   */
  void edit(ANote editedNote, Pitch newPitch, int newOctave, int newStart, int newDuration,
            int newInstrument, int newVolume);

  /**
   * removes the note from the piece
   *
   * @param n the note you will remove
   * @throws IllegalArgumentException this note cannot be found
   */
  void remove(ANote n);

  /**
   * Find the notes playing at this time
   *
   * @param time at what time are we looking for the notes
   * @return a collection of notes playing
   */
  Collection<ANote> notesPlaying(int time);


  /**
   * @return the last beat where a note is played
   */
  int getEnd();

  /**
   * Gets the tempo
   *
   * @return the tempo
   */
  int getTempo();

  /**
   * Get the low note
   *
   * @param note          high or low note?
   * @param desiredOctave in which octave?
   * @return the low note
   */
  int getLowOrHighNote(String note, int desiredOctave);

  /**
   * @param maxOrMin is this a max or min
   * @return the max or min octave
   */
  int getMinOrMaxOctave(String maxOrMin);

  /**
   * Gets the lines
   *
   * @return the lines
   */
  AbstractMap<Integer, ArrayList<ANote>> getLines();

  /**
   * prints the Notes in this octave
   */
  void printNotes(int octave, int minNote, int maxNote, int time, Appendable output) throws
          IOException;

  /**
   * increments the current beat it is on
   */
  void increment();

  /**
   * Gives the current beat you are on
   *
   * @return the beat number
   */
  int getBeat();

}
