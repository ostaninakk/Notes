package ostanina.kk.unit_testing_2.ui.noteList;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import ostanina.kk.unit_testing_2.models.Note;
import ostanina.kk.unit_testing_2.repository.NoteRepository;
import ostanina.kk.unit_testing_2.ui.Resource;

public class NotesListViewModel extends ViewModel {

    private static final String TAG = "NotesListViewModel";

    // inject
    private final NoteRepository noteRepository;

    private MediatorLiveData<List<Note>> notes = new MediatorLiveData<>();

    @Inject
    public NotesListViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<Resource<Integer>> deleteNote(final Note note) throws Exception{
        return noteRepository.deleteNote(note);
    }

    public LiveData<List<Note>> observeNotes(){
        return notes;
    }

    public void getNotes(){
        final LiveData<List<Note>> source = noteRepository.getNotes();
        notes.addSource(source, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesList) {
                if(notesList != null){
                    notes.setValue(notesList);
                }
                notes.removeSource(source);
            }
        });
    }
}