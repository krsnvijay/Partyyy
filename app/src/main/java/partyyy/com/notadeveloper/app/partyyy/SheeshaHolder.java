package partyyy.com.notadeveloper.app.partyyy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chirag on 16-Apr-17.
 */

public class SheeshaHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.flavor)
    TextView flavor;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.minusicon)
    ImageView minusicon;
    @BindView(R.id.nopot)
    TextView nopot;
    @BindView(R.id.addicon)
    ImageView addicon;

    @BindView(R.id.stprice)
    TextView stprice;
    @BindView(R.id.cv1)
    CardView cv1;


    public SheeshaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

}
