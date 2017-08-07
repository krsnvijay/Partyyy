package com.notadeveloper.app.pat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.florent37.glidepalette.GlidePalette;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @ColorInt final int defaultColor =
        ContextCompat.getColor(mContext, android.R.color.background_light);
    @ColorInt final int textc = ColorUtils.setAlphaComponent(~defaultColor, 0xFF);

    GlideApp.with(mContext)
        .load(p.getPicture())
        .placeholder(R.drawable.ima).listener(GlidePalette.with(p.getPicture())
        .use(GlidePalette.Profile.VIBRANT)
        .intoBackground(cv, GlidePalette.Swatch.RGB)
        .intoTextColor(pname, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(timet, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(price, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(date, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(month, GlidePalette.Swatch.TITLE_TEXT_COLOR)
        .intoTextColor(day, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(loct, GlidePalette.Swatch.BODY_TEXT_COLOR)

        .use(GlidePalette.Profile.MUTED)
        .intoBackground(cv, GlidePalette.Swatch.RGB)
        .intoTextColor(pname, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(timet, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(price, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(date, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(month, GlidePalette.Swatch.TITLE_TEXT_COLOR)
        .intoTextColor(day, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(loct, GlidePalette.Swatch.BODY_TEXT_COLOR)

        .use(GlidePalette.Profile.MUTED_DARK)
        .intoBackground(cv, GlidePalette.Swatch.RGB)
        .intoTextColor(timet, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(day, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(loct, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(price, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(date, GlidePalette.Swatch.BODY_TEXT_COLOR)
        .intoTextColor(month, GlidePalette.Swatch.TITLE_TEXT_COLOR)

        .crossfade(true)
    ).into(iv);
    pname.setText(p.getTitle());
    loct.setText(p.getAddress3());
    timet.setText(p.getTime() + " to " + p.getTime1());
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
