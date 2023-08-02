package com.example.xisdapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<messages> list;

    public RecyclerViewAdapter (Context context, ArrayList<messages> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.messages_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.username.setText(list.get(position).getUserEmail());
        holder.message.setText(list.get(position).getThemessage());
        holder.dateTime.setText(list.get(position).getDatetime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message, username, dateTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.userEmail);
            message = itemView.findViewById(R.id.userMessage);
            dateTime = itemView.findViewById(R.id.messageDate);
        }
    }
}
