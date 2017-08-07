package com.notadeveloper.app.pat;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chirag on 04-Aug-17.
 */

public class ClubHolder extends RecyclerView.ViewHolder {
  final CardView cv;
  final ImageView iv;
  final TextView clubname;
  final TextView loct;

  public ClubHolder(View itemView) {
    super(itemView);

    cv = itemView.findViewById(R.id.cv);
    iv = itemView.findViewById(R.id.iv);
    clubname = itemView.findViewById(R.id.clubname);
    loct = itemView.findViewById(R.id.loct);
  }
}
