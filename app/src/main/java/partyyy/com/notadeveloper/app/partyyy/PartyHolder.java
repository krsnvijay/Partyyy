package partyyy.com.notadeveloper.app.partyyy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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


    public PartyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(party p, final Context mContext) {
        this.p = p;
        this.mContext = mContext;
        Glide.with(mContext).load(p.getPicture()).into(iv);
        pname.setText(p.getTitle());
        loct.setText(p.getAddress3());
        timet.setText(p.getTime()+" to "+p.getTime1());
        String d = p.getDates();
        String a[] = d.split("/");
        date.setText(a[0]);
        if(a[1].equals("1"))
            month.setText("JAN");
        else if(a[1].equals("2"))
            month.setText("FEB");
        else if(a[1].equals("3"))
            month.setText("MAR");
        else if(a[1].equals("4"))
            month.setText("APR");
        else if(a[1].equals("5"))
            month.setText("MAY");
        else if(a[1].equals("6"))
            month.setText("JUN");
        else if(a[1].equals("7"))
            month.setText("JLY");
        else if(a[1].equals("8"))
            month.setText("AUG");
        else if(a[1].equals("9"))
            month.setText("SEP");
        else if(a[1].equals("10"))
            month.setText("OCT");
        else if(a[1].equals("11"))
            month.setText("NOV");
        else if(a[1].equals("12"))
            month.setText("DEC");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, detailedpartyactivity.class);
                mContext.startActivity(i);
            }
        });


    }


}
