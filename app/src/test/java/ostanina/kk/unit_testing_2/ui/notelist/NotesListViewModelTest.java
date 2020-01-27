package ostanina.kk.unit_testing_2.ui.notelist;

import androidx.lifecycle.MutableLiveData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import ostanina.kk.unit_testing_2.Util.InstantExecutorExtension;
import ostanina.kk.unit_testing_2.Util.LiveDataTestUtil;
import ostanina.kk.unit_testing_2.Util.UtilTest;
import ostanina.kk.unit_testing_2.models.Note;
import ostanina.kk.unit_testing_2.repository.NoteRepository;
import ostanina.kk.unit_testing_2.ui.Resource;
import ostanina.kk.unit_testing_2.ui.noteList.NotesListViewModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ostanina.kk.unit_testing_2.repository.NoteRepository.DELETE_FAILURE;
import static ostanina.kk.unit_testing_2.repository.NoteRepository.DELETE_SUCCESS;

@ExtendWith(InstantExecutorExtension.class)
public class NotesListViewModelTest {

    // system under test
    private NotesListViewModel viewModel;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        viewModel = new NotesListViewModel(noteRepository);
    }

    /*
        Retrieve list of notes
        observe list
        return list
     */

    @Test
    void retrieveNotes_returnNotesList() throws Exception {
        // Arrange
        List<Note> returnedData = UtilTest.TEST_NOTES_LIST;
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.getNotes()).thenReturn(returnedValue);

        // Act
        viewModel.getNotes();
        List<Note> observedData = liveDataTestUtil.getValue(viewModel.observeNotes());

        // Assert
        assertEquals(returnedData, observedData);
    }
    /*
        retrieve list of notes
        observe the list
        return empty list
     */

    @Test
    void retrieveNotes_returnEmptyNotesList() throws Exception {
        // Arrange
        List<Note> returnedData = new ArrayList<>();
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.getNotes()).thenReturn(returnedValue);

        // Act
        viewModel.getNotes();
        List<Note> observedData = liveDataTestUtil.getValue(viewModel.observeNotes());

        // Assert
        assertEquals(returnedData, observedData);
    }


    /*
        delete note
        observe Resource.success
        return Resource.success
     */

    @Test
    void deleteNote_observeResourceSuccess() throws Exception {
        // Arrange
        Note deletedNote = new Note(UtilTest.TEST_NOTE_1);
        Resource<Integer> returnedData = Resource.success(1, DELETE_SUCCESS);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<Resource<Integer>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.deleteNote(any(Note.class))).thenReturn(returnedValue);

        // Act
        Resource<Integer> observedValue = liveDataTestUtil.getValue(viewModel.deleteNote(deletedNote));


        // Assert
        assertEquals(returnedData, observedValue);
    }

    /*
        delete note
        observe Resource.error
        return Resource.error
     */
    @Test
    void deleteNote_observeResourceError() throws Exception {
        // Arrange
        Note deletedNote = new Note(UtilTest.TEST_NOTE_1);
        Resource<Integer> returnedData = Resource.error(null, DELETE_FAILURE);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<Resource<Integer>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.deleteNote(any(Note.class))).thenReturn(returnedValue);

        // Act
        Resource<Integer> observedValue = liveDataTestUtil.getValue(viewModel.deleteNote(deletedNote));


        // Assert
        assertEquals(returnedData, observedValue);
    }

}
