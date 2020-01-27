package ostanina.kk.unit_testing_2.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ostanina.kk.unit_testing_2.ui.noteList.NotesListActivity;
import ostanina.kk.unit_testing_2.ui.note.NoteActivity;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();

    @ContributesAndroidInjector
    abstract NoteActivity contributeNoteActivity();
}
