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
import com.example.studyapp2023.Domain.MarkDomain;
import com.example.studyapp2023.R;

import java.util.ArrayList;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.ViewHolder> {

    private ArrayList<MarkDomain> items;
    private Context context;

    public MarkAdapter(ArrayList<MarkDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list3, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarkDomain mark = items.get(position);
        holder.title.setText(mark.getTextMark());
        holder.des.setText(mark.getSub()); // Set the description
        holder.G.setText( mark.getG()+" ");

        // Load image using Glide if needed
        // Glide.with(context).load(mark.getPicPath()).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, des,G; // Changed G to title and Sub to des
        ImageView pic, background_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.MTextMid);
            des = itemView.findViewById(R.id.Mt1); // Changed ID
            pic = itemView.findViewById(R.id.picMark);
            G = itemView.findViewById(R.id.MGt1);

            background_img = itemView.findViewById(R.id.background_imgMark);
        }
    }
}
