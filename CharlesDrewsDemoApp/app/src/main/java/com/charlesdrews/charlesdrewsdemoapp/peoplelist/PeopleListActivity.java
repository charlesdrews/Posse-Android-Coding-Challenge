package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.Injection;

public class PeopleListActivity extends AppCompatActivity
        implements PeopleListFragment.OnPersonSelectedListener {

    private static final String TAG = "PeopleListActivity";

    private boolean mTabletLandscapeMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up people list fragment
        PeopleListFragment listFragment = (PeopleListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.people_list_fragment);

        listFragment.setPresenter(new PeopleListPresenter(listFragment,
                Injection.getTaskRepository(getApplicationContext())));

        // Set up people detail fragment (if present)
        FrameLayout detailFragmentContainer = (FrameLayout)
                findViewById(R.id.detail_fragment_container);
        mTabletLandscapeMode = (detailFragmentContainer != null);

        if (mTabletLandscapeMode) {
            //TODO - plug detail fragment into container
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
        //TODO - launch DetailActivity or communicate with detail fragment
    }
}
