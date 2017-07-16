package partyyy.com.notadeveloper.app.partyyy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox cb;


    public SheeshaHolder(View itemView) {
        super(itemView);
        cb = (CheckBox)itemView.findViewById(R.id.check);
        ButterKnife.bind(this, itemView);


    }

}
