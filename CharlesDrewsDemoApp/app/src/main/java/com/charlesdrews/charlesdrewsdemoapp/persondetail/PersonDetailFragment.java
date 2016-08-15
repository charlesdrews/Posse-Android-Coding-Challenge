package com.charlesdrews.charlesdrewsdemoapp.persondetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charlesdrews.charlesdrewsdemoapp.Injection;
import com.charlesdrews.charlesdrewsdemoapp.PresenterCache;
import com.charlesdrews.charlesdrewsdemoapp.R;

/**
 * Controls the UI for the Person Detail feature
 */
public class PersonDetailFragment extends Fragment implements PersonDetailContract.View {
    private static final String TAG = "PersonDetailFragment";
    public static final String PERSON_ID_KEY = "person_id_key";

    private PersonDetailContract.Presenter mPresenter;
    private ViewGroup mDataContainer;
    private ProgressBar mProgressBar;
    private TextView mMessageToUser, mName, mPlatform;
    private TextView mLocation, mLocationDetails, mPersonalDetails;

    public PersonDetailFragment() {}

    public static PersonDetailFragment newInstance(@Nullable Long personId) {
        PersonDetailFragment fragment = new PersonDetailFragment();

        Bundle args = new Bundle();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_detail_fragment, container, false);

        mDataContainer = (ViewGroup) view.findViewById(R.id.person_detail_data_container);
        mProgressBar = (ProgressBar) view.findViewById(R.id.person_detail_progress_bar);
        mMessageToUser = (TextView) view.findViewById(R.id.person_detail_message);
        mName = (TextView) view.findViewById(R.id.person_detail_name);
        mPlatform = (TextView) view.findViewById(R.id.person_detail_platform);
        mLocation = (TextView) view.findViewById(R.id.person_detail_location);
        mLocationDetails = (TextView) view.findViewById(R.id.person_detail_location_details);
        mPersonalDetails = (TextView) view.findViewById(R.id.person_detail_personal_info);

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
    public void showName(String name) {
        updateVisibilityToDisplayData();
        mName.setText(name);
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
