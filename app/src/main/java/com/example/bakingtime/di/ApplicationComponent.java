package com.example.bakingtime.di;

import android.app.Application;

import dagger.Component;

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application application);
}