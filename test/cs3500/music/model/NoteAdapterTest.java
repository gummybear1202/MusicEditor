package cs3500.music.model;

import org.junit.Test;

import cs3500.music.modelTheirs.ANote;
import cs3500.music.modelTheirs.MusicEditorModel;

import static org.junit.Assert.*;

/**
 * Created by rosaline17 on 12/10/15.
 */
public class NoteAdapterTest {

  @Test
  public void testEquals0() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    ANote anote = new NoteAdapter(fs4);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertTrue(fs4.equals(fs4));
    assertTrue(adapter.equals(anote));
  }


  @Test
  public void testEquals1() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    Note f4 = new Note(1, 4, 0, Note.Pitches.F, true, 3, 1);
    ANote anote = new NoteAdapter(f4);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertFalse(fs4.equals(f4));
    assertFalse(adapter.equals(anote));
  }

  @Test
  public void testGetVolume() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(1, adapter.getVolume());
  }

  @Test
  public void testGetPitch() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(MusicEditorModel.Pitch.FSHARP, adapter.getPitch());
  }

  @Test
  public void testGetPitchNum() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(54, adapter.getPitchNum());
  }

  @Test
  public void testGetStart() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(0, adapter.getStart());
  }

  @Test
  public void testGetOctave() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(4, adapter.getOctave());
  }

  @Test
  public void testGetEnd() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(1, adapter.getVolume());
  }

  @Test
  public void testGetInstrument() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(3, adapter.getInstrument());
  }

  @Test
  public void testInRange0() throws Exception {
    Note fs4 = new Note(2, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(false, adapter.inRange(4));
    assertEquals(true, adapter.inRange(1));
    assertEquals(false, adapter.inRange(0));
    assertEquals(false, adapter.inRange(2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInRange1() throws Exception {
    Note fs4 = new Note(2, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    adapter.inRange(-4);
  }

  @Test
  public void testInRange2() throws Exception {
    Note fs4 = new Note(1, 4, 0, Note.Pitches.FSharp, true, 3, 1);
    NoteAdapter adapter = new NoteAdapter(fs4);
    assertEquals(false, adapter.inRange(4));
    assertEquals(false, adapter.inRange(0));
    assertEquals(false, adapter.inRange(1));
  }
}