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

public class PeopleActivity extends AppCompatActivity implements OnPersonSelectedListener {

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
                .replace(R.id.people_fragment_container, mPeopleFragment)
                .commit();

        // Set up person detail fragment (if container present)
        FrameLayout detailFragmentContainer = (FrameLayout)
                findViewById(R.id.person_detail_fragment_container);

        mTabletLandscapeMode = (detailFragmentContainer != null);

        if (mTabletLandscapeMode) {
            mPersonDetailFragment = PersonDetailFragment.newInstance(null);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.person_detail_fragment_container, mPersonDetailFragment)
                    .commit();
        }
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
        if (mTabletLandscapeMode) {
            mPersonDetailFragment.loadPerson(personId);
        } else {
            Intent intent = new Intent(PeopleActivity.this, PersonDetailActivity.class);
            intent.putExtra(PersonDetailFragment.PERSON_ID_KEY, personId);
            startActivity(intent);
        }
    }
}
