package com.notadeveloper.app.pad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import static com.notadeveloper.app.pad.MainActivity.fromHtml;

/**
 * Created by Chirag on 20-Jul-17.
 */

public class SheeshaOrderAdapter extends RecyclerView.Adapter<SheeshaOrderHolder> {

  private List<MySheesha> wList;
  private Context mContext;

  public SheeshaOrderAdapter(List<MySheesha> wList, Context context) {

    this.wList = wList;
    mContext = context;
  }

  @Override
  public SheeshaOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    View itemView = LayoutInflater.from(mContext)
        .inflate(R.layout.sheeshaordercard, parent, false);

    return new SheeshaOrderHolder(itemView);
  }

  @Override
  public void onBindViewHolder(SheeshaOrderHolder holder, int position) {
    final MySheesha c = wList.get(holder.getAdapterPosition());
    holder.orderid.setText(c.getOrderid());
    holder.date.setText(c.getOrderdate());
    if (c.getAmount() != null) {
      holder.price.setText("₹ " + c.getAmount());
    } else {
      holder.price.setText("");
    }
    holder.noofpots.setText("Number of pots: " + c.noofpots);
    holder.deliverby.setText("Deliver By : " + c.getDeliverby());
    if (c.getStatus().equals("Delivered")) {
      holder.status.setText(
          fromHtml("Status: <font color='#228B22'>" + c.getStatus() + "</font>"));
    } else {
      holder.status.setText(
          fromHtml("Status: <font color='#FFC107'>" + c.getStatus() + "</font>"));
    }
  }

  @Override
  public int getItemCount() {
    return wList.size();
  }
}
