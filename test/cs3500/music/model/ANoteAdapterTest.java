package cs3500.music.model;

import org.junit.Test;

import cs3500.music.modelTheirs.ANote;
import cs3500.music.modelTheirs.MusicEditorModel;

import static org.junit.Assert.*;

/**
 *
 */
public class ANoteAdapterTest {

  @Test
  public void testGenerate0() throws Exception {
    Note c4 = new Note(1, 4, 0, Note.Pitches.C, true, 3, 1);
    ANoteAdapter adapter = new ANoteAdapter(new NoteAdapter(c4));
    assertTrue(c4.equals( adapter.generate()));
  }

  @Test
  public void testGenerate1() throws Exception {
    Note d4 = new Note(1, 4, 0, Note.Pitches.D, false, 3, 1);
    ANoteAdapter adapter = new ANoteAdapter(new NoteAdapter(d4));
    assertTrue(d4.equals( adapter.generate()));
  }

  @Test
  public void testConvertToPitch0() throws Exception {
    Note d4 = new Note(1, 4, 0, Note.Pitches.D, false, 3, 1);
    ANote anote = new NoteAdapter(d4);
    assertEquals(MusicEditorModel.Pitch.D, anote.getPitch());
  }

  @Test
  public void testConvertToPitch1() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    ANote anote = new NoteAdapter(fs4);
    assertEquals(MusicEditorModel.Pitch.FSHARP, anote.getPitch());
    assertEquals(6, anote.getPitch().order);
    assertEquals("F#", anote.getPitch().value);
  }
}