package ostanina.kk.unit_testing_2.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import ostanina.kk.unit_testing_2.BaseApplication;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuildersModule.class,
        ViewModelFactoryModule.class})

public interface AppComponent extends AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
