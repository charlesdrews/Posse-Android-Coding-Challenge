package com.charlesdrews.charlesdrewsdemoapp.persondetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.charlesdrews.charlesdrewsdemoapp.R;

public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up fragment
        PersonDetailFragment personDetailFragment;

        long selectedPersonId = getIntent().getLongExtra(PersonDetailFragment.PERSON_ID_KEY, -1);
        if (selectedPersonId == -1) {
            personDetailFragment = PersonDetailFragment.newInstance(null);
        } else {
            personDetailFragment = PersonDetailFragment.newInstance(selectedPersonId);
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.person_detail_fragment_container, personDetailFragment)
                .commit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
