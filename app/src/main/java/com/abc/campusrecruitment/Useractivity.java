package com.abc.campusrecruitment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Useractivity extends AppCompatActivity {
    ImageView app11,pro,an11,con,down;
    private FirebaseAuth auth;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);
        auth = FirebaseAuth.getInstance();


        user = auth.getCurrentUser();
        assert user != null;
        uid = user.getUid();


        app11 = findViewById(R.id.app);
        pro = findViewById(R.id.pro);
        an11 = findViewById(R.id.ann);
        con=findViewById(R.id.contact);
        down=findViewById(R.id.down);

        app11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoapp();

            }
        });

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    gotopro();
            }
        });

        an11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoprofile();
            }
        });

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotocon();
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotodown();
            }
        });
    }

    public void gotoapp() {
        Intent intent = new Intent (this, DisplayImagesActivity.class);
        startActivity(intent);
    }
    public void gotopro() {
        Intent intent = new Intent (this, UpdateProfile.class);
        startActivity(intent);
    }
    public void gotoprofile() {
        Intent intent = new Intent (this, Viewann.class);
        startActivity(intent);
    }
    public void gotocon() {
        Intent intent = new Intent (this, contact.class);
        startActivity(intent);
    }
    public void gotodown() {
        Intent intent = new Intent (this, viewselectionlist.class);
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
                startActivity(new Intent(Useractivity.this, LoginActivity.class));
                finish();
                break;


        }
        return true;
    }

}
