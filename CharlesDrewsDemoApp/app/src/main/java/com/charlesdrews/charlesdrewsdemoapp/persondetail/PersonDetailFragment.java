package com.charlesdrews.charlesdrewsdemoapp.persondetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charlesdrews.charlesdrewsdemoapp.ColorUtil;
import com.charlesdrews.charlesdrewsdemoapp.Injection;
import com.charlesdrews.charlesdrewsdemoapp.PresenterCache;
import com.charlesdrews.charlesdrewsdemoapp.R;

/**
 * Controls the UI for the Person Detail feature
 */
public class PersonDetailFragment extends Fragment implements PersonDetailContract.View {
    private static final String TAG = "PersonDetailFragment";
    private static final String TWO_PANE_MODE_KEY = "two_pane_mode_key";
    public static final String PERSON_ID_KEY = "person_id_key";

    private boolean mTwoPaneMode;
    private PersonDetailContract.Presenter mPresenter;

    private CollapsingToolbarLayout mToolbarLayout;
    private Toolbar mToolbar;
    private ViewGroup mDataContainer;
    private ProgressBar mProgressBar;
    private TextView mMessageToUser, mPlatform;
    private TextView mLocation, mLocationDetails, mPersonalDetails;

    public PersonDetailFragment() {}

    public static PersonDetailFragment newInstance(boolean twoPaneMode, @Nullable Long personId) {
        PersonDetailFragment fragment = new PersonDetailFragment();

        Bundle args = new Bundle();
        args.putBoolean(TWO_PANE_MODE_KEY, twoPaneMode);
        if (personId != null) {
            args.putLong(PERSON_ID_KEY, personId);
        }
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = PresenterCache.getInstance().getPresenter(TAG,
                new PresenterCache.PresenterFactory<PersonDetailContract.Presenter>() {
                    @Override
                    public PersonDetailContract.Presenter createPresenter() {
                        return new PersonDetailPresenter(Injection.getPeopleRepository(
                                getContext().getApplicationContext()));
                    }
                });

        long selectedPersonId = getArguments().getLong(PERSON_ID_KEY, -1);
        if (selectedPersonId != -1) {
            mPresenter.loadPerson(selectedPersonId);
        }

        mTwoPaneMode = getArguments().getBoolean(TWO_PANE_MODE_KEY);
        Log.d(TAG, "onCreate: two pane? " + mTwoPaneMode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_detail_fragment, container, false);

        mToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        mToolbar = (Toolbar) view.findViewById(R.id.person_detail_toolbar);
        mDataContainer = (ViewGroup) view.findViewById(R.id.person_detail_data_container);
        mProgressBar = (ProgressBar) view.findViewById(R.id.person_detail_progress_bar);
        mMessageToUser = (TextView) view.findViewById(R.id.person_detail_message);
        mPlatform = (TextView) view.findViewById(R.id.person_detail_platform);
        mLocation = (TextView) view.findViewById(R.id.person_detail_location);
        mLocationDetails = (TextView) view.findViewById(R.id.person_detail_location_details);
        mPersonalDetails = (TextView) view.findViewById(R.id.person_detail_personal_info);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mTwoPaneMode) {
            // Don't want a status bar showing up below PeopleActivity's toolbar
            mToolbarLayout.setStatusBarScrimResource(android.R.color.transparent);
        } else {
            // But do want the status bar to show if in PersonalDetailActivity
            mToolbarLayout.setStatusBarScrimResource(R.color.colorPrimaryDark);

            // Also set support action bar for menu
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        }
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
    public void showLoadingIndicator(boolean show) {
        mDataContainer.setVisibility(show ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mMessageToUser.setVisibility(View.GONE);
    }

    @Override
    public void showDataNotAvailableMessage() {
        updateVisibilityToShowMessage();
        mMessageToUser.setText(getString(R.string.data_not_available));
    }

    @Override
    public void showSelectAPersonMessage() {
        updateVisibilityToShowMessage();
        mMessageToUser.setText(R.string.please_select_person);
    }

    @Override
    public void showFavColor(String favColor) {
        int color = ColorUtil.ParseColorByName(favColor);
        if (color == -1) {
            color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        }
        mToolbarLayout.setBackgroundColor(color);
        mToolbarLayout.setExpandedTitleColor(ColorUtil.GetAppropriateTextColor(color));
    }

    @Override
    public void showName(String name) {
        updateVisibilityToDisplayData();
        mToolbar.setTitle(name);
    }

    @Override
    public void showPlatform(String platform) {
        updateVisibilityToDisplayData();
        mPlatform.setText(platform);
    }

    @Override
    public void showLocation(String location) {
        updateVisibilityToDisplayData();
        mLocation.setText(location);
    }

    @Override
    public void showLocationDetails(String locationDetails) {
        updateVisibilityToDisplayData();
        mLocationDetails.setVisibility(View.VISIBLE);
        mLocationDetails.setText(locationDetails);
    }

    @Override
    public void showPersonalDetails(String personalDetails) {
        updateVisibilityToDisplayData();
        mPersonalDetails.setVisibility(View.VISIBLE);
        mPersonalDetails.setText(personalDetails);
    }

    private void updateVisibilityToDisplayData() {
        mDataContainer.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mMessageToUser.setVisibility(View.GONE);
    }

    private void updateVisibilityToShowMessage() {
        mDataContainer.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mMessageToUser.setVisibility(View.VISIBLE);
    }

    public void loadPerson(long personId) {
        mPresenter.loadPerson(personId);
    }
}
