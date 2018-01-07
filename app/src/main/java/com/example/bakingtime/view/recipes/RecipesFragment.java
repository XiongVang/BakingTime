package com.example.bakingtime.view.recipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingtime.BakingTimeApp;
import com.example.bakingtime.R;
import com.example.bakingtime.repository.recipes.model.Recipe;
import com.example.bakingtime.view.recipesteps.RecipeStepsActivity;
import com.example.bakingtime.viewmodel.RecipesViewModel;
import com.example.bakingtime.viewmodel.uimodel.RecipeUiModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RecipesFragment extends Fragment {

    private static final String TAG = "RecipesFragment";

    // Dagger
    @Inject
    RecipesViewModel mRecipesViewModel;

    private Context mContext;

    // ButterKnife
    private Unbinder mUnbinder;
    @BindView(R.id.recipes_recycler_view)
    RecyclerView mRecipesRecyclerView;


    // RecyclerView
    private RecipesAdapter mRecipesAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        mContext = container.getContext();
        ((BakingTimeApp) mContext.getApplicationContext())
                .getViewModelComponent()
                .inject(this);

        // Butterknife view binding
        mUnbinder = ButterKnife.bind(this, view);

        // RecyclerView
        mRecipesAdapter = new RecipesAdapter();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecipesRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecipesRecyclerView.setAdapter(mRecipesAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar,menu);

    }

    @Override
    public void onResume() {

        super.onResume();

        mRecipesViewModel.getAllRecipes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<RecipeUiModel>>() {
                    @Override
                    public void onSuccess(List<RecipeUiModel> value) {
                        mRecipesAdapter.updateRecipesList(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });

        mRecipesAdapter.getRecipeClickNotifier()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String id) {
                        mRecipesViewModel.getRecipe(id)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new DisposableSingleObserver<Recipe>() {
                                    @Override
                                    public void onSuccess(Recipe recipe) {
                                        Intent intent = RecipeStepsActivity.newIntent(mContext,recipe.getId());
                                       startActivity(intent);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e(TAG, "onError: ", e);
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
