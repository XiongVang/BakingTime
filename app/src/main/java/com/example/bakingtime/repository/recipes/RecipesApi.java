package com.example.bakingtime.repository.networking;

import com.example.bakingtime.repository.networking.model.Recipe;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by vang4999 on 7/3/17.
 */

public interface RecipesApi {
    @GET("baking.json")
    Single<List<Recipe>> getAllRecipes();
}
