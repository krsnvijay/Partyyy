package com.notadeveloper.app.pad;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chirag on 04-Aug-17.
 */

public class ClubHolder extends RecyclerView.ViewHolder
{
    final CardView cv;
    final ImageView iv;
    final TextView clubname;
    final TextView loct;

    public ClubHolder(View itemView) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cv);
        iv = (ImageView)itemView.findViewById(R.id.iv);
        clubname = (TextView)itemView.findViewById(R.id.clubname);
        loct = (TextView)itemView.findViewById(R.id.loct);

    }
}
