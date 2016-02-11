package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.ModelAdapter;
import cs3500.music.modelTheirs.MusicEditorModel;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ViewModel;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.ModelImpl;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view2.ViewFactory;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    String file = args[0];
    String in = args[1];

    MusicReader reader = new MusicReader();
    CompositionBuilder<Model> compositionBuilder = ModelImpl.builder();
    try {
      reader.parseFile(new FileReader(args[0]),
              compositionBuilder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Model model = compositionBuilder.build();
    MusicEditorModel adapter = new ModelAdapter(model);
    ViewFactory.make(args[1], adapter);
  }
}
