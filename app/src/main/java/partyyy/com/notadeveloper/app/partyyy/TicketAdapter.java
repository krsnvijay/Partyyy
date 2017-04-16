package partyyy.com.notadeveloper.app.partyyy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Chirag on 13-Apr-17.
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketHolder> {
    private List<party.BookedTickets> wList;
    private Context mContext;

    public TicketAdapter(List<party.BookedTickets> wList, Context context) {


        this.wList = wList;
        mContext = context;
    }
    @Override
    public TicketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.myticketcard, parent, false);

        return new TicketHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TicketHolder holder, int position) {
        final party.BookedTickets c =wList.get(holder.getAdapterPosition());

        holder.name.setText(c.getPname());
        holder.date.setText(c.getDate());
        holder.orderprice.setText("Amount paid: ₹"+c.getTprice());
        holder.stagprice.setText("(Stag): "+c.getStagno());
        holder.coupleprice.setText("(Couple): "+c.getCoupleno());
        holder.loct.setText(c.getLoct());
        holder.timet.setText(c.getTime());
        holder.cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailedTicketActivity.class);
                intent.putExtra("Ticket_id", String.valueOf(c.getTid()));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return wList.size();
    }
}
