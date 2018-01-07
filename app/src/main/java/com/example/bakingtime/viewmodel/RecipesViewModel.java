package com.example.bakingtime.viewmodel;

import com.example.bakingtime.repository.recipes.Repository;
import com.example.bakingtime.repository.recipes.model.Recipe;
import com.example.bakingtime.viewmodel.uimodel.RecipeUiModel;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.Single;

public class RecipesViewModel {

    private static final String TAG = "RecipesViewModel";

    private Repository repository;

    @Inject
    public RecipesViewModel(Repository repository) {
        this.repository = repository;
    }

    public Single<List<RecipeUiModel>> getAllRecipes() {
        return repository.getAllRecipes()
                .map(recipes -> recipes.stream()
                        .map(recipe -> new RecipeUiModel(recipe.getId(), recipe.getName()))
                        .collect(Collectors.toList()));
    }

    public Single<Recipe> getRecipe(String id) {
        return repository.getRecipe(id);
    }
}
