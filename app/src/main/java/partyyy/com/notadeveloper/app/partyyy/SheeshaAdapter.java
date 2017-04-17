package partyyy.com.notadeveloper.app.partyyy;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by Chirag on 16-Apr-17.
 */

public class SheeshaAdapter extends RecyclerView.Adapter <SheeshaHolder> {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    ArrayList<shesha> list;
    Context mContext;

    public SheeshaAdapter(ArrayList<shesha> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public SheeshaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.sheeshacard, parent, false);

        return new SheeshaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SheeshaHolder holder, int position) {
        final shesha s=list.get(position);
        holder.flavor.setText(s.getFlavor());
        holder.price.setText("₹"+s.getPrice()+" ×");
        double f;

        holder.addicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.nopot.getText().toString()) >= 0) {
                    holder.nopot.setText(String.valueOf(Integer.parseInt(holder.nopot.getText().toString()) + 1));
                    double tamt = Integer.parseInt(holder.nopot.getText().toString()) * Double.parseDouble(s.getPrice());
                    holder.stprice.setText("₹" + String.format(Locale.UK, "%.2f", tamt));
                    double f = Double.parseDouble(Sheesha.total.getText().toString());
                    Sheesha.total.setText(String.valueOf(f + Double.parseDouble(s.getPrice())));
                }

            }
        });
        holder.minusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.nopot.getText().toString()) > 0) {
                    holder.nopot.setText(String.valueOf(Integer.parseInt(holder.nopot.getText().toString()) - 1));
                    double tamt = Integer.parseInt(holder.nopot.getText().toString()) * Double.parseDouble(s.getPrice());
                    holder.stprice.setText("₹" + String.format(Locale.UK, "%.2f", tamt));
                    double f = Double.parseDouble(Sheesha.total.getText().toString());
                    Sheesha.total.setText(String.valueOf(f - Double.parseDouble(s.getPrice())));
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
