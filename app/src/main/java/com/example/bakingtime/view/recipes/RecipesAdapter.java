package com.example.bakingtime.view.recipes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingtime.R;

import java.util.Arrays;
import java.util.List;


public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder> {

    private static final String TAG = "RecipesAdapter";

    private Context mContext;

    private List<String> mRecipesList;

    public RecipesAdapter(){
        mRecipesList = getMockRecipes();
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_card, parent, false);
        return new RecipeHolder(view);
    }

    private List<String> getMockRecipes() {
        String[] recipes = {"Nutella Pie", "Brownies", "Yellow Cake", "Cheesecake"};

        return Arrays.asList(recipes);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {

        holder.setRecipeText(mRecipesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipesList.size();
    }

    public class RecipeHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "RecipeHolder";

        private TextView mRecipeText;

        public RecipeHolder(View itemView) {
            super(itemView);

            mRecipeText = (TextView) itemView.findViewById(R.id.recipe_text_view);
        }

        public void setRecipeText(String recipe) {
            Log.d(TAG, "setRecipeText: " + recipe);
            mRecipeText.setText(recipe);
        }
    }
}
