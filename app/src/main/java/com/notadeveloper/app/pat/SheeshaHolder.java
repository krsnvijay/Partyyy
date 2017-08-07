package com.notadeveloper.app.pat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
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
    cb = itemView.findViewById(R.id.check);
    ButterKnife.bind(this, itemView);
  }
}
