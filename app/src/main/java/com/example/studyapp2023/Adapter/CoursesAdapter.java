package com.example.studyapp2023.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.studyapp2023.Domain.CoursesDomain;
import com.example.studyapp2023.R;

import java.util.ArrayList;
import java.util.Random;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.Viewholder> {
    ArrayList<CoursesDomain> items;
    Context context;

    public CoursesAdapter(ArrayList<CoursesDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CoursesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list2, parent, false);
        context = parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.Viewholder holder, int position) {
        CoursesDomain item = items.get(position);
        holder.title.setText(item.getTitle());

        holder.des.setText(item.getDescription());

        holder.date.setText(item.getDate());


        Random random = new Random();

        int randomNumber = random.nextInt(5) + 1;




        if(randomNumber==1){
            holder.background_img.setImageResource(R.drawable.bg_1);
            holder.pic.setImageResource(R.drawable.ic_1);



        } else if (randomNumber==2) {
            holder.background_img.setImageResource(R.drawable.bg_2);
            holder.pic.setImageResource(R.drawable.ic_1);



        }
        else if (randomNumber==3) {
            holder.background_img.setImageResource(R.drawable.bg_3);
            holder.pic.setImageResource(R.drawable.ic_3);



        }
        else if (randomNumber==4) {
            holder.background_img.setImageResource(R.drawable.bg_4);
            holder.pic.setImageResource(R.drawable.ic_4);



        }
        else if (randomNumber==5) {
            holder.background_img.setImageResource(R.drawable.bg_5);
            holder.pic.setImageResource(R.drawable.ic_5);



        }










    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title, date,des;

        ImageView pic, background_img;
        ConstraintLayout layout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt22);
            date = itemView.findViewById(R.id.titleTxt23);  // Make sure to add this ID in viewholder_list.xml
            pic = itemView.findViewById(R.id.pic365);
            des=itemView.findViewById(R.id.titleTxt2);
            background_img = itemView.findViewById(R.id.background_img);
            layout = itemView.findViewById(R.id.mail_layout);
        }
    }
}
