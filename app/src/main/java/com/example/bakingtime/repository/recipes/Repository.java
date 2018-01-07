package com.example.bakingtime.repository.recipes;

import com.example.bakingtime.repository.recipes.model.Recipe;

import java.util.List;

import io.reactivex.Single;

public interface Repository {

    Single<List<Recipe>> getAllRecipes();

    Single<Recipe> getRecipe(String id);
}
