package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnPersonSelectedListener;
import com.charlesdrews.charlesdrewsdemoapp.persondetail.PersonDetailActivity;
import com.charlesdrews.charlesdrewsdemoapp.persondetail.PersonDetailFragment;

public class PeopleActivity extends AppCompatActivity
        implements OnPersonSelectedListener {

    private static final String TAG = "PeopleActivity";

    private boolean mTabletLandscapeMode = false;
    private PeopleFragment mPeopleFragment;
    private PersonDetailFragment mPersonDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_list_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up people list fragment
        mPeopleFragment = PeopleFragment.newInstance(null);
        mPeopleFragment.setOnPersonSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.people_fragment_container, mPeopleFragment)
                .commit();

        // Set up person detail fragment (if container present)
        FrameLayout detailFragmentContainer = (FrameLayout)
                findViewById(R.id.person_detail_fragment_container);

        mTabletLandscapeMode = (detailFragmentContainer != null);

        if (mTabletLandscapeMode) {
            mPersonDetailFragment = PersonDetailFragment.newInstance(null);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.person_detail_fragment_container, mPersonDetailFragment)
                    .commit();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPersonSelected(long personId) {
        Log.d(TAG, "onPersonSelected: activity is launching the detail UI for person id " + personId);
        if (mTabletLandscapeMode) {
            Log.d(TAG, "onPersonSelected: table configuration - personId is " + personId);
            mPersonDetailFragment.loadPerson(personId);
        } else {
            Log.d(TAG, "onPersonSelected: phone configuration - personId is " + personId);
            Intent intent = new Intent(PeopleActivity.this, PersonDetailActivity.class);
            intent.putExtra(PersonDetailFragment.PERSON_ID_KEY, personId);
            startActivity(intent);
        }
    }
}