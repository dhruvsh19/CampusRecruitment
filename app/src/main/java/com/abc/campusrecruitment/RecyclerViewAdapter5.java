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

public class RecyclerViewAdapter5 extends RecyclerView.Adapter<RecyclerViewAdapter5.ViewHolder> {

    Context context;
    DatabaseReference databaseReference,data1;
    List<Ann> MainImageUploadInfoList;
    private FirebaseAuth auth;
    FirebaseUser user;
    String uid,name,phone,email,v1,userid;




    public RecyclerViewAdapter5(Context context, List<Ann> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;

        this.v1=v1;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewann, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {





        final Ann apply = MainImageUploadInfoList.get(position);

        holder.imageNameTextView.setText(apply.getName());






    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView imageNameTextView;



        public ViewHolder(View itemView) {
            super(itemView);


            imageNameTextView = (TextView) itemView.findViewById(R.id.viewann);


        }
    }
}