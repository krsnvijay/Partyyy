package partyyy.com.notadeveloper.app.partyyy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chirag on 20-Jul-17.
 */

public class SheeshaOrderHolder extends RecyclerView.ViewHolder
{
    final TextView orderid;
    final TextView date;
    final TextView price;
    final TextView status;
    final TextView noofpots;
    final TextView deliverby;
    final CardView cv1;
    public SheeshaOrderHolder(View itemView) {
        super(itemView);
        cv1 = (CardView) itemView.findViewById(R.id.cv1);
        orderid = (TextView)itemView.findViewById(R.id.orderid);
        date = (TextView)itemView.findViewById(R.id.date);
        price = (TextView)itemView.findViewById(R.id.orderprice);
        status= (TextView) itemView.findViewById(R.id.orderstatus);
        noofpots= (TextView) itemView.findViewById(R.id.noofpots);
        deliverby = (TextView) itemView.findViewById(R.id.deliverby);
    }
}
