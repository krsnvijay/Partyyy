package com.notadeveloper.app.pad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    final String uid = mUser.getUid();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase.child("users")
        .child(uid)
        .child("mytickets")
        .child(s)
        .addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            party.BookedTickets b = dataSnapshot.getValue(party.BookedTickets.class);
            Glide.with(DetailedTicketActivity.this).load(b.getQrcode()).thumbnail(0.1f).into(mQr);
            Log.e("det", "onDataChange: " + b.getStagno());
            //                mStagprice.setText(b.getStagno());
            //                mCoupleprice.setText(b.getCoupleno());
            mTotprice.setText(String.valueOf(b.getTprice()));
            mLoct.setText(b.getLoct());
            mPname.setText(b.getPname());
            mTimet.setText(b.getTime());
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
        });
  }
}