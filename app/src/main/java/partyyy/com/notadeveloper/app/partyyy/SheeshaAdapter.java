package partyyy.com.notadeveloper.app.partyyy;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
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
    List<String>llist= new ArrayList<>();

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
    public void onBindViewHolder(final SheeshaHolder holder, final int position) {
        final shesha s=list.get(position);

        holder.flavor.setText(s.getFlavour());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                TextView txtView = (TextView) ((Activity)mContext).findViewById(R.id.nopot);
                int ii = Integer.parseInt(txtView.getText().toString());
                if(holder.cb.isChecked()==true)
                {
                    int t;
                    if(llist == null)
                    {t=0;}
                    else
                        t=llist.size();
                    if(ii==0)
                    {Toast.makeText(mContext, "Select number of pots first!",
                            Toast.LENGTH_LONG).show();
                        holder.cb.setChecked(false);}
                    else if(t<(ii*3))
                       llist.add(list.get(position).flavour);
                    else
                    {
                        Toast.makeText(mContext, "Cannot select more than "+(ii*3)+" flavours!!",
                                Toast.LENGTH_LONG).show();
                        holder.cb.setChecked(false);
                    }
                }
                else
                {
                    int f = llist.size();
                    for(int i =0;i<f;i++)
                    {
                        if(list.get(position).flavour.equals(llist.get(i)))
                        {llist.remove(i);
                            break;}
                    }

                }
            }
        });
        double f;




    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
