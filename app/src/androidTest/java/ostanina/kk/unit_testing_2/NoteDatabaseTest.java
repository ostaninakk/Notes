package ostanina.kk.unit_testing_2;

import org.junit.After;
import org.junit.Before;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import ostanina.kk.unit_testing_2.models.Note;
import ostanina.kk.unit_testing_2.persistance.NoteDao;
import ostanina.kk.unit_testing_2.persistance.NoteDatabase;
import ostanina.kk.unit_testing_2.util.UtilTest;

public abstract class NoteDatabaseTest {

    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao() {
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init() {
        noteDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish() {
        noteDatabase.close();
    }

}
