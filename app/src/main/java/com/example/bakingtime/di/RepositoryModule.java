package com.example.bakingtime.di;

import com.example.bakingtime.repository.networking.Repository;
import com.example.bakingtime.repository.networking.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class RepositoryModule {
    
    @Singleton
    @Provides
    Repository provideRepository(){
        return new RepositoryImpl();
    }
}
