package com.example.bakingtime;

import android.app.Application;

import com.example.bakingtime.di.ApplicationModule;
import com.example.bakingtime.di.DaggerViewModelComponent;
import com.example.bakingtime.di.NetworkingModule;
import com.example.bakingtime.di.RepositoryModule;
import com.example.bakingtime.di.ViewModelComponent;
import com.example.bakingtime.di.ViewModelModule;


public class BakingTimeApp extends Application {

    ViewModelComponent mViewModelComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mViewModelComponent = DaggerViewModelComponent.builder()
                .networkingModule(new NetworkingModule())
                .repositoryModule(new RepositoryModule())
                .viewModelModule(new ViewModelModule())
                .applicationModule(new ApplicationModule(this))
                .build();



    }

    public ViewModelComponent getViewModelComponent() {
        return mViewModelComponent;
    }

}