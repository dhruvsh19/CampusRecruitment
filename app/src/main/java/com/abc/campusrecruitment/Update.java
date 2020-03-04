package com.abc.campusrecruitment;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseUser user;
    String uid,name,phone,email,id,cgpa,branch;
    DatabaseReference databaseReference;
    EditText name1,phone1,cgpa1,branch1,id1;

    Button upda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        name1 = findViewById(R.id.edit_text_name1);
        phone1 = findViewById(R.id.edit_text_phone1);
        cgpa1 = findViewById(R.id.cgpa1);
        branch1 = findViewById(R.id.Branch1);
        id1 = findViewById(R.id.id1);

        upda = findViewById(R.id.up);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        assert user != null;
        uid = user.getUid();
        String abc = "Users";
        databaseReference = FirebaseDatabase.getInstance().getReference(abc);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                phone =  dataSnapshot.child(uid).child("phone").getValue(String.class);

                name = dataSnapshot.child(uid).child("name").getValue(String.class);

                email = dataSnapshot.child(uid).child("email").getValue(String.class);

                id =  dataSnapshot.child(uid).child("id").getValue(String.class);

                cgpa = dataSnapshot.child(uid).child("cgpa").getValue(String.class);

                branch = dataSnapshot.child(uid).child("branch").getValue(String.class);

                name1.setText(name);
                phone1.setText(phone);
                cgpa1.setText(cgpa);
                branch1.setText(branch);
                id1.setText(id);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        upda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mref = database.getReference();

                if (name.length() > 0 && cgpa.length() > 0 && branch.length() > 0 && phone.length() > 0 && id.length() > 0) {

                    mref.child("Users").child(uid).child("name").setValue(name1.getText().toString());
                    mref.child("Users").child(uid).child("cgpa").setValue(cgpa1.getText().toString());
                    mref.child("Users").child(uid).child("branch").setValue(branch1.getText().toString());
                    mref.child("Users").child(uid).child("phone").setValue(phone1.getText().toString());
                    mref.child("Users").child(uid).child("id").setValue(id1.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Fill all fields !!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), UpdateProfile.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

}
