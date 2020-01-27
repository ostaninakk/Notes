package ostanina.kk.unit_testing_2.ui.note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import io.reactivex.internal.operators.single.SingleToFlowable;
import ostanina.kk.unit_testing_2.Util.InstantExecutorExtension;
import ostanina.kk.unit_testing_2.models.Note;
import ostanina.kk.unit_testing_2.repository.NoteRepository;
import ostanina.kk.unit_testing_2.ui.Resource;
import ostanina.kk.unit_testing_2.ui.note.NoteViewModel;
import ostanina.kk.unit_testing_2.util.LiveDataTestUtil1;
import ostanina.kk.unit_testing_2.util.UtilTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ostanina.kk.unit_testing_2.repository.NoteRepository.INSERT_SUCCESS;

@ExtendWith(InstantExecutorExtension.class)
public class NoteViewModelTest {
    private NoteViewModel noteViewModel;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
        noteViewModel = new NoteViewModel(noteRepository);
    }

    @Test
    void observeEmptyNoteWhenNoteSet() throws Exception {
        LiveDataTestUtil1<Note> liveDataTestUtil = new LiveDataTestUtil1<>();

        Note note = liveDataTestUtil.getValue(noteViewModel.observeNote());

        assertNull(note);

    }

    @Test
    void observeNote_whenSet() throws Exception {
        LiveDataTestUtil1<Note> liveDataTestUtil = new LiveDataTestUtil1<>();
        Note note = new Note(UtilTest.TEST_NOTE_1);

        noteViewModel.setNote(note);
        Note insertedNote = liveDataTestUtil.getValue(noteViewModel.observeNote());

        assertEquals(note, insertedNote);
    }

    @Test
    void insertNote_returnRow() throws Exception {
        LiveDataTestUtil1<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil1<>();
        Note note = new Note(UtilTest.TEST_NOTE_1);
        final int insertedRow = 1;
        Flowable<Resource<Integer>> returnedData = SingleToFlowable.just(Resource.success(insertedRow, INSERT_SUCCESS));
        when(noteRepository.insertNote(any(Note.class))).thenReturn(returnedData);

        noteViewModel.setNote(note);
        Resource<Integer> insertedNote = liveDataTestUtil.getValue(noteViewModel.insertNote());

        assertEquals(insertedNote, Resource.success(insertedRow, INSERT_SUCCESS));
    }

    @Test
    void dontReturnInsertRowWithoutObserver() throws Exception {
        Note note = new Note(UtilTest.TEST_NOTE_1);

        noteViewModel.setNote(note);

        verify(noteRepository, never()).insertNote(note);

    }

    @Test
    void setNote_nullTitle_throwException() throws Exception {
        final Note note = new Note(UtilTest.TEST_NOTE_1);
        note.setTitle(null);

        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                noteViewModel.setNote(note);

            }
        });
    }
}
