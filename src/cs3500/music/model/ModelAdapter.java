package cs3500.music.model;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs3500.music.modelTheirs.ANote;
import cs3500.music.modelTheirs.MusicEditorModel;

/**
 * Adapts our model to the model that the view requests
 */
public class ModelAdapter implements MusicEditorModel {
  private final Model adaptee;

  public ModelAdapter(Model adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Adapts addNote from Model.
   *
   * @param theirNote the newNote you would like to add exists
   * @return true if successfully added, false if addNote throws exception.
   */
  @Override
  public boolean addNote(ANote theirNote) {
    try {
      ANoteAdapter adapt = new ANoteAdapter(theirNote);
      adaptee.addNote(adapt.generate());

    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  @Override
  public void edit(ANote editedNote, Pitch newPitch, int newOctave,
                   int newStart, int newDuration, int newInstrument, int newVolume) {

    ANoteAdapter adapt = new ANoteAdapter(editedNote);
    Note n = adapt.generate();
    Note actual = adaptee.find(n.getStartMeasure(),n.getDuration(),n
            .getOctave(),n.toMidiIndex());
    actual.moveTo(actual.getPitch(), actual.getOctave(), newStart);
    switch (newPitch.order) {
      case 0:
        actual.setPitch(Note.Pitches.C);
        break;
      case 1:
        actual.setPitch(Note.Pitches.CSharp);
        break;
      case 2:
        actual.setPitch(Note.Pitches.D);
        break;
      case 3:
        actual.setPitch(Note.Pitches.DSharp);
        break;
      case 4:
        actual.setPitch(Note.Pitches.E);
        break;
      case 5:
        actual.setPitch(Note.Pitches.F);
        break;
      case 6:
        actual.setPitch(Note.Pitches.FSharp);
        break;
      case 7:
        actual.setPitch(Note.Pitches.G);
        break;
      case 8:
        actual.setPitch(Note.Pitches.GSharp);
        break;
      case 9:
        actual.setPitch(Note.Pitches.A);
        break;
      case 10:
        actual.setPitch(Note.Pitches.ASharp);
        break;
      case 11:
        actual.setPitch(Note.Pitches.B);
        break;
      default:
        actual.setPitch(Note.Pitches.E);
        break;
    }
    actual.setOctave(newOctave);
    actual.setDuration(newDuration);
    actual.setInstrument(newInstrument);
    actual.setVolume(newVolume);
    editedNote = new NoteAdapter(actual);
  }

  @Override
  public void remove(ANote theirNote) {
    ANoteAdapter adapt = new ANoteAdapter(theirNote);
    adaptee.removeNote(adapt.generate());
  }

  @Override
  public Collection<ANote> notesPlaying(int time) {
    Set<Note> atMeasure = adaptee.getNotes().get(time);
    Collection<ANote> result = new HashSet<>();
    for (Note n : atMeasure) {
      NoteAdapter anote = new NoteAdapter(n);
      result.add(anote);
    }
    return result;
  }

  @Override
  public int getEnd() {
    return adaptee.getNotes().size() - 1;
  }

  @Override
  public int getTempo() {
    return adaptee.getTempo();
  }

  @Override
  public int getLowOrHighNote(String note, int desiredOctave) {
    if (note.equals("low")) {
      return 0;
    }
    else if (note.equals("high")) {
      return 11;
    }
    else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  @Override
  public int getMinOrMaxOctave(String maxOrMin) {
    if (maxOrMin.equals("min")) {
      return this.adaptee.getLowest().getOctave();
    }
    else if (maxOrMin.equals("max")) {
      return this.adaptee.getHighest().getOctave();
    }
    else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  @Override
  public AbstractMap<Integer, ArrayList<ANote>> getLines() {
    return null;
  }

  @Override
  public void printNotes(int octave, int minNote, int maxNote,
                         int time, Appendable output) throws IOException {
    Set<Note> measure = adaptee.getNotes().get(time);
      for(Note n: measure) {
        if (n.getOctave() == octave) {
          output.append(n.toString());
        }
      }
  }

  @Override
  public void increment() {
    this.adaptee.updateMeasure();
  }

  @Override
  public int getBeat() {
    return this.adaptee.getCurrentMeasure();
  }
}
