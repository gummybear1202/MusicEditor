package cs3500.music.view2;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.modelTheirs.ANote;
import cs3500.music.modelTheirs.MusicEditorModel;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {
  MusicEditorModel model;
  int lowestOctave;
  int highestOctave;
  int minNote;
  int maxNote;
  int end;
  int lowPitchNum;
  int maxPitchNum;
  static final int BEAT_WIDTH = 20;
  static final int BEAT_LENGTH = 20;


  public ConcreteGuiViewPanel(MusicEditorModel model) {
    this.model = model;
  }


  @Override
  public void paint(Graphics g) {
    lowestOctave = model.getMinOrMaxOctave("min");
    highestOctave = model.getMinOrMaxOctave("max");
    minNote = model.getLowOrHighNote("low", lowestOctave);
    maxNote = model.getLowOrHighNote("high", highestOctave);
    end = model.getEnd();
    lowPitchNum = lowestOctave * 12 + minNote;
    maxPitchNum = highestOctave * 12 + maxNote;

    super.paint(g);
    if (model.getBeat() <= model.getEnd()) {
      g.setColor(Color.RED);
      g.drawLine(2 * BEAT_WIDTH + (model.getBeat() * BEAT_WIDTH), BEAT_LENGTH,
              2 * BEAT_WIDTH + (model.getBeat() * BEAT_WIDTH), getPreferredSize().height);
    }
    this.repaint();


    for (int i = 0; i <= end; i += 16) {
      g.setColor(Color.BLACK);
      g.drawString(Integer.toString(i), BEAT_WIDTH + BEAT_WIDTH * (i + 1) - 7, BEAT_LENGTH / 2);
    }

    int numNotes = 1;
    for (int oct = highestOctave; oct >= lowestOctave; oct--) {
      if (oct == lowestOctave) {
        for (int i = 11; i >= minNote; i--) {
          g.drawString(MusicEditorModel.Pitch.values()[i].value + Integer.toString(oct),
                  0, 15 + (BEAT_LENGTH * numNotes));
          numNotes++;
        }
      } else if (oct == highestOctave) {
        for (int j = maxNote; j >= 0; j--) {
          g.drawString(MusicEditorModel.Pitch.values()[j].value + Integer.toString(oct),
                  0, 15 + (BEAT_LENGTH * numNotes));
          numNotes++;
        }
      } else {
        for (int i = 11; i >= 0; i--) {
          g.drawString(MusicEditorModel.Pitch.values()[i].value + Integer.toString(oct),
                  0, 15 + (BEAT_LENGTH * numNotes));
          numNotes++;
        }
      }

    }


    for (int i = 1; i < numNotes; i++) {
      for (int j = 0; j <= (end / 4); j++) {
        g.drawRect(2 * BEAT_WIDTH + 4 * BEAT_WIDTH * j, BEAT_LENGTH * i,
                4 * BEAT_WIDTH, BEAT_LENGTH);
      }
    }


    for (int i = 0; i <= end; i++) {
      if (!model.notesPlaying(i).isEmpty()) {
        for (ANote n : model.notesPlaying(i)) {
          if (n.getStart() == i) {
            g.setColor(Color.black);
          } else {
            g.setColor(Color.green);
          }
          g.fillRect(2 * BEAT_WIDTH + (BEAT_WIDTH * i),
                  BEAT_LENGTH + BEAT_LENGTH * (maxPitchNum - n.getPitchNum())
                  , BEAT_WIDTH, BEAT_LENGTH);
        }

      }

    }
  }


  public Dimension getPreferredSize() {
    return new Dimension(250 + BEAT_WIDTH * model.getEnd(),
            160 + 12 * BEAT_LENGTH * (highestOctave - lowestOctave + 1)
                    + minNote - 12 +
                    maxNote);
  }

  /**
   * Adds a mouse listener
   */
  public void addMouseListener(MouseListener mouse) {
    super.addMouseListener(mouse);
  }


}



