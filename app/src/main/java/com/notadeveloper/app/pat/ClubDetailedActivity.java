package com.notadeveloper.app.pat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClubDetailedActivity extends AppCompatActivity {

  @BindView(R.id.iv)
  ImageView iv;
  @BindView(R.id.pname)
  TextView pname;
  @BindView(R.id.loct)
  TextView loct;
  @BindView(R.id.timet)
  TextView timet;
  @BindView(R.id.descrip)
  TextView descrip;
  @BindView(R.id.add1)
  TextView add1;
  @BindView(R.id.add2)
  TextView add2;
  @BindView(R.id.city)
  TextView city;
  @BindView(R.id.pin)
  TextView pin;
  @BindView(R.id.parking)
  TextView parking;
  @BindView(R.id.swimming)
  TextView swimming;
  @BindView(R.id.menus)
  ImageView menus;
  @BindView(R.id.contactt)
  TextView contactt;
  @BindView(R.id.email)
  TextView email;
  @BindView(R.id.phone)
  TextView phone;

  Club c = new Club();
  String s;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_club_detailed);
    ButterKnife.bind(this);
    s = getIntent().getStringExtra("Club_id");
    insertValues();
  }

  void insertValues() {
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    final String uid = mUser.getUid();
    final DatabaseReference mDatabase =
        FirebaseDatabase.getInstance().getReference().child("clubs").child(s);

    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        c = dataSnapshot.getValue(Club.class);

        pname.setText(c.getClubname());
        loct.setText(c.getAddress3());
        timet.setText(c.getTime() + " to " + c.getTime1());
        descrip.setText(c.getDescription());
        add1.setText(c.getAddress1());
        add2.setText(c.getAddress2());
        city.setText(c.getAddress3());
        pin.setText(c.getPin());
        if (c.getUtils().contains("Parking")) {
          parking.setText("Parking - YES");
        } else {
          parking.setText("Parking - NO");
        }
        if (c.getUtils().contains("Swimming pool")) {
          swimming.setText("Swimming pool - YES");
        } else {
          swimming.setText("Swimming pool - NO");
        }
        email.setText(c.getEmail());
        phone.setText(c.getNumber());
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }
}
