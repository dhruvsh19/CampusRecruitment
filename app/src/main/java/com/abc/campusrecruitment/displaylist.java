package com.abc.campusrecruitment;



import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.TextStyle;


public class displaylist extends AppCompatActivity {

    // Creating DatabaseReference.
    DatabaseReference databaseReference;

    // Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter ;

    // Creating Progress dialog
    ProgressDialog progressDialog;
    Button btnCreate;

    DatabaseReference mDatabaseReference;

    //list to store uploads data
    List<Apply> uploadList = new ArrayList<>();
    String f ;

    // Creating List of ImageUploadInfo class.
    List<Apply> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaylist);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnCreate = (Button)findViewById(R.id.download1);
        f= "  ";

        // Assign id to RecyclerView.
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView5);

        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.
        recyclerView.setLayoutManager(new LinearLayoutManager(displaylist.this));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(displaylist.this);

        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Images From Firebase.");

        // Showing progress dialog.
        progressDialog.show();

        // Setting up Firebase image upload folder path in databaseReference.
        // The path is already defined in MainActivity.
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String v1 = Objects.requireNonNull(bundle).getString("v1");


        databaseReference = FirebaseDatabase.getInstance().getReference("Register").child(v1);


        // Adding Add Value Event Listener to databaseReference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Apply apply = postSnapshot.getValue(Apply.class);

                    list.add(apply);
                }

                adapter = new RecyclerViewAdapter3(getApplicationContext(), list, v1);

                recyclerView.setAdapter(adapter);

                // Hiding the progress dialog.
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
                progressDialog.dismiss();

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mDatabaseReference = FirebaseDatabase.getInstance().getReference("Selection").child(v1);

                //retrieving upload data from firebase database
                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Apply apply = postSnapshot.getValue(Apply.class);
                            uploadList.add(apply);
                        }

                        String[] uploads = new String[uploadList.size()];

                        for (int i = 0; i < uploads.length; i++) {
                            uploads[i] = uploadList.get(i).getname();
                            f = f + "\n" + uploadList.get(i).getname();

                        }

                        createPdf(f,uploads);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), selection.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

    private void createPdf(String sometext, String[] uploads){


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {


                // create a new document
                PdfDocument document = new PdfDocument();
                // crate a page description
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
                // start a page
                PdfDocument.Page page = document.startPage(pageInfo);
                Canvas canvas = page.getCanvas();
                Paint paint = new Paint();

                for (int i = 0; i < uploads.length; i++) {
                    paint.setColor(Color.BLACK);
                    canvas.drawText(uploads[i], 80, 50 + (i * 20), paint);

                }

                //canvas.drawt
                // finish the page
                document.finishPage(page);
// draw text on the graphics object of the page
                // Create Page 2

                // write the document content
                String directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                String dur = String.valueOf(Environment.getExternalStorageDirectory().getAbsoluteFile());
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String targetPdf = directory_path +"test.pdf";
                File filePath = new File(targetPdf);
                try {
                    document.writeTo(new FileOutputStream(filePath));
                    Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Log.e("main", "error "+e.toString());
                    Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
                }
                // close the document
                document.close();


            } else {


                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

            }
        }
        else { //permission is automatically granted on sdk<23 upon installation


        }
    }

}