package partyyy.com.notadeveloper.app.partyyy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class detailedpartyactivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.month)
    TextView month;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.line)
    RelativeLayout line;
    @BindView(R.id.pname)
    TextView pname;
    @BindView(R.id.loct)
    TextView loct;
    @BindView(R.id.timet)
    TextView timet;
    @BindView(R.id.descrip)
    TextView descrip;
    @BindView(R.id.addt)
    TextView addt;
    @BindView(R.id.add1)
    TextView add1;
    @BindView(R.id.add2)
    TextView add2;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.pin)
    TextView pin;
    @BindView(R.id.contactt)
    TextView contactt;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phoneicon)
    ImageView phoneicon;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.book)
    Button book;

    DatabaseReference ref;
    party p = new party();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedpartyactivity);
        ButterKnife.bind(this);

        String s = getIntent().getStringExtra("party_id");


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        ref = mDatabase.child("parties").child(s);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                p=dataSnapshot.getValue(party.class);


                Glide.with(detailedpartyactivity.this).load(p.getPicture()).into(iv);

                String d = p.getDates();



                String a[] = d.split("/");

                if (a[0].length()<2)
                    a[0]="0"+a[0];

                date.setText(a[0]);
                if(a[1].equals("1"))
                    month.setText("JAN");
                else if(a[1].equals("2"))
                    month.setText("FEB");
                else if(a[1].equals("3"))
                    month.setText("MAR");
                else if(a[1].equals("4"))
                    month.setText("APR");
                else if(a[1].equals("5"))
                    month.setText("MAY");
                else if(a[1].equals("6"))
                    month.setText("JUN");
                else if(a[1].equals("7"))
                    month.setText("JLY");
                else if(a[1].equals("8"))
                    month.setText("AUG");
                else if(a[1].equals("9"))
                    month.setText("SEP");
                else if(a[1].equals("10"))
                    month.setText("OCT");
                else if(a[1].equals("11"))
                    month.setText("NOV");
                else if(a[1].equals("12"))
                    month.setText("DEC");


                pname.setText(p.getTitle());
                loct.setText(p.getAddress3());
                timet.setText(p.getTime()+" to "+p.getTime1());
                descrip.setText(p.getDescription());
                add1.setText(p.getAddress1());
                add2.setText(p.getAddress2());
                city.setText(p.getAddress3());
                pin.setText(p.getPincode());
                phone.setText(p.getNumber());
                email.setText(p.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
