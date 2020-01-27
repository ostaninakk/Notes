package ostanina.kk.unit_testing_2;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import ostanina.kk.unit_testing_2.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
