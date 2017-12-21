package com.ibtikar.myfirstmvvmapplication.di;

import com.ibtikar.myfirstmvvmapplication.application.MyFirstMvvmApplication;
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent;
import com.ibtikar.myfirstmvvmapplication.di.component.DaggerApplicationComponent;
import com.ibtikar.myfirstmvvmapplication.di.modules.ApplicationModule;
import com.ibtikar.myfirstmvvmapplication.di.modules.NetworkModule;
import com.ibtikar.myfirstmvvmapplication.utils.Constants;

public enum Injector {
    INSTANCE;

    private ApplicationComponent applicationComponent;

    public ApplicationComponent initializeAppComponent(MyFirstMvvmApplication application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
        return applicationComponent;
    }

    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
