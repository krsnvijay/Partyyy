package partyyy.com.notadeveloper.app.partyyy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.book)
    Button book;
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

         @ColorInt final int defaultColor= ContextCompat.getColor(mContext, android.R.color.background_light);
        @ColorInt final int textc= ColorUtils.setAlphaComponent(~defaultColor, 0xFF);
        Glide.with(mContext)
                .load(p.getPicture())
                .asBitmap()
                .placeholder(R.drawable.ima)
                .into(new BitmapImageViewTarget(iv) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        super.onResourceReady(bitmap, anim);
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                // Get the "vibrant" color swatch based on the bitmap
                                // Get the "vibrant" color swatch based on the bitmap
                                Palette.Swatch dominate = palette.getVibrantSwatch();
                                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                                Palette.Swatch lightn = palette.getLightVibrantSwatch();
                                Palette.Swatch light=palette.getLightMutedSwatch();
                                Palette.Swatch muted=palette.getDarkMutedSwatch();



                                if (muted != null) {

                                    cv.setCardBackgroundColor(muted.getRgb());
                                    pname.setTextColor(muted.getBodyTextColor());
                                    book.setBackgroundColor(muted.getBodyTextColor());
                                    book.setTextColor(muted.getRgb());
                                    locicon.setColorFilter(muted.getBodyTextColor());
                                    timicon.setColorFilter(muted.getBodyTextColor());
                                    loct.setTextColor(muted.getBodyTextColor());
                                    timet.setTextColor(muted.getBodyTextColor());
                                    price.setTextColor(muted.getBodyTextColor());
                                    date.setTextColor(muted.getBodyTextColor());
                                    month.setTextColor(muted.getBodyTextColor());

                                }

                                else  if (vibrant != null) {
                                    cv.setCardBackgroundColor(vibrant.getRgb());
                                    pname.setTextColor(vibrant.getBodyTextColor());
                                    book.setBackgroundColor(vibrant.getBodyTextColor());
                                    book.setTextColor(vibrant.getRgb());
                                    locicon.setColorFilter(vibrant.getBodyTextColor());
                                    timicon.setColorFilter(vibrant.getBodyTextColor());
                                    loct.setTextColor(vibrant.getBodyTextColor());
                                    timet.setTextColor(vibrant.getBodyTextColor());
                                    price.setTextColor(vibrant.getBodyTextColor());
                                    date.setTextColor(vibrant.getBodyTextColor());
                                    month.setTextColor(vibrant.getBodyTextColor());

                                }
//                                else  if (dominate != null) {
//                                    cv.setCardBackgroundColor(dominate.getRgb());
//                                    pname.setTextColor(dominate.getBodyTextColor());
//                                    book.setBackgroundColor(dominate.getBodyTextColor());
//                                    book.setTextColor(dominate.getRgb());
//                                    locicon.setColorFilter(dominate.getBodyTextColor());
//                                    timicon.setColorFilter(dominate.getBodyTextColor());
//                                    loct.setTextColor(dominate.getBodyTextColor());
//                                    timet.setTextColor(dominate.getBodyTextColor());
//                                    price.setTextColor(dominate.getBodyTextColor());
//                                    date.setTextColor(dominate.getBodyTextColor());
//                                    month.setTextColor(dominate.getBodyTextColor());
//
//                                }
                                else if (light != null) {
                                    cv.setCardBackgroundColor(light.getRgb());
                                    pname.setTextColor(light.getBodyTextColor());
                                    book.setBackgroundColor(light.getBodyTextColor());
                                    book.setTextColor(light.getRgb());
                                    locicon.setColorFilter(light.getBodyTextColor());
                                    timicon.setColorFilter(light.getBodyTextColor());
                                    loct.setTextColor(light.getBodyTextColor());
                                    timet.setTextColor(light.getBodyTextColor());
                                    price.setTextColor(light.getBodyTextColor());
                                    date.setTextColor(light.getBodyTextColor());
                                    month.setTextColor(light.getBodyTextColor());

                                }
                               else if (lightn != null) {
                                    cv.setCardBackgroundColor(lightn.getRgb());
                                    pname.setTextColor(lightn.getBodyTextColor());
                                    book.setBackgroundColor(lightn.getBodyTextColor());
                                    book.setTextColor(lightn.getRgb());
                                    locicon.setColorFilter(lightn.getBodyTextColor());
                                    timicon.setColorFilter(lightn.getBodyTextColor());
                                    loct.setTextColor(lightn.getBodyTextColor());
                                    timet.setTextColor(lightn.getBodyTextColor());
                                    price.setTextColor(lightn.getBodyTextColor());
                                    date.setTextColor(lightn.getBodyTextColor());
                                    month.setTextColor(lightn.getBodyTextColor());

                                }
                            }
                        });
                    }
                });
//        if (p.getPicture() != null) { // simulate an optional url from the data item
//            Glide.with(mContext)
//                    .fromString()
//                    .asBitmap()
//                    .transcode(new PaletteBitmapTranscoder(mContext), PaletteBitmap.class)
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                .load(p.getPicture())
//                    .into(new ImageViewTarget<PaletteBitmap>(iv) {
//                        @Override protected void setResource(PaletteBitmap resource) {
//                            super.view.setImageBitmap(resource.bitmap);
//                            int color = resource.palette.getDarkMutedColor(defaultColor);
//                            int color2 = resource.palette.getDarkMutedColor(textc);
//
//                            cv.setCardBackgroundColor(color);
//                            pname.setTextColor(color2);
//                            book.setTextColor(color);
//                            locicon.setColorFilter(color2);
//                            timicon.setColorFilter(color2);
//                            loct.setTextColor(color2);
//                            timet.setTextColor(color2);
//                            price.setTextColor(color2);
//                            date.setTextColor(color2);
//                            month.setTextColor(color2);
//                            book.setBackgroundColor(color2);
//                            Palette.Swatch dominate = resource.palette.getVibrantSwatch();
//                            Palette.Swatch vibrant = resource.palette.getDarkVibrantSwatch();
//                            Palette.Swatch light=resource.palette.getLightVibrantSwatch();
//                            Palette.Swatch muted=resource.palette.getDarkMutedSwatch();
//                            if (dominate!=null){
//                                book.setBackgroundColor(dominate.getBodyTextColor());
//
//                            }
//                            if (muted != null) {
//
//
//
//                            }
//                        }
//                    });
//        } else {
//            // clear when no image is shown, don't use holder.imageView.setImageDrawable(null) to do the same
//            Glide.clear(iv);
//
//        }

        // Glide.with(mContext).load(p.getPicture()).into(iv);
        pname.setText(p.getTitle());
        loct.setText(p.getAddress3());
        timet.setText(p.getTime() + " to " + p.getTime1());
        String d = p.getDates();
        int as = Integer.parseInt(p.getPricestag());
        int b = Integer.parseInt(p.getPricecouple());
        if (as > b || as == b)
            price.setText("₹ " + p.getPricecouple() + " onwards");
        else
            price.setText("₹ " + p.getPricestag() + " onwards");


        String a[] = d.split("/");
        if (a[0].length() < 2)
            a[0] = "0" + a[0];
        date.setText(a[0]);
        if (a[1].equals("01"))
            month.setText("JAN");
        else if (a[1].equals("02"))
            month.setText("FEB");
        else if (a[1].equals("03"))
            month.setText("MAR");
        else if (a[1].equals("04"))
            month.setText("APR");
        else if (a[1].equals("05"))
            month.setText("MAY");
        else if (a[1].equals("06"))
            month.setText("JUN");
        else if (a[1].equals("07"))
            month.setText("JLY");
        else if (a[1].equals("08"))
            month.setText("AUG");
        else if (a[1].equals("09"))
            month.setText("SEP");
        else if (a[1].equals("10"))
            month.setText("OCT");
        else if (a[1].equals("11"))
            month.setText("NOV");
        else if (a[1].equals("12"))
            month.setText("DEC");

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date datee = null;
        try {
            datee = (Date)formatter.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l = datee.getTime();

        Date date3 = new Date(l);



        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String dayOfTheWeek = sdf.format(datee);

        if(dayOfTheWeek.equals("Monday"))
            dayOfTheWeek = "MON";
        else if(dayOfTheWeek.equals("Tuesday"))
            dayOfTheWeek = "TUE";
        else if(dayOfTheWeek.equals("Wednesday"))
            dayOfTheWeek = "WED";
        else if(dayOfTheWeek.equals("Thursday"))
            dayOfTheWeek = "THU";
        else if(dayOfTheWeek.equals("Friday"))
            dayOfTheWeek = "FRI";
        else if(dayOfTheWeek.equals("Saturday"))
            dayOfTheWeek = "SAT";
        else if(dayOfTheWeek.equals("Sunday"))
            dayOfTheWeek = "SUN";


        day.setText(dayOfTheWeek);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, detailedpartyactivity.class);
                intent.putExtra("party_id", String.valueOf(p.getPid()));
                mContext.startActivity(intent);
            }
        });
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
