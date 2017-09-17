package com.notadeveloper.app.pat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;

/**
 * Created by Chirag on 04-Aug-17.
 */

public class ClubHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.iv) ImageView iv;
  @BindView(R.id.clubname) TextView clubname;
  @BindView(R.id.locicon) ImageView locicon;
  @BindView(R.id.loct) TextView loct;
  @BindView(R.id.rl) RelativeLayout rl;
  @BindView(R.id.cv) CardView cv;

  public ClubHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void setData(final Club c, final Context mContext) {
    clubname.setText(c.clubname);
    loct.setText(c.address3);
    if (c.getPicture() != null) {
      Log.e("CLubGlide", "onBindViewHolder: " + c.getPicture());
      GlideApp.with(mContext)
          .load(c.getPicture())
          .thumbnail(0.1f)
          .placeholder(R.drawable.club)
          .listener(GlidePalette.with(c.getPicture()).intoCallBack(new BitmapPalette.CallBack() {
            @Override public void onPaletteLoaded(@Nullable Palette palette) {
              if (palette != null) {
                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                Palette.Swatch muted = palette.getDarkMutedSwatch();
                if (muted != null) {

                  cv.setCardBackgroundColor(muted.getRgb());
                  clubname.setTextColor(muted.getBodyTextColor());
                  loct.setTextColor(muted.getBodyTextColor());
                  locicon.setColorFilter(muted.getBodyTextColor());
                } else if (vibrant != null) {
                  cv.setCardBackgroundColor(vibrant.getRgb());
                  clubname.setTextColor(vibrant.getBodyTextColor());
                  loct.setTextColor(vibrant.getBodyTextColor());
                  locicon.setColorFilter(vibrant.getBodyTextColor());
                }
              }
            }
          }))
          .into(iv);
    } else {
      Glide.with(mContext).load(R.drawable.club).into(iv);
    }
    cv.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(mContext, ClubDetailedActivity.class);
        intent.putExtra("Club_id", String.valueOf(c.getClubid()));
        mContext.startActivity(intent);
      }
    });
  }
}
