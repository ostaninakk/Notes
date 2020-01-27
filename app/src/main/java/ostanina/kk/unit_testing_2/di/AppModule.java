package ostanina.kk.unit_testing_2.di;

import android.app.Application;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ostanina.kk.unit_testing_2.persistance.NoteDao;
import ostanina.kk.unit_testing_2.persistance.NoteDatabase;
import ostanina.kk.unit_testing_2.repository.NoteRepository;

@Module
public class AppModule {
    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application) {
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                NoteDatabase.DATABASE_NAME).build();

    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase database) {
        return database.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao) {
        return new NoteRepository(noteDao);
    }

}
