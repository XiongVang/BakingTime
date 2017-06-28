package com.example.bakingtime.view.recipes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingtime.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipesFragment extends Fragment {

    private static final String TAG = "RecipesFragment";

    private Context mContext;

    // ButterKnife
    @BindView(R.id.recipes_recycler_view)
    RecyclerView mRecipesRecyclerView;
    private Unbinder mUnbinder;

    // RecyclerView
    private RecipesAdapter mRecipesAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipes,container,false);

        mContext = container.getContext();

        // Butterknife view binding
        mUnbinder = ButterKnife.bind(this,view);

        // RecyclerView
        mRecipesAdapter = new RecipesAdapter();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecipesRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecipesRecyclerView.setAdapter(mRecipesAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
