package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.charlesdrews.charlesdrewsdemoapp.PresenterCache;
import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.Injection;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnFiltersSelectedListener;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnPersonClickedListener;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnPersonSelectedListener;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.PeopleContract;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PeopleFragment extends Fragment implements PeopleContract.View,
        OnPersonClickedListener, OnFiltersSelectedListener {

    private static final String TAG = "PeopleFragment";
    private static final String FILTER_DIALOG_FRAGMENT_TAG = "filter_dialog_fragment_tag";

    private PeopleContract.Presenter mPresenter;
    private OnPersonSelectedListener mOnPersonSelectedListener;

    private ProgressBar mProgressBar;
    private TextView mDataNotAvailable;
    private ViewGroup mDataContainer;
    private PeopleRvAdapter mAdapter;
    private List<Person> mPeople;

    private ViewGroup mFilterBar;
    private TextView mActivePlatformFilter, mActiveLocationFilter;
    private SearchView mSearchView;
    private String mSearchQuery;

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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.people_list_fragment, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.people_list_progress_bar);
        mDataNotAvailable = (TextView) view.findViewById(R.id.people_list_data_not_available);
        mDataContainer = (ViewGroup) view.findViewById(R.id.people_list_data_container);

        mFilterBar = (ViewGroup) view.findViewById(R.id.filter_bar);
        mActivePlatformFilter = (TextView) view.findViewById(R.id.active_platform_filter);
        mActiveLocationFilter = (TextView) view.findViewById(R.id.active_location_filter);

        // Set up RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.people_list_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        RecyclerView.ItemDecoration itemDecoration = new PeopleRvItemDecoration(
                ContextCompat.getDrawable(getContext(), R.drawable.horizontal_divider),
                getResources().getDimension(R.dimen.horizontal_divider_margin));
        recyclerView.addItemDecoration(itemDecoration);

        mPeople = new ArrayList<>();
        mAdapter = new PeopleRvAdapter(mPeople, this);
        recyclerView.setAdapter(mAdapter);


        // Set up active filter boxes
        mActivePlatformFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivePlatformFilter.setText(null);
                mActivePlatformFilter.setVisibility(View.GONE);
                hideFilterBarIfBothFiltersInactive();
                mPresenter.setFilters(null, mActiveLocationFilter.getText().toString());
            }
        });

        mActiveLocationFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActiveLocationFilter.setText(null);
                mActiveLocationFilter.setVisibility(View.GONE);
                hideFilterBarIfBothFiltersInactive();
                mPresenter.setFilters(mActivePlatformFilter.getText().toString(), null);
            }
        });

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_people_list, menu);

        // Set up search view
        SearchManager searchManager = (SearchManager) getActivity()
                .getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.loadPeople(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.loadPeople(newText);
                return true;
            }
        });

        // SearchView not closing on its own... do it manually
        View closeBtn = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadPeople(null);
                mSearchView.setIconified(true);
            }
        });

        // If the presenter already supplied a searchQuery (e.g. after device rotation), apply it
        if (mSearchQuery != null) {
            showSearchQuery(mSearchQuery);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;

            case R.id.action_filter:
                mPresenter.startFilterProcess();
                return true;

            case R.id.action_reload:
                mPresenter.loadPeople(null);
                return true;

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void showFilterDialog(@NonNull ArrayList<String> platforms,
                                 @NonNull ArrayList<String> locations) {
        FilterDialogFragment dialogFragment = FilterDialogFragment
                .newInstance(platforms, locations,
                        mActivePlatformFilter.getText().toString(),
                        mActiveLocationFilter.getText().toString());

        dialogFragment.setOnFiltersSelectedListener(this);
        dialogFragment.show(getFragmentManager(), FILTER_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void showUnableToFilterMessage() {
        Toast.makeText(getContext(), R.string.filter_values_unavailable_message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPlatFormFilter(@NonNull String platformFilter) {
        mFilterBar.setVisibility(View.VISIBLE);
        mActivePlatformFilter.setVisibility(View.VISIBLE);
        mActivePlatformFilter.setText(platformFilter);
    }

    @Override
    public void hidePlatformFilter() {
        mActivePlatformFilter.setVisibility(View.GONE);
        mActivePlatformFilter.setText(null);
        hideFilterBarIfBothFiltersInactive();
    }

    @Override
    public void showLocationFilter(@NonNull String locationFilter) {
        mFilterBar.setVisibility(View.VISIBLE);
        mActiveLocationFilter.setVisibility(View.VISIBLE);
        mActiveLocationFilter.setText(locationFilter);
    }

    @Override
    public void hideLocationFilter() {
        mActiveLocationFilter.setVisibility(View.GONE);
        mActiveLocationFilter.setText(null);
        hideFilterBarIfBothFiltersInactive();
    }

    @Override
    public void showSearchQuery(@NonNull String searchQuery) {
        if (mSearchView != null) {
            mSearchView.setIconified(false);
            mSearchView.setQuery(searchQuery, false);
        } else {
            mSearchQuery = searchQuery;
        }
    }

    @Override
    public void launchPersonDetailUi(long personId) {
        if (mOnPersonSelectedListener != null) {
            mOnPersonSelectedListener.onPersonSelected(personId);
        }
    }

    @Override
    public void onPersonClicked(long personId) {
        mPresenter.handlePersonClicked(personId);
    }

    @Override
    public void onFiltersSelected(@Nullable String platformSelected,
                                  @Nullable String locationSelected) {
        mPresenter.setFilters(platformSelected, locationSelected);
    }

    public void setOnPersonSelectedListener(@NonNull OnPersonSelectedListener listener) {
        mOnPersonSelectedListener = listener;
    }

    public void hideFilterBarIfBothFiltersInactive() {
        if (mActivePlatformFilter.getVisibility() == View.GONE &&
                mActiveLocationFilter.getVisibility() == View.GONE) {
            mFilterBar.setVisibility(View.GONE);
        } else {
            mFilterBar.setVisibility(View.VISIBLE);
        }
    }
}
