package com.abc.campusrecruitment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adminpage extends AppCompatActivity {
    public TextView username;
    ImageView add1,sel,ann1,rem;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;
    FirebaseUser user;
    public String uid,re,name,phone,email;
    static String abc="Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        auth = FirebaseAuth.getInstance();


        user = auth.getCurrentUser();


        add1 = findViewById(R.id.add);
        sel = findViewById(R.id.sel);
        rem = findViewById(R.id.rem);
        ann1 = findViewById(R.id.ann);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoadd();

            }
        });

        sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoselection();

            }
        });

        ann1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoann();
            }
        });

        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoremove();
            }
        });





    }
    public void gotoadd() {
        Intent intent = new Intent (this, Addcompany.class);
        startActivity(intent);
    }
    public void gotoview(View view) {
        Intent intent = new Intent (this, DisplayImagesActivity.class);
        startActivity(intent);
    }
    public void gotoprofile(View view) {
        Intent intent = new Intent (this, UpdateProfile.class);
        startActivity(intent);
    }
    public void gotoremove() {
        Intent intent = new Intent (this, remove.class);
        startActivity(intent);
    }
    public void gotoselection() {
        Intent intent = new Intent (this, selection.class);
        startActivity(intent);
    }
    public void gotoann() {
        Intent intent = new Intent (this, Announcement.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menusignout,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.signout:
                auth.signOut();
                startActivity(new Intent(Adminpage.this, LoginActivity.class));
                finish();
                break;


        }
        return true;
    }



}