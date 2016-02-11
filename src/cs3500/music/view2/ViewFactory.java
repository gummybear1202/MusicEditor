package cs3500.music.view2;

import cs3500.music.modelTheirs.MusicEditorModel;

/**
 * Created by alexgomez on 11/15/15.
 */
public class ViewFactory {
  public static View make(String view, MusicEditorModel model) {
    switch (view) {
      case "midi":
        return new MidiViewImpl(model);
      case "console":
        return new ConsoleView(model, System.out);
      case "gui":
        return new GuiViewFrame(model);
      case "composite":
        return new CompoundView(model);
      default:
        throw new IllegalArgumentException("invalid argument");
    }
  }

}
