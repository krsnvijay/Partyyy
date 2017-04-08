package partyyy.com.notadeveloper.app.partyyy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

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
                                Palette.Swatch dominate = palette.getVibrantSwatch();
                                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                                Palette.Swatch light=palette.getLightVibrantSwatch();
                                Palette.Swatch muted=palette.getDarkMutedSwatch();
                                if (dominate!=null){
                                    book.setBackgroundColor(dominate.getBodyTextColor());

                                }
                                if (muted != null) {
                                    cv.setCardBackgroundColor(muted.getRgb());
                                    pname.setTextColor(muted.getTitleTextColor());
                                    book.setTextColor(muted.getRgb());
                                    locicon.setColorFilter(muted.getBodyTextColor());
                                    timicon.setColorFilter(muted.getBodyTextColor());
                                    loct.setTextColor(muted.getBodyTextColor());
                                    timet.setTextColor(muted.getBodyTextColor());
                                    price.setTextColor(muted.getBodyTextColor());
                                    date.setTextColor(muted.getBodyTextColor());
                                    month.setTextColor(muted.getBodyTextColor());

                                }
                            }
                        });
                    }
                });


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
        if (a[1].equals("1"))
            month.setText("JAN");
        else if (a[1].equals("2"))
            month.setText("FEB");
        else if (a[1].equals("3"))
            month.setText("MAR");
        else if (a[1].equals("4"))
            month.setText("APR");
        else if (a[1].equals("5"))
            month.setText("MAY");
        else if (a[1].equals("6"))
            month.setText("JUN");
        else if (a[1].equals("7"))
            month.setText("JLY");
        else if (a[1].equals("8"))
            month.setText("AUG");
        else if (a[1].equals("9"))
            month.setText("SEP");
        else if (a[1].equals("10"))
            month.setText("OCT");
        else if (a[1].equals("11"))
            month.setText("NOV");
        else if (a[1].equals("12"))
            month.setText("DEC");

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
