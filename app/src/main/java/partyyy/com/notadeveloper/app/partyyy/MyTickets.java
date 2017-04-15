package partyyy.com.notadeveloper.app.partyyy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTickets extends AppCompatActivity {

    @BindView(R.id.recycl)RecyclerView mRecyclerView;
    List<users.mytickets> lc=new ArrayList<>();
    TicketAdapter o;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);
        ButterKnife.bind(this);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setupadapter();
    }
    void setupadapter()
    {           final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        String uid=mUser.getUid();


        mDatabase.child("users").child(uid).child("mytickets").orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                for (DataSnapshot postSnapshot : datasnapshot.getChildren()) {
                    users.mytickets u = postSnapshot.getValue(users.mytickets.class);
                    if (u != null) {
                        if (!lc.contains(u))
                            lc.add(u);
                    }
                    else lc=new ArrayList<users.mytickets>();
                    o=new TicketAdapter(lc,MyTickets.this);

                    mRecyclerView.setAdapter(o);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}