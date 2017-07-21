package com.notadeveloper.app.pad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

public class PartyAdapter extends RecyclerView.Adapter<PartyHolder> {

  private List<party> myItems;
  private Context mContext;

  public PartyAdapter(List<party> myItems, Context context) {
    this.myItems = myItems;
    mContext = context;
  }

  @Override
  public PartyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PartyHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.partycard2, parent, false)); // TODO
  }

  @Override
  public int getItemCount() {
    return myItems.size();
  }

  @Override
  public void onBindViewHolder(PartyHolder holder, int position) {
    holder.setData(myItems.get(position), mContext);
  }
}
                                