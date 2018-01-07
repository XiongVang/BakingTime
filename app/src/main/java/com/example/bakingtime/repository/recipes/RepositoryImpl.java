package com.example.bakingtime.repository.recipes;


import com.example.bakingtime.repository.recipes.model.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;

public final class RepositoryImpl implements Repository {
    private final String RECIPES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private final RecipesApi recipesApi;

    @Inject
    public RepositoryImpl(Retrofit.Builder retrofitBuilder) {
        recipesApi = retrofitBuilder.baseUrl(RECIPES_URL)
                .build()
                .create(RecipesApi.class);
    }

    @Override
    public Single<List<Recipe>> getAllRecipes() {
        return recipesApi.getAllRecipes().cache();
    }

    @Override
    public Single<Recipe> getRecipe(String id) {
        return getAllRecipes().map(recipes -> {
            for (Recipe recipe : recipes) {
                if (recipe.getId().equals(id)) {
                    return recipe;
                }
            }

            throw new Exception(new Throwable("Recipe id did not match"));
        }).cache();
    }

}
