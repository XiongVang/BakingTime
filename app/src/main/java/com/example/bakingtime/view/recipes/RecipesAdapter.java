package com.example.bakingtime.view.recipes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingtime.R;
import com.example.bakingtime.viewmodel.uimodel.RecipeUiModel;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.Collections;
import java.util.List;


public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder> {

    private static final String TAG = "RecipesAdapter";

    private PublishRelay<String> mRecipeClickNotifier = PublishRelay.create();

    private Context mContext;

    private List<RecipeUiModel> mRecipesList;

    public RecipesAdapter() {
        mRecipesList = Collections.EMPTY_LIST;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_text_card, parent, false);
        return new RecipeHolder(view);
    }


    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {

        holder.setRecipeText(mRecipesList.get(position).getName());
        holder.itemView.setOnClickListener(v -> mRecipeClickNotifier.accept(mRecipesList.get(position).getId()));

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

            mRecipeText = (TextView) itemView.findViewById(R.id.single_text);
        }

        public void setRecipeText(String recipe) {
            Log.d(TAG, "setRecipeText: " + recipe);
            mRecipeText.setText(recipe);
        }
    }

    public void updateRecipesList(List<RecipeUiModel> updatedRecipes) {
        mRecipesList = updatedRecipes;
        notifyDataSetChanged();
    }

    public PublishRelay<String> getRecipeClickNotifier(){
        return mRecipeClickNotifier;
    }


}
