package com.example.bakingtime.view.recipesteps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingtime.R;

import java.util.Collections;
import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepHolder> {

    private static final String TAG = "RecipeStepsAdapter";

    private Context mContext;
    private List<String> mRecipeSteps;

    public RecipeStepsAdapter() {
        mRecipeSteps = Collections.EMPTY_LIST;
    }

    @Override
    public RecipeStepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_text_card,parent,false);
        return new RecipeStepHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeStepHolder holder, int position) {
        holder.setRecipeStepText(mRecipeSteps.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipeSteps.size();
    }

    public class RecipeStepHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "RecipeStepHolder";

        private TextView mRecipeStep;

        public RecipeStepHolder(View itemView) {
            super(itemView);
            mRecipeStep = (TextView) itemView.findViewById(R.id.single_text);
        }

        public void setRecipeStepText(String recipeStep){
            Log.d(TAG, "setRecipeStepText: " + recipeStep);
            mRecipeStep.setText(recipeStep);
        }
    }

    public void updateRecipesList(List<String> updatedRecipeSteps){
        mRecipeSteps = updatedRecipeSteps;
        notifyDataSetChanged();
    }
}
