package com.abc.campusrecruitment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class RecyclerViewAdapter3 extends RecyclerView.Adapter<RecyclerViewAdapter3.ViewHolder> {

    Context context;
    DatabaseReference databaseReference,data1;
    List<Apply> MainImageUploadInfoList;
    private FirebaseAuth auth;
    FirebaseUser user;
    String uid,name,phone,email,v1,userid;




    public RecyclerViewAdapter3(Context context, List<Apply> TempList,String v1) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;

        this.v1=v1;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {





        final Apply apply = MainImageUploadInfoList.get(position);

        holder.imageNameTextView.setText(apply.getname());

        holder.imageNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid1;
                String usernam;
                userid1 = apply.getUserid();
                usernam = apply.getname();

                Intent i1 = new Intent (context, ViewUploadsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userid",userid1);
                bundle.putString("usernam",usernam);
                i1.putExtras(bundle);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);


            }
        });



        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Apply user = new Apply(
                        name= apply.getname(),
                email=apply.getemail(),
                phone=apply.getphone(),
                        userid=apply.getUserid()
                );

                databaseReference = FirebaseDatabase.getInstance().getReference("Selection").child(v1);
                String dId = databaseReference.push().getKey();
                databaseReference.child(dId).setValue(user);





            }
        });


    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView imageNameTextView;

        public Button apply;

        public ViewHolder(View itemView) {
            super(itemView);


            imageNameTextView = (TextView) itemView.findViewById(R.id.select);

            apply =(Button)  itemView.findViewById(R.id.sbutton);
        }
    }
}