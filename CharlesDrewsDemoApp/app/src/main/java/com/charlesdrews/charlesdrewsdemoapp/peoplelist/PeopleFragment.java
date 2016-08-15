package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.charlesdrews.charlesdrewsdemoapp.PresenterCache;
import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.Injection;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnPersonSelectedListener;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.PeopleContract;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PeopleFragment extends Fragment implements PeopleContract.View {
    private static final String TAG = "PeopleFragment";

    private PeopleContract.Presenter mPresenter;
    private OnPersonSelectedListener mOnPersonSelectedListener;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private PeopleRecyclerViewAdapter mAdapter;
    private List<Person> mPeople;

    public PeopleFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = PresenterCache.getInstance().getPresenter(TAG,
                new PresenterCache.PresenterFactory<PeopleContract.Presenter>() {
                    @Override
                    public PeopleContract.Presenter createPresenter() {
                        return new PeoplePresenter(Injection.getPeopleRepository(
                                getContext().getApplicationContext()));
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.people_list_fragment, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.people_list_progress_bar);

        // Set up RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.people_list_recycler_view);

        //TODO - grid if in landscape but not on tablet
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        mPeople = new ArrayList<>();
        mAdapter = new PeopleRecyclerViewAdapter(mPeople);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPeople(@NonNull List<Person> people) {
        mPeople.clear();
        mPeople.addAll(people);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataNotAvailableIndicator() {
        Toast.makeText(getContext(), "Data not available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchPersonDetailUi(long personId) {
        //TODO
        if (mOnPersonSelectedListener != null) {
            mOnPersonSelectedListener.onPersonSelected(personId);
        }
    }

    public void setOnPersonSelectedListener(@NonNull OnPersonSelectedListener listener) {
        mOnPersonSelectedListener = listener;
    }
}
