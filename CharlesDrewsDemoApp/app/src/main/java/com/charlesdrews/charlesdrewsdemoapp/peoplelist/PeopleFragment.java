package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.charlesdrews.charlesdrewsdemoapp.PresenterCache;
import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.Injection;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnPersonClickedListener;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnPersonSelectedListener;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.PeopleContract;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PeopleFragment extends Fragment implements PeopleContract.View,
        OnPersonClickedListener {

    private static final String TAG = "PeopleFragment";

    private PeopleContract.Presenter mPresenter;
    private OnPersonSelectedListener mOnPersonSelectedListener;

    private ProgressBar mProgressBar;
    private TextView mDataNotAvailable;
    private ViewGroup mDataContainer;
    private PeopleRecyclerViewAdapter mAdapter;
    private List<Person> mPeople;

    private List<String> mPlatformNames, mLocationNames;
    private ArrayAdapter<String> mPlatformSpinnerAdapter, mLocationSpinnerAdapter;

    public PeopleFragment() {}

    public static PeopleFragment newInstance(@Nullable Bundle args) {
        PeopleFragment fragment = new PeopleFragment();

        if (args != null) {
            fragment.setArguments(args);
        }

        return fragment;
    }

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
        mDataNotAvailable = (TextView) view.findViewById(R.id.people_list_data_not_available);
        mDataContainer = (ViewGroup) view.findViewById(R.id.people_list_data_container);

        // Set up RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.people_list_recycler_view);

        //TODO - grid if in landscape but not on tablet
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        mPeople = new ArrayList<>();
        mAdapter = new PeopleRecyclerViewAdapter(mPeople, this);
        recyclerView.setAdapter(mAdapter);

        // Set up filter spinners
        Spinner platformFilterSpinner = (Spinner) view.findViewById(R.id.platform_filter_spinner);
        mPlatformNames = new ArrayList<>();
        mPlatformNames.add(getString(R.string.platform_filter_initial_value));
        mPlatformSpinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mPlatformNames);
        mPlatformSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platformFilterSpinner.setAdapter(mPlatformSpinnerAdapter);

        Spinner locationFilterSpinner = (Spinner) view.findViewById(R.id.location_filter_spinner);
        mLocationNames = new ArrayList<>();
        mLocationNames.add(getString(R.string.location_filter_initial_value));
        mLocationSpinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mLocationNames);
        mLocationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationFilterSpinner.setAdapter(mLocationSpinnerAdapter);

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
        mDataNotAvailable.setVisibility(View.GONE);
    }

    @Override
    public void showPeople(@NonNull List<Person> people) {
        mDataContainer.setVisibility(View.VISIBLE);
        mPeople.clear();
        mPeople.addAll(people);
        mAdapter.notifyDataSetChanged();

        mProgressBar.setVisibility(View.GONE);
        mDataNotAvailable.setVisibility(View.GONE);
    }

    @Override
    public void showDataNotAvailableIndicator() {
        mProgressBar.setVisibility(View.GONE);
        mDataContainer.setVisibility(View.GONE);
        mDataNotAvailable.setVisibility(View.VISIBLE);
    }

    @Override
    public void launchPersonDetailUi(long personId) {
        if (mOnPersonSelectedListener != null) {
            mOnPersonSelectedListener.onPersonSelected(personId);
        }
    }

    public void setOnPersonSelectedListener(@NonNull OnPersonSelectedListener listener) {
        mOnPersonSelectedListener = listener;
    }

    @Override
    public void onPersonClicked(long personId) {
        mPresenter.handlePersonClicked(personId);
    }
}
