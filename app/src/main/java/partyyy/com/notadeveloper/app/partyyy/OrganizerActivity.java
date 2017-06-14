package partyyy.com.notadeveloper.app.partyyy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizerActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private Button publicparty;
    private Button privateparty;
    private  users u=new users();
    String uid;
    LinearLayoutManager mLayoutManager;
    PartyAdapter mPartyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer1);
        ButterKnife.bind(this);
        loadadapter();

        publicparty = (Button)findViewById(R.id.publicparty);
        privateparty = (Button)findViewById(R.id.privateparty);

        publicparty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrganizerActivity.this, AddAParty.class);
                startActivity(i);
            }
        });
        privateparty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrganizerActivity.this, AddAParty.class);
                startActivity(i);
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setHasFixedSize(true);

    }
    void loadadapter()
    {
        final DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        uid=mUser.getUid();
        mDatabase.child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                u=dataSnapshot.getValue(users.class);
                ArrayList<String> ls;
                ls=u.getMyparties();
                if (ls!=null){
                    final ArrayList<String> finalLs = ls;
                    mDatabase.child("parties").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ArrayList<party> partls=new ArrayList<party>();
                            for(DataSnapshot ds:dataSnapshot.getChildren())
                            {   if (finalLs.contains(ds.getKey()))
                                    partls.add(ds.getValue(party.class));

                            }
                            mPartyAdapter=new PartyAdapter(partls,OrganizerActivity.this);
                            mRecyclerview.setAdapter(mPartyAdapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
