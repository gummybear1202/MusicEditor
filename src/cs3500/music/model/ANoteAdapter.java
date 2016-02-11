package cs3500.music.model;

import cs3500.music.modelTheirs.ANote;

/**
 * Makes one of our Note from their ANote.
 */
public class ANoteAdapter {
  private final ANote adaptee;

  public ANoteAdapter(ANote adaptee) {
    this.adaptee = adaptee;
  }

  public Note generate() {
    int duration;
    int octave;
    int startMeasure;
    Note.Pitches pitch;
    boolean isHead;
    int instrument;
    int volume;
    duration = this.adaptee.getEnd() - this.adaptee.getStart();
    octave = this.adaptee.getOctave();
    startMeasure = this.adaptee.getStart();
    pitch = this.convertToPitch();
    isHead = true;
    instrument = this.adaptee.getInstrument();
    volume = this.adaptee.getVolume();
    return new Note(duration, octave, startMeasure, pitch, isHead, instrument,
            volume);
  }

  public Note.Pitches convertToPitch() {
    switch (this.adaptee.getPitch().order) {
      case 0:
        return Note.Pitches.C;
      case 1:
        return Note.Pitches.CSharp;
      case 2:
        return Note.Pitches.D;
      case 3:
        return Note.Pitches.DSharp;
      case 4:
        return Note.Pitches.E;
      case 5:
        return Note.Pitches.F;
      case 6:
        return Note.Pitches.FSharp;
      case 7:
        return Note.Pitches.G;
      case 8:
        return Note.Pitches.GSharp;
      case 9:
        return Note.Pitches.A;
      case 10:
        return Note.Pitches.ASharp;
      case 11:
        return Note.Pitches.B;
      default:
        throw new IllegalArgumentException("Shouldn't get here");
    }
  }

}
