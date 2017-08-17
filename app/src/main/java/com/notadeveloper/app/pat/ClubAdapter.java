package com.notadeveloper.app.pat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
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
    if (c.getPicture() != null) {
      Log.e("CLubGlide", "onBindViewHolder: " + c.getPicture());
      GlideApp.with(mContext)
          .load(c.getPicture())
          .thumbnail(0.1f)
          .placeholder(R.drawable.club)
          .into(holder.iv);
    } else {
      Glide.with(mContext).load(R.drawable.club).into(holder.iv);
    }
    holder.cv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(mContext, ClubDetailedActivity.class);
        intent.putExtra("Club_id", String.valueOf(c.getClubid()));
        mContext.startActivity(intent);
      }
    });

  }

  @Override
  public int getItemCount() {
    return list.size();
  }
}
