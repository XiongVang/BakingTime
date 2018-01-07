package com.example.bakingtime.di;

import com.example.bakingtime.repository.recipes.Repository;
import com.example.bakingtime.repository.recipes.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Singleton
@Module
public class RepositoryModule {
    
    @Singleton
    @Provides
    Repository provideRepository(Retrofit.Builder retrofitBuilder){
        return new RepositoryImpl(retrofitBuilder);
    }
}
