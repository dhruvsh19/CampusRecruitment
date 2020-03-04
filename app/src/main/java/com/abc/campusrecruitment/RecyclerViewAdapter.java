package com.abc.campusrecruitment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;
import java.util.Objects;

/**
 * Created by AndroidJSon.com on 6/18/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    DatabaseReference databaseReference,data1;
    List<ImageUploadInfo> MainImageUploadInfoList;
    private FirebaseAuth auth;
    FirebaseUser user;
    String uid,name,phone,email,id;




    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();




        final ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);

        holder.imageNameTextView.setText(UploadInfo.getImageName());

        holder.eligibility.setText(UploadInfo.geteligibility());

        holder.description.setText(UploadInfo.getdescription());

        holder.location.setText(UploadInfo.getlocation());
        holder.website.setText(UploadInfo.getwebsite());

        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String applycom= UploadInfo.getImageName();
                String abc = "Users";
                databaseReference = FirebaseDatabase.getInstance().getReference(abc);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        phone =  dataSnapshot.child(uid).child("phone").getValue(String.class);

                        name = dataSnapshot.child(uid).child("name").getValue(String.class);

                         email = dataSnapshot.child(uid).child("email").getValue(String.class);

                        id =  dataSnapshot.child(uid).child("cgpa").getValue(String.class);
                        if(Double.valueOf(id) >= Double.valueOf(holder.eligibility.getText().toString())){
                            Apply user = new Apply(
                                    name,
                                    email,
                                    phone,
                                    uid
                            );
                            data1 = FirebaseDatabase.getInstance().getReference("Register").child(UploadInfo.getImageName());
                            String dId = databaseReference.push().getKey();
                            data1.child(uid).setValue(user);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });







            }
        });

        //Loading image from Glide library.
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView imageNameTextView;
        public TextView eligibility,description,location,website;
        public Button apply;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.card_view_image);

            imageNameTextView = (TextView) itemView.findViewById(R.id.name);

            eligibility = (TextView) itemView.findViewById(R.id.eligibility);

            location = (TextView) itemView.findViewById(R.id.location);

            description = (TextView) itemView.findViewById(R.id.description);

            website = (TextView) itemView.findViewById(R.id.website);
            apply =(Button)  itemView.findViewById(R.id.apply);
        }
    }
}