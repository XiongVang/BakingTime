package com.example.bakingtime.viewmodel;

import com.example.bakingtime.repository.recipes.Repository;
import com.example.bakingtime.repository.recipes.model.Recipe;
import com.example.bakingtime.viewmodel.uimodel.RecipeStepsUiModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class RecipeStepsViewModel {

    private Repository repository;

    @Inject
    public RecipeStepsViewModel(Repository repository) {
        this.repository = repository;
    }

    public Single<RecipeStepsUiModel> getRecipe(String id) {
        return repository.getRecipe(id).map(new Function<Recipe, RecipeStepsUiModel>() {
            @Override
            public RecipeStepsUiModel apply(Recipe recipe) throws Exception {

                StringBuilder ingredients = new StringBuilder();
                List<String> steps = new ArrayList<>();

                recipe.getIngredients().stream().forEach(ingredient -> {

                    ingredients
                            .append(" - ")
                            .append(ingredient.getIngredient())
                            .append(": ")
                            .append(" ")
                            .append(ingredient.getQuantity())
                            .append(" ")
                            .append(ingredient.getMeasure())
                            .append("\n");
                });

                recipe.getSteps().stream().forEach(step -> steps.add(step.getShortDescription()));

                return new RecipeStepsUiModel(ingredients.toString(),steps);
            }
        });
    }

}
