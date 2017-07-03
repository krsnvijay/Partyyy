package partyyy.com.notadeveloper.app.partyyy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedTicketActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.qr)
    ImageView mQr;
    @BindView(R.id.month)
    TextView mMonth;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.day)
    TextView mDay;
    @BindView(R.id.line)
    RelativeLayout mLine;
    @BindView(R.id.pname)
    TextView mPname;
    @BindView(R.id.locicon)
    ImageView mLocicon;
    @BindView(R.id.loct)
    TextView mLoct;
    @BindView(R.id.timicon)
    ImageView mTimicon;
    @BindView(R.id.timet)
    TextView mTimet;
    @BindView(R.id.totprice)
    TextView mTotprice;
    @BindView(R.id.stagicon)
    ImageView mStagicon;
    @BindView(R.id.stagprice)
    TextView mStagprice;
    @BindView(R.id.coupleicon)
    ImageView mCoupleicon;
    @BindView(R.id.coupleprice)
    TextView mCoupleprice;
    @BindView(R.id.fgh)
    RelativeLayout mFgh;
    @BindView(R.id.rel)
    RelativeLayout mRel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detailed);
        ButterKnife.bind(this);
        String s = getIntent().getStringExtra("Ticket_id");
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid=mUser.getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(uid).child("mytickets").child(s).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                party.BookedTickets b=dataSnapshot.getValue(party.BookedTickets.class);
                Glide.with(DetailedTicketActivity.this).load(b.getQrcode()).into(mQr);
                Log.e("det", "onDataChange: "+b.getStagno() );
//                mStagprice.setText(b.getStagno());
//                mCoupleprice.setText(b.getCoupleno());
                mTotprice.setText("Amount paid: ₹" + String.valueOf(b.getTprice()));
                mLoct.setText(b.getLoct());
                mPname.setText(b.getPname());
                mTimet.setText(b.getTime());
                mStagprice.setText("(Stag): " + b.getStagno());
                mCoupleprice.setText("(Couple): " + b.getCoupleno());
                String s = b.getDate();
                String a[] = s.split("/");
                if (a[0].length() < 2)
                    a[0] = "0" + a[0];
                mDate.setText(a[0]);
                if (a[1].equals("01"))
                    mMonth.setText("JAN");
                else if (a[1].equals("02"))
                    mMonth.setText("FEB");
                else if (a[1].equals("03"))
                    mMonth.setText("MAR");
                else if (a[1].equals("04"))
                    mMonth.setText("APR");
                else if (a[1].equals("05"))
                    mMonth.setText("MAY");
                else if (a[1].equals("06"))
                    mMonth.setText("JUN");
                else if (a[1].equals("07"))
                    mMonth.setText("JLY");
                else if (a[1].equals("08"))
                    mMonth.setText("AUG");
                else if (a[1].equals("09"))
                    mMonth.setText("SEP");
                else if (a[1].equals("10"))
                    mMonth.setText("OCT");
                else if (a[1].equals("11"))
                    mMonth.setText("NOV");
                else if (a[1].equals("12"))
                    mMonth.setText("DEC");
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date datee = null;
                try {
                    datee = (Date)formatter.parse(b.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long l = datee.getTime();

                Date date3 = new Date(l);



                SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                String dayOfTheWeek = sdf.format(datee);

                if(dayOfTheWeek.equals("Monday"))
                    dayOfTheWeek = "MON";
                else if(dayOfTheWeek.equals("Tuesday"))
                    dayOfTheWeek = "TUE";
                else if(dayOfTheWeek.equals("Wednesday"))
                    dayOfTheWeek = "WED";
                else if(dayOfTheWeek.equals("Thursday"))
                    dayOfTheWeek = "THU";
                else if(dayOfTheWeek.equals("Friday"))
                    dayOfTheWeek = "FRI";
                else if(dayOfTheWeek.equals("Saturday"))
                    dayOfTheWeek = "SAT";
                else if(dayOfTheWeek.equals("Sunday"))
                    dayOfTheWeek = "SUN";


                mDay.setText(dayOfTheWeek);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
