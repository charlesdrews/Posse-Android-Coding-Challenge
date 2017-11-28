package com.charlesdrews.charlesdrewsdemoapp.persondetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charlesdrews.charlesdrewsdemoapp.R;

public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail_activity);

        // Set up fragment
        if (findViewById(R.id.person_detail_fragment_container) != null) {
            PersonDetailFragment personDetailFragment;

            long selectedPersonId = getIntent().getLongExtra(PersonDetailFragment.PERSON_ID_KEY, -1);
            if (selectedPersonId == -1) {
                personDetailFragment = PersonDetailFragment.newInstance(false, null);
            } else {
                personDetailFragment = PersonDetailFragment.newInstance(false, selectedPersonId);
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.person_detail_fragment_container, personDetailFragment)
                    .commit();
        } else {
            // If the container is not present, should be in two-pane mode in the PeopleActivity
            finish();
        }
    }
}
