package com.example.bakingtime.view.recipesteps;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.bakingtime.view.SingleFragmentActivity;


public class RecipeStepsActivity extends SingleFragmentActivity {

    private static final String RECIPE_ID = "RecipeStepsActivity.RECIPE_ID";

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, RecipeStepsActivity.class);
        intent.putExtra(RECIPE_ID, id);

        return intent;
    }

    @Override
    protected Fragment createFragment() {

        String id = getIntent().getStringExtra(RECIPE_ID);

        return RecipeStepsFragment.newInstance(id);
    }
}
