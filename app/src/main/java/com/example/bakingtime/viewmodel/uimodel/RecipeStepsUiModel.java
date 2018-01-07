package com.example.bakingtime.viewmodel.uimodel;

import java.util.List;

public class RecipeStepsUiModel {

    private String ingredients;
    private List<String> steps;

    public RecipeStepsUiModel(String ingredients, List<String> steps) {
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }
}
