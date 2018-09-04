package com.example.syan.gankclient.Fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.syan.gankclient.Models.Race;
import com.example.syan.gankclient.R;
import java.util.ArrayList;


public class NextEventAdapter extends RecyclerView.Adapter<NextEventAdapter.VH> {

    public static class VH extends RecyclerView.ViewHolder{

        public final TextView title;
        public final TextView content;
        public final TextView status;


        public VH(View v) {
            super(v);
            this.title = v.findViewById(R.id.tv_title);
            this.content = v.findViewById(R.id.tv_content);
            this.status = v.findViewById(R.id.tv_status);
        }
    }

    public ArrayList<Race> races;

    public NextEventAdapter(ArrayList<Race> races){
        this.races = races;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.title.setText(races.get(position).RaceNo);
        holder.content.setText(races.get(position).eventName);
        holder.status.setText(races.get(position).TimeToRace);
    }

    @Override
    public int getItemCount() {
        return races.size();
    }


}
