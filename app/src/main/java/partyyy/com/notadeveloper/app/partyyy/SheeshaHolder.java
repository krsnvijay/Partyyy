package partyyy.com.notadeveloper.app.partyyy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chirag on 16-Apr-17.
 */

public class SheeshaHolder extends RecyclerView.ViewHolder {

    TextView flavor;
    TextView price;


    public SheeshaHolder(View itemView)
    {
        super(itemView);
        flavor = (TextView)itemView.findViewById(R.id.flavor);
        price = (TextView)itemView.findViewById(R.id.price);

    }

}
