package partyyy.com.notadeveloper.app.partyyy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SheeshaActivity extends AppCompatActivity {
    public static TextView total;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    ArrayList<shesha> list = new ArrayList<>();
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheesha);

        total = (TextView) findViewById(R.id.total);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        RecyclerView rv=(RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(this));


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        ref = mDatabase.child("Sheesha");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot)
            {

                for (DataSnapshot postSnapshot : datasnapshot.getChildren()) {
                    shesha s = postSnapshot.getValue(shesha.class);
                    if (!list.contains(s)&&list!=null)
                        list.add(s);

                }




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        SheeshaAdapter s=new SheeshaAdapter(list,SheeshaActivity.this);
        rv.setAdapter(s);
    }

}
