package com.example.bakingtime.view.recipes;

import android.support.v4.app.Fragment;

import com.example.bakingtime.view.SingleFragmentActivity;

public class RecipesActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new RecipesFragment();
    }
}
