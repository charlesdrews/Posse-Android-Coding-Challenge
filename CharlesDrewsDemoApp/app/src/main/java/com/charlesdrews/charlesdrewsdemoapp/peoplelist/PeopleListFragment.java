package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PeopleListFragment extends Fragment
        implements PeopleListContract.View<PeopleListContract.Presenter> {

    private PeopleListContract.Presenter mPresenter;
    private OnPersonSelectedListener mOnPersonSelectedListener;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private PeopleRecyclerViewAdapter mAdapter;
    private List<Person> mPeople;

    public interface OnPersonSelectedListener {
        void onPersonSelected(long personId);
    }

    public PeopleListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_list, container, false);

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
    public void setPresenter(PeopleListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPeople(List<Person> people) {
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
