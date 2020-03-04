package com.abc.campusrecruitment;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewUploadsActivity extends AppCompatActivity {

    //the listview
    ListView listView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference;
    DatabaseReference databaseReference;

    //list to store uploads data
    List<Upload> uploadList;
    String pname1,pcgpa1,pbranch1,pid1,pphone1,pemail1;
    String name,url;
    TextView pname,pcgpa,pbranch,pid,pphone,pemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String uid = Objects.requireNonNull(bundle).getString("userid");
        final String usernam = Objects.requireNonNull(bundle).getString("usernam");

        pname = findViewById(R.id.pname);
        pemail = findViewById(R.id.pemail);
        pbranch = findViewById(R.id.pbranch);
        pphone = findViewById(R.id.pphone);
        pid = findViewById(R.id.pid);
        pcgpa = findViewById(R.id.pcgpa);



        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pphone1 =  dataSnapshot.child(uid).child("phone").getValue(String.class);

                pname1 = dataSnapshot.child(uid).child("name").getValue(String.class);

                pemail1 = dataSnapshot.child(uid).child("email").getValue(String.class);

                pid1 =  dataSnapshot.child(uid).child("id").getValue(String.class);

                pcgpa1 = dataSnapshot.child(uid).child("cgpa").getValue(String.class);

                pbranch1 = dataSnapshot.child(uid).child("branch").getValue(String.class);

                pname.setText(pname1);
                pbranch.setText(pbranch1);
                pcgpa.setText(pcgpa1);
                pphone.setText(pphone1);
                pemail.setText(pemail1);
                pid.setText(pid1);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);


        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                Upload upload = uploadList.get(i);

                //Opening the upload file in browser using the upload url
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);
            }
        });


        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Upload");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(uid).child("name").getValue(String.class) != null){


                    name = dataSnapshot.child(uid).child("name").getValue(String.class);

                    url = dataSnapshot.child(uid).child("url").getValue(String.class);

                    Upload upload = new Upload(name , url);

                    uploadList.add(upload);

                    resume();

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), displaylist.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

    public void resume(){

        String[] uploads = new String[uploadList.size()];

        if(uploadList.size()!=0) {

            for (int i = 0; i < uploads.length; i++) {
                uploads[i] = uploadList.get(i).getName();
            }

            //displaying it to list
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
            listView.setAdapter(adapter);
        }
        else{

            Toast.makeText(this, "No resume Available "  ,  Toast.LENGTH_LONG).show();

        }




    }


}
