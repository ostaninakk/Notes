package ostanina.kk.unit_testing_2;

import android.database.sqlite.SQLiteConstraintException;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import ostanina.kk.unit_testing_2.models.Note;
import ostanina.kk.unit_testing_2.util.LiveDataTestUtil1;
import ostanina.kk.unit_testing_2.util.UtilTest;

import static org.junit.Assert.*;

public class NoteDaoTest extends NoteDatabaseTest {
    public static final String TEST_TITLE = "This is a test title";
    public static final String TEST_CONTENT = "This is some test content";
    public static final String TEST_TIMESTAMP = "08-2018";

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    /*
       Insert, Read, Delete
    */
    @Test
    public void insertReadDelete() throws Exception{
        Note note = new Note(UtilTest.TEST_NOTE_1);

        // insert
        getNoteDao().insertNote(note).blockingGet();

        // read
        LiveDataTestUtil1<List<Note>> liveDataTestUtil = new LiveDataTestUtil1();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertNotNull(insertedNotes);

        assertEquals(insertedNotes.get(0).getTitle(), note.getTitle());
        assertEquals(insertedNotes.get(0).getContent(), note.getContent());
        assertEquals(insertedNotes.get(0).getTimestamp(), note.getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(insertedNotes.get(0), note);

        // delete
        getNoteDao().deleteNote(note).blockingGet();
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(0, insertedNotes.size());
    }

    /*
        Insert, Read, Update, Read, Delete,
     */
    @Test
    public void insertReadUpdateReadDelete() throws Exception{
        Note note = new Note(UtilTest.TEST_NOTE_1);

        // insert
        getNoteDao().insertNote(note).blockingGet();

        // read
        LiveDataTestUtil1<List<Note>> liveDataTestUtil = new LiveDataTestUtil1();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertNotNull(insertedNotes);

        assertEquals(insertedNotes.get(0).getTitle(), note.getTitle());
        assertEquals(insertedNotes.get(0).getContent(), note.getContent());
        assertEquals(insertedNotes.get(0).getTimestamp(), note.getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(insertedNotes.get(0), note);

        // update
        note.setContent(TEST_CONTENT);
        note.setTitle(TEST_TITLE);
        note.setTimestamp(TEST_TIMESTAMP);
        getNoteDao().updateNote(note).blockingGet();

        // read
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(insertedNotes.get(0).getContent(), TEST_CONTENT);
        assertEquals(insertedNotes.get(0).getTitle(), TEST_TITLE);
        assertEquals(insertedNotes.get(0).getTimestamp(), TEST_TIMESTAMP);

        // delete
        getNoteDao().deleteNote(note).blockingGet();
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(0, insertedNotes.size());
    }

    /*
        Insert note with null title, throw exception
     */
    @Test(expected = SQLiteConstraintException.class)
    public void insert_nullTitle_throwSQLiteConstraintException() throws Exception{

        final Note note = new Note(UtilTest.TEST_NOTE_1);
        note.setTitle(null);

        // insert
        getNoteDao().insertNote(note).blockingGet();
    }


    /*
        Insert, Update with null title, throw exception
     */

    @Test(expected = SQLiteConstraintException.class)
    public void updateNote_nullTitle_throwSQLiteConstraintException() throws Exception{

        Note note = new Note(UtilTest.TEST_NOTE_1);

        // insert
        getNoteDao().insertNote(note).blockingGet();

        // read
        LiveDataTestUtil1<List<Note>> liveDataTestUtil = new LiveDataTestUtil1<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertNotNull(insertedNotes);

        // update
        note = new Note(insertedNotes.get(0));
        note.setTitle(null);
        getNoteDao().updateNote(note).blockingGet();

    }

}
