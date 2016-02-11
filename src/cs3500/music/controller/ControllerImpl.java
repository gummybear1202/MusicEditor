package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.Model;
import cs3500.music.model.Note;

import cs3500.music.model.NoteAdapter;
import cs3500.music.view2.CompoundView;


/**
 * Concrete controller class.
 */
public final class ControllerImpl implements Controller {
  private final KeyboardHandler keyHandler;
  private final MouseHandler mouseHandler;
  private final Model model;
  private CompoundView view;
  // either adding or removing or moving
  private Mode mode;
  private State state;


  // pause/resume state
  protected enum State {
    Paused, Playing;
  }

  protected enum Mode {
    Adding, Removing, Moving, Resting;
  }


  private int currentMeasure;
  private final String fileName;


  /**
   * Constructs an implementation of a controller
   *
   * @param model    the model
   * @param view     the view
   * @param fileName the name of the file that is being rendered
   */
  public ControllerImpl(CompoundView view, Model model, String fileName)
          throws InvalidMidiDataException {
    this.view = view;
    this.keyHandler = new KeyboardHandler();
    this.mouseHandler = new MouseHandler();
    this.model = model;
    this.mode = Mode.Resting;
    this.state = State.Paused;
    this.currentMeasure = this.model.getCurrentMeasure();
    this.fileName = fileName;
  }


  /**
   * Initializes the maps that are used in the keyboard handler.
   */
  @Override
  public void initialize() {

    ControllerImpl that = this;

    keyHandler.addToKeyTyped(KeyEvent.VK_E, () -> {
      if (mode == Mode.Adding) {
        mode = Mode.Resting;
      } else {
        mode = Mode.Adding;
      }
    });
    keyHandler.addToKeyTyped(KeyEvent.VK_R, () -> {
      if (this.mode == Mode.Removing) {
        this.mode = Mode.Resting;
      } else {
        this.mode = Mode.Removing;
      }
    });
    keyHandler.addToKeyTyped(KeyEvent.VK_M, () -> {
      if (this.mode == Mode.Moving) {
        this.mode = Mode.Resting;
      } else {
        this.mode = Mode.Moving;
      }
    });
    keyHandler.addToKeyTyped(KeyEvent.VK_COMMA, () -> {
      this.toBeginning();
    });
    keyHandler.addToKeyTyped(KeyEvent.VK_PERIOD, () -> {
      this.toEnd();
    });

    keyHandler.addToKeyPressed(KeyEvent.VK_SPACE, () -> {
      if (this.state.equals(State.Playing)) {
        this.state = State.Paused;
      } else {
        this.state = State.Playing;
        //TODO find out what time is? What is the go method?
        view.go(model.getTempo());
      }
    });
    view.setKeyListener(keyHandler);

    mouseHandler.addToMouseButton("Press", () -> {

      if (this.mode == Mode.Adding) {
        this.alter("add");
      } else if (this.mode == Mode.Removing) {
        this.alter("remove");
      } else if (this.mode == Mode.Moving) {
        this.alter("move");
      }
    });
    view.setMouseListener(mouseHandler);

  }

  public void alter(String execute) {
    int x1 = (mouseHandler.getXPress() - 30) / 20;
    int x2 = (mouseHandler.getXRelease() - 30) / 20;
    int y1 = (mouseHandler.getYPress() - 30) / 20;
    int y2 = (mouseHandler.getYRelease() - 30) / 20;
    Note base = this.model.getLowest();
    for (int i = model.getPitches().size() - 1; i > y1; i--) {
      base.up();
      System.out.println("This is base: " + base.toString());
    }
    Note base2 = this.model.getHighest();

    for (int i = 0; i < y2; i++) {
      base2.down();
    }

    if (execute.equals("add")) {
      this.addNote(x1, x2 - x1, base.getOctave(), base.toMidiIndex());
    }
    if (execute.equals("remove")) {
      this.removeNote(x1, x2 - x1, Note.intToOctave(base.toMidiIndex()), base.toMidiIndex());
    }
    if (execute.equals("move")) {
      Set<Note> beat = this.model.getHeadNotes().get(x1);
      this.moveNote(x1, Note.intToOctave(base.toMidiIndex()), x2, Note
              .intToOctave(base2.toMidiIndex()), base
              .getDuration(), base.toMidiIndex(), base2
              .toMidiIndex());
    }
  }

  /**
   * Adds a note to the model.
   */
  public void addNote(int start, int dur, int oct, int midi) {
    int duration = dur;
    int octave = oct;
    int startingMeasure = start;
    int midiPitch = midi;
    int instrument = 1;
    int volume = 70;
    Note n = new Note(duration, octave, startingMeasure, Note.intToPitch(midiPitch),
            true, instrument, volume);
    view.addNote(new NoteAdapter(n));
  }

  /**
   * Removes a note to the model.
   */
  public void removeNote(int start, int dur, int oct, int midi) {
    try {
      int duration = dur;
      int octave = oct;
      int startingMeasure = start;
      int midiPitch = midi;
      int instrument = 1;
      int volume = 70;
      Note n = this.model.find(start, dur, oct, midi);
      this.model.removeNote(n);
    } catch (IllegalArgumentException e) {

    }

    this.mode = Mode.Resting;
  }

  /**
   * Moves a note to the model.
   */
  public void moveNote(int oldStart, int oldOct, int newStart, int newOct, int
          dur, int oldMidi, int newMidi) {
    try {
      int instrument = 1;
      int volume = 70;
      Note n = this.model.find(oldStart, dur, oldOct, oldMidi);
      this.model.move(n, Note.intToPitch(newMidi), newOct, newStart);
    } catch (IllegalArgumentException e) {

    }
    this.mode = Mode.Resting;
  }

  /**
   * Scrolls to the beginning of the piece.
   */
  public void toBeginning() {
    this.view.home();
  }

  /**
   * Scrolls to the end of the piece.
   */
  public void toEnd() {
    this.view.end();
  }

  @Override
  public State getState() {
    return this.state;
  }

  @Override
  public Mode getMode() {
    return this.mode;
  }

  @Override
  public KeyboardHandler getKeyHandler() {
    return this.keyHandler;
  }

}
