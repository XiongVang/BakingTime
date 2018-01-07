package com.example.bakingtime.view.recipesteps;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingtime.BakingTimeApp;
import com.example.bakingtime.R;
import com.example.bakingtime.viewmodel.RecipeStepsViewModel;
import com.example.bakingtime.viewmodel.uimodel.RecipeStepsUiModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class RecipeStepsFragment extends Fragment {

    private static final String ARG_RECIPE_ID = "RecipeStepsFragment.ARG_RECIPE_ID";

    public static Fragment newInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_RECIPE_ID , id);
        Fragment fragment = new RecipeStepsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    // Dagger
    @Inject
    RecipeStepsViewModel mRecipeStepsViewModel;

    // ButterKnife
    private Unbinder mUnbinder;
    @BindView(R.id.ingredients_text)
    TextView mIngredients_Text;
    @BindView(R.id.recipe_steps_recycler)
    RecyclerView mRecipeStepsRecyler;

    // RecyclerView
    private RecipeStepsAdapter mRecipeStepsAdapter;

    private Context mContext;
    private String mRecipeId;
    private RecipeStepsUiModel mRecipeStepsUiModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_steps,container,false);

        mContext = container.getContext();

        // Dagger injection
        ((BakingTimeApp) mContext.getApplicationContext())
                .getViewModelComponent()
                .inject(this);

        // Butterknife injection
        mUnbinder = ButterKnife.bind(this,view);

        // Retrieve recipe id
        mRecipeId = getArguments().getString(ARG_RECIPE_ID);

        // Setup RecyclerView
        mRecipeStepsAdapter = new RecipeStepsAdapter();
        mRecipeStepsRecyler.setAdapter(mRecipeStepsAdapter);
        mRecipeStepsRecyler.setLayoutManager(new LinearLayoutManager(mContext));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        subcribe();

    }

    private void subcribe() {

        mRecipeStepsViewModel.getRecipe(mRecipeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<RecipeStepsUiModel>() {
                    @Override
                    public void onSuccess(RecipeStepsUiModel recipeStepsUiModel) {
                        mRecipeStepsUiModel = recipeStepsUiModel;
                        populateViews();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: getRecipe() id " + mRecipeId, e);
                    }
                });
    }

    private void populateViews(){
        mIngredients_Text.setText(mRecipeStepsUiModel.getIngredients());
        mRecipeStepsAdapter.updateRecipesList(mRecipeStepsUiModel.getSteps());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
