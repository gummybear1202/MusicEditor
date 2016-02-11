package cs3500.music.model;

import cs3500.music.modelTheirs.ANote;
import cs3500.music.modelTheirs.MusicEditorModel;

/**
 * adapter for Note. Makes one of their ANote from our Note.
 */
public class NoteAdapter extends ANote {
  private final Note adaptee;

  public NoteAdapter(Note adaptee) {
    super(MusicEditorModel.Pitch.A, adaptee.getOctave(), adaptee
                    .getStartMeasure(),
            adaptee.getStartMeasure() + adaptee.getDuration(), adaptee
                    .getInstrument(), adaptee.getVolume());
    this.adaptee = adaptee;
    this.pitch = this.getPitch();
  }

//  protected void setNote(MusicEditorModel.Pitch newPitch, int newOctave,
//                         int newStart, int newEnd, int newInstrument, int newVolume) {
//    Note.Pitches note;
//    switch (newPitch.order) {
//      case 0:
//        note = Note.Pitches.C;
//        break;
//      case 1:
//        note = Note.Pitches.CSharp;
//        break;
//      case 2:
//        note = Note.Pitches.D;
//        break;
//      case 3:
//        note = Note.Pitches.DSharp;
//        break;
//      case 4:
//        note = Note.Pitches.E;
//        break;
//      case 5:
//        note = Note.Pitches.F;
//        break;
//      case 6:
//        note = Note.Pitches.FSharp;
//        break;
//      case 7:
//        note = Note.Pitches.G;
//        break;
//      case 8:
//        note = Note.Pitches.GSharp;
//        break;
//      case 9:
//        note = Note.Pitches.A;
//        break;
//      case 10:
//        note = Note.Pitches.ASharp;
//        break;
//      case 11:
//        note = Note.Pitches.B;
//        break;
//      default:
//        throw new IllegalArgumentException("Shouldn't get here");
//
//    }
//    adaptee.moveTo(note, newOctave, newStart);
//    adaptee.setDuration(newEnd - newStart);
//    adaptee.setInstrument(newInstrument);
//    adaptee.setVolume(newVolume);
//  }


  @Override
  public int getVolume() {
    return adaptee.getVolume();
  }

  @Override
  public MusicEditorModel.Pitch getPitch() {
    Note.Pitches aPitch = adaptee.getPitch();
    switch (aPitch) {
      case C:
        return MusicEditorModel.Pitch.C;
      case CSharp:
        return MusicEditorModel.Pitch.CSHARP;
      case D:
        return MusicEditorModel.Pitch.D;
      case DSharp:
        return MusicEditorModel.Pitch.DSHARP;
      case E:
        return MusicEditorModel.Pitch.E;
      case F:
        return MusicEditorModel.Pitch.F;
      case FSharp:
        return MusicEditorModel.Pitch.FSHARP;
      case G:
        return MusicEditorModel.Pitch.G;
      case GSharp:
        return MusicEditorModel.Pitch.GSHARP;
      case A:
        return MusicEditorModel.Pitch.A;
      case ASharp:
        return MusicEditorModel.Pitch.ASHARP;
      case B:
        return MusicEditorModel.Pitch.B;
      default:
        throw new IllegalArgumentException("Shouldn't get here");
    }
  }

  @Override
  public int getPitchNum() {
    return adaptee.toIndex() + adaptee.getOctave() * 12;
  }

  @Override
  public int getStart() {
    return adaptee.getStartMeasure();
  }

  @Override
  public int getOctave() {
    return adaptee.getOctave();
  }

  @Override
  public int getEnd() {
    return adaptee.getStartMeasure() + adaptee.getDuration();
  }

  @Override
  public int getInstrument() {
    return adaptee.getInstrument();
  }

  @Override
  public boolean inRange(int time) {
    return super.inRange(time);
  }

  @Override
  public boolean equals(ANote other) {
    return this.adaptee.equals(new ANoteAdapter(other).generate());
  }
}
