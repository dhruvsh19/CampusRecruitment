package com.abc.campusrecruitment;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    public void gotoupdate(View view) {
        Intent intent = new Intent (this, Update.class);
        startActivity(intent);
    }
    public void gotoresume(View view) {
        Intent intent = new Intent (this, Profile.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item){

        Intent myIntent = new Intent(getApplicationContext(), Useractivity.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

}
