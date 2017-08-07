package com.notadeveloper.app.pat;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chirag on 20-Jul-17.
 */

public class SheeshaOrderHolder extends RecyclerView.ViewHolder {
  final TextView orderid;
  final TextView date;
  final TextView price;
  final TextView status;
  final TextView noofpots;
  final TextView deliverby;
  final CardView cv1;

  public SheeshaOrderHolder(View itemView) {
    super(itemView);
    cv1 = itemView.findViewById(R.id.cv1);
    orderid = itemView.findViewById(R.id.orderid);
    date = itemView.findViewById(R.id.date);
    price = itemView.findViewById(R.id.orderprice);
    status = itemView.findViewById(R.id.orderstatus);
    noofpots = itemView.findViewById(R.id.noofpots);
    deliverby = itemView.findViewById(R.id.deliverby);
  }
}
