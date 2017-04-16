package partyyy.com.notadeveloper.app.partyyy;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Chirag on 16-Apr-17.
 */

public class SheeshaAdapter extends RecyclerView.Adapter <SheeshaHolder> {
    ArrayList<shesha> list;
    Context mContext;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

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
    public void onBindViewHolder(SheeshaHolder holder, int position) {
        final shesha s=list.get(position);
        holder.flavor.setText(s.getFlavor());
        holder.price.setText("₹"+s.getPrice()+" ×");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
