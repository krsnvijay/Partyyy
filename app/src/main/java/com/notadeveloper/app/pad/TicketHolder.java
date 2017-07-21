package com.notadeveloper.app.pad;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chirag on 13-Apr-17.
 */

public class TicketHolder extends RecyclerView.ViewHolder {

  final TextView name;
  final TextView orderprice;
  final TextView date;
  final TextView loct;
  final TextView timet;
  final TextView stagprice;
  final TextView coupleprice;
  final CardView cv1;

  public TicketHolder(View itemView) {
    super(itemView);

    name = (TextView) itemView.findViewById(R.id.name);
    orderprice = (TextView) itemView.findViewById(R.id.orderprice);
    date = (TextView) itemView.findViewById(R.id.date);
    loct = (TextView) itemView.findViewById(R.id.loct);
    timet = (TextView) itemView.findViewById(R.id.timet);
    stagprice = (TextView) itemView.findViewById(R.id.stagprice);
    coupleprice = (TextView) itemView.findViewById(R.id.coupleprice);
    cv1 = (CardView) itemView.findViewById(R.id.cv1);
  }
}
