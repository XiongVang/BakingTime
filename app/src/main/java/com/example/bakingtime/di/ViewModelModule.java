package com.example.bakingtime.di;

import com.example.bakingtime.repository.recipes.Repository;
import com.example.bakingtime.viewmodel.RecipeStepsViewModel;
import com.example.bakingtime.viewmodel.RecipesViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class ViewModelModule {

    @Singleton
    @Provides
    RecipesViewModel provideRecipesViewModel(Repository repository){
        return new RecipesViewModel(repository);
    }

    @Singleton
    @Provides
    RecipeStepsViewModel provideRecipeStepsViewModel(Repository repository){
        return new RecipeStepsViewModel(repository);
    }
}
