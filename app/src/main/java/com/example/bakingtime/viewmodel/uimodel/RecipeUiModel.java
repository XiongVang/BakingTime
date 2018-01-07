package com.example.bakingtime.viewmodel.uimodel;

import android.support.annotation.NonNull;

/**
 * Created by vang4999 on 7/3/17.
 */

public final class RecipeUiModel {

    private String id;
    private String name;

    public RecipeUiModel(@NonNull String id, @NonNull String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
