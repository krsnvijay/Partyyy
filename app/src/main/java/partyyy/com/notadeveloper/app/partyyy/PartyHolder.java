package partyyy.com.notadeveloper.app.partyyy;

import android.content.Context;
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

    public void setData(party p, Context mContext) {
        this.p = p;
        this.mContext = mContext;
        Glide.with(mContext).load(p.getPicture()).into(iv);
        pname.setText(p.getTitle());
        loct.setText(p.getAddress3());
        timet.setText(p.getTime()+" to "+p.getTime1());
        String d = p.getDates();
        String a[] = new String[3];
        date.setText("17");


    }


}
