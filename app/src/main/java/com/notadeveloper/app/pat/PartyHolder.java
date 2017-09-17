package com.notadeveloper.app.pat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Admin on 3/21/2017.
 */

public class PartyHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.iv)
  ImageView iv;
  @BindView(R.id.month)
  TextView month;
  @BindView(R.id.date)
  TextView date;
  @BindView(R.id.day)
  TextView day;
  @BindView(R.id.line)
  RelativeLayout line;
  @BindView(R.id.pname)
  TextView pname;
  @BindView(R.id.locicon)
  ImageView locicon;
  @BindView(R.id.loct)
  TextView loct;
  @BindView(R.id.timicon)
  ImageView timicon;
  @BindView(R.id.nooftickets) TextView nooftickets;
  @BindView(R.id.ticicon) ImageView ticicon;
  @BindView(R.id.cv)
  CardView cv;
  party p;
  Context mContext;
  @BindView(R.id.timet)
  TextView timet;
  @BindView(R.id.price)
  TextView price;
  @BindView(R.id.daterel)
  RelativeLayout mDaterel;

  public PartyHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void setData(party p1, final Context mContext) {
    this.p = p1;
    this.mContext = mContext;

    GlideApp.with(mContext)
        .load(p.getPicture())
        .thumbnail(0.1f)
        .transition(withCrossFade())
        .placeholder(R.drawable.ima)
        .listener(GlidePalette.with(p.getPicture()).intoCallBack(new BitmapPalette.CallBack() {
          @Override public void onPaletteLoaded(@Nullable Palette palette) {
            if (palette != null) {
              Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
              Palette.Swatch muted = palette.getDarkMutedSwatch();
              if (muted != null) {

                cv.setCardBackgroundColor(muted.getRgb());
                pname.setTextColor(muted.getBodyTextColor());
                timet.setTextColor(muted.getBodyTextColor());
                price.setTextColor(muted.getBodyTextColor());
                date.setTextColor(muted.getBodyTextColor());
                month.setTextColor(muted.getBodyTextColor());
                day.setTextColor(muted.getBodyTextColor());
                loct.setTextColor(muted.getBodyTextColor());
                nooftickets.setTextColor(muted.getBodyTextColor());
                locicon.setColorFilter(muted.getBodyTextColor());
                timicon.setColorFilter(muted.getBodyTextColor());
                ticicon.setColorFilter(muted.getBodyTextColor());
                line.setBackgroundColor(muted.getBodyTextColor());
              } else if (vibrant != null) {
                cv.setCardBackgroundColor(vibrant.getRgb());
                pname.setTextColor(vibrant.getBodyTextColor());
                timet.setTextColor(vibrant.getBodyTextColor());
                price.setTextColor(vibrant.getBodyTextColor());
                date.setTextColor(vibrant.getBodyTextColor());
                month.setTextColor(vibrant.getBodyTextColor());
                day.setTextColor(vibrant.getBodyTextColor());
                loct.setTextColor(vibrant.getBodyTextColor());
                nooftickets.setTextColor(vibrant.getBodyTextColor());
                locicon.setColorFilter(vibrant.getBodyTextColor());
                timicon.setColorFilter(vibrant.getBodyTextColor());
                ticicon.setColorFilter(vibrant.getBodyTextColor());
                line.setBackgroundColor(vibrant.getBodyTextColor());
              }
            }
          }
        })
    ).into(iv);
    pname.setText(p.getTitle());
    loct.setText(p.getAddress3());
    timet.setText(p.getTime() + " to " + p.getTime1());
    nooftickets.setText(String.valueOf(p.getTickets()));
    String d = p.getDates();
    int as = Integer.parseInt(p.getPricestag());
    int b = Integer.parseInt(p.getPricecouple());
    if (as > b || as == b) {
      price.setText("₹ " + p.getPricecouple() + " onwards");
    } else {
      price.setText("₹ " + p.getPricestag() + " onwards");
    }

    String a[] = d.split("/");
    if (a[0].length() < 2) {
      a[0] = "0" + a[0];
    }
    date.setText(a[0]);
    if (a[1].equals("01")) {
      month.setText("JAN");
    } else if (a[1].equals("02")) {
      month.setText("FEB");
    } else if (a[1].equals("03")) {
      month.setText("MAR");
    } else if (a[1].equals("04")) {
      month.setText("APR");
    } else if (a[1].equals("05")) {
      month.setText("MAY");
    } else if (a[1].equals("06")) {
      month.setText("JUN");
    } else if (a[1].equals("07")) {
      month.setText("JLY");
    } else if (a[1].equals("08")) {
      month.setText("AUG");
    } else if (a[1].equals("09")) {
      month.setText("SEP");
    } else if (a[1].equals("10")) {
      month.setText("OCT");
    } else if (a[1].equals("11")) {
      month.setText("NOV");
    } else if (a[1].equals("12")) {
      month.setText("DEC");
    }

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date datee = null;
    try {
      datee = formatter.parse(d);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    long l = datee.getTime();

    Date date3 = new Date(l);

    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    String dayOfTheWeek = sdf.format(datee);

    if (dayOfTheWeek.equals("Monday")) {
      dayOfTheWeek = "MON";
    } else if (dayOfTheWeek.equals("Tuesday")) {
      dayOfTheWeek = "TUE";
    } else if (dayOfTheWeek.equals("Wednesday")) {
      dayOfTheWeek = "WED";
    } else if (dayOfTheWeek.equals("Thursday")) {
      dayOfTheWeek = "THU";
    } else if (dayOfTheWeek.equals("Friday")) {
      dayOfTheWeek = "FRI";
    } else if (dayOfTheWeek.equals("Saturday")) {
      dayOfTheWeek = "SAT";
    } else if (dayOfTheWeek.equals("Sunday")) {
      dayOfTheWeek = "SUN";
    }

    day.setText(dayOfTheWeek);

    cv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(mContext, detailedpartyactivity.class);
        intent.putExtra("party_id", String.valueOf(p.getPid()));
        mContext.startActivity(intent);
      }
    });
  }
}
