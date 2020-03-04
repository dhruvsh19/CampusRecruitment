package com.abc.campusrecruitment;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Announcement extends AppCompatActivity {

    EditText announce;
    Button Ann,uploadlist;
    ListView list11;
    String ann;
    List<Ann> uploadList;
    DatabaseReference data1;
    RecyclerView recyclerView;
    List<Ann> list1 = new ArrayList<>();

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler1);
        uploadlist = findViewById(R.id.uploadlist);

        uploadlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoselection();
            }
        });

        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.
        recyclerView.setLayoutManager(new LinearLayoutManager(Announcement.this));

        announce = findViewById(R.id.announce);
        Ann = findViewById(R.id.ann);
        list11 = findViewById(R.id.list1);

        Ann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ann = announce.getText().toString();

                data1 = FirebaseDatabase.getInstance().getReference("Announcement");
                Ann an = new Ann(ann);

                String dId = data1.push().getKey();
                data1.child(dId).setValue(an);
            }
        });

        data1 = FirebaseDatabase.getInstance().getReference("Announcement");



        data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                   Ann imageUploadInfo = postSnapshot.getValue(Ann.class);

                    list1.add(imageUploadInfo);
                }

                adapter = new RecyclerViewAdapter4(getApplicationContext(), list1);

                recyclerView.setAdapter(adapter);

                // Hiding the progress dialog.

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.


            }
        });


    }
    public void gotoselection() {
        Intent intent = new Intent (this, uploadselectlist.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Adminpage.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }


    }

