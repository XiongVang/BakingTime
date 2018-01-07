package com.example.bakingtime.di;


import com.example.bakingtime.view.recipes.RecipesFragment;
import com.example.bakingtime.view.recipesteps.RecipeStepsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class, RepositoryModule.class,ViewModelModule.class})
public interface ViewModelComponent {
    void inject(RecipesFragment fragment);
    void inject(RecipeStepsFragment fragment);
}
