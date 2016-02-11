package cs3500.music.model;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import cs3500.music.modelTheirs.ANote;
import cs3500.music.modelTheirs.MusicEditorModel;

import static org.junit.Assert.*;

/**
 * Created by rosaline17 on 12/10/15.
 */
public class ModelAdapterTest {

  @Test
  public void testAddNote() throws Exception {

    Note c2 = new Note(4, 2, 2, Note.Pitches.C, false, 0, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 0, 1);
    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    assertTrue(adapter.addNote(c2a));
    assertFalse(adapter.addNote(c2a));
  }

  @Test
  public void testEdit() throws Exception {

    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(4, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);

    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    assertTrue(adapter.addNote(c2a));
    assertTrue(m3.find(c2.getStartMeasure(), c2.getDuration(), c2
            .getOctave(), c2
            .toMidiIndex()).equals(c2));
    adapter.edit(c2a, MusicEditorModel.Pitch.D, 2, 0, 1, 2, 1);
    assertFalse(adapter.addNote(d2a));

  }

  @Test
  public void testRemove() throws Exception {

    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);

    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    adapter.addNote(d2a);
    adapter.remove(c2a);
    assertTrue(adapter.addNote(c2a));
  }

  @Test
  public void testNotesPlaying0() throws Exception {
    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);

    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    adapter.addNote(d2a);
    assertEquals(1, adapter.notesPlaying(2).size());
  }
  @Test
  public void testNotesPlaying1() throws Exception {
    Note c2 = new Note(4, 2, 0, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(4, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);

    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    assertTrue(adapter.addNote(c2a));
    assertTrue(adapter.addNote(d2a));
    assertEquals(adapter.notesPlaying(2).size(), 2);
  }

  @Test
  public void testGetEnd() throws Exception {

    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);
    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    adapter.addNote(d2a);
    assertEquals(5, adapter.getEnd());
  }

  @Test
  public void testGetTempo() throws Exception {
    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    assertEquals(130000, adapter.getTempo());

  }

  @Test
  public void testGetLowOrHighNote() throws Exception {
    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 2, 1);
    Note b2 = new Note(1, 2, 0, Note.Pitches.B, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);
    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    adapter.addNote(d2a);
    assertEquals(c2.toIndex(), adapter.getLowOrHighNote("low", 2));
    assertEquals(b2.toIndex(), adapter.getLowOrHighNote("high", 2));
  }

  @Test
  public void testGetMinOrMaxOctave() throws Exception {
    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);
    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    adapter.addNote(d2a);
    assertEquals(c2.getOctave(), adapter.getMinOrMaxOctave("max"));
    assertEquals(d2.getOctave(), adapter.getMinOrMaxOctave("min"));
  }


  @Test
  public void testPrintNotes() throws Exception {

    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);
    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    adapter.addNote(d2a);
    StringBuilder builder = new StringBuilder();
    adapter.printNotes(2,0,11,2, builder);
    assertEquals("C2", builder.toString());
  }

  @Test
  public void testIncrement0() throws Exception {

    Note c2 = new Note(4, 2, 2, Note.Pitches.C, true, 1, 1);
    ANote c2a = new NoteAdapter(c2);
    Note d2 = new Note(1, 2, 0, Note.Pitches.D, true, 2, 1);
    ANote d2a = new NoteAdapter(d2);
    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.addNote(c2a);
    adapter.addNote(d2a);
    adapter.increment();
    assertEquals(1, adapter.getBeat());
  }


  @Test
  public void testIncrement1() throws Exception {

    Model m3 = ModelImpl.builder().build();
    ModelAdapter adapter = new ModelAdapter(m3);
    adapter.increment();
    assertEquals(0, adapter.getBeat());
  }

}