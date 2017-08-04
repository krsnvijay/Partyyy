package com.notadeveloper.app.pad;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Chirag on 04-Aug-17.
 */

public class ClubAdapter extends RecyclerView.Adapter<ClubHolder> {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    ArrayList<Club> list;
    Context mContext;

    public ClubAdapter(ArrayList<Club> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public ClubHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.club_card, parent, false);

        return new ClubHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ClubHolder holder, int position)
    {
        final Club c = list.get(position);

        holder.clubname.setText(c.clubname);
        holder.loct.setText(c.address3);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
