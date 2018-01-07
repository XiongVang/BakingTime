package com.example.bakingtime.repository.networking;

import com.example.bakingtime.repository.networking.model.Recipe;

import java.util.List;

import io.reactivex.Single;

public interface Repository {

    Single<List<Recipe>> getAllRecipes();

    Single<Recipe> getRecipe(String id);
}
