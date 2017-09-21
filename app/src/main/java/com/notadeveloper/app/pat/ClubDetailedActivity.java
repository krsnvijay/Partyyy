package com.notadeveloper.app.pat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.text.Html.fromHtml;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ClubDetailedActivity extends AppCompatActivity {

  @BindView(R.id.pname) TextView pname;
  @BindView(R.id.loct) TextView loct;
  @BindView(R.id.timet) TextView timet;
  @BindView(R.id.descrip) TextView descrip;
  @BindView(R.id.add1) TextView add1;
  @BindView(R.id.add2) TextView add2;
  @BindView(R.id.city) TextView city;
  @BindView(R.id.pin) TextView pin;
  @BindView(R.id.parking) TextView parking;
  @BindView(R.id.swimming) TextView swimming;

  @BindView(R.id.contactt) TextView contactt;
  @BindView(R.id.email) TextView email;
  @BindView(R.id.phone) TextView phone;

  Club c = new Club();
  String s;
  int currentPage = 0;
  ArrayList<String> al = new ArrayList<String>();
  ArrayList<String> alm = new ArrayList<String>();
  @BindView(R.id.cv2) CardView cv2;
  private TextView[] dots;
  private MyViewPagerAdapter myViewPagerAdapter;
  private LinearLayout dotsLayout;
  private MyViewPagerAdapter myViewPagerAdapter1;
  private LinearLayout dotsLayout1;

  @Override protected void onCreate(Bundle savedInstanceState) {
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
    final ViewPager viewPager = findViewById(R.id.view_pager);
    final ViewPager viewPager1 = findViewById(R.id.view_pager1);

    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        c = dataSnapshot.getValue(Club.class);

        pname.setText(c.getClubname());
        loct.setText(c.getAddress3());
        timet.setText(c.getTime() + " to " + c.getTime1());
        descrip.setText(c.getDescription());
        add1.setText(c.getAddress1());
        add2.setText(c.getAddress2());
        city.setText(c.getAddress3());
        pin.setText(c.getPin());
        if (c.getUtils() != null) {
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
        }
        email.setText(c.getEmail());
        phone.setText(c.getNumber());

        if (c.getClubpicture() != null) {
          al.addAll(c.getClubpicture());
          myViewPagerAdapter = new MyViewPagerAdapter(al.size(), al);
          viewPager.setAdapter(myViewPagerAdapter);
          dotsLayout = findViewById(R.id.layoutDots);
          addBottomDots(0);

          ViewPager.OnPageChangeListener viewPagerPageChangeListener =
              new ViewPager.OnPageChangeListener() {

                @Override public void onPageSelected(int position) {
                  addBottomDots(position);
                  currentPage = position;
                }

                @Override public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override public void onPageScrollStateChanged(int arg0) {

                }
              };
          viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
          final Handler handler = new Handler();
          final Runnable Update = new Runnable() {
            public void run() {
              if (currentPage == al.size()) {
                currentPage = 0;
              }
              viewPager.setCurrentItem(currentPage++, true);
            }
          };

          Timer timer = new Timer(); // This will create a new Thread
          timer.schedule(new TimerTask() { // task to be scheduled

            @Override public void run() {
              handler.post(Update);
            }
          }, 500, 1500);
        }
        if (c.getMenupicture() != null) {
          alm.addAll(c.getMenupicture());
          myViewPagerAdapter1 = new MyViewPagerAdapter(alm.size(), alm);
          viewPager1.setAdapter(myViewPagerAdapter1);
          dotsLayout1 = findViewById(R.id.layoutDots1);
          addBottomDotsm(0);

          ViewPager.OnPageChangeListener viewPagerPageChangeListener =
              new ViewPager.OnPageChangeListener() {

                @Override public void onPageSelected(int position) {
                  addBottomDotsm(position);
                  currentPage = position;
                }

                @Override public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override public void onPageScrollStateChanged(int arg0) {

                }
              };
          viewPager1.addOnPageChangeListener(viewPagerPageChangeListener);
          final Handler handler = new Handler();
          final Runnable Update = new Runnable() {
            public void run() {
              if (currentPage == alm.size()) {
                currentPage = 0;
              }
              viewPager1.setCurrentItem(currentPage++, true);
            }
          };

          Timer timer = new Timer(); // This will create a new Thread
          timer.schedule(new TimerTask() { // task to be scheduled

            @Override public void run() {
              handler.post(Update);
            }
          }, 500, 1500);
        }
      }

      @Override public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  private void addBottomDots(int currentPage) {
    dots = new TextView[al.size()];

    int colorsActive = ContextCompat.getColor(this, R.color.background);
    int colorsInactive = ContextCompat.getColor(this, R.color.textColorSecondary);

    dotsLayout.removeAllViews();
    for (int i = 0; i < dots.length; i++) {
      dots[i] = new TextView(this);
      dots[i].setText(fromHtml("&#8226;"));
      dots[i].setTextSize(35);
      dots[i].setTextColor(colorsInactive);
      dotsLayout.addView(dots[i]);
    }

    if (dots.length > 0) dots[currentPage].setTextColor(colorsActive);
  }

  private void addBottomDotsm(int currentPage) {
    dots = new TextView[alm.size()];

    int colorsActive = ContextCompat.getColor(this, R.color.background);
    int colorsInactive = ContextCompat.getColor(this, R.color.textColorSecondary);

    dotsLayout1.removeAllViews();
    for (int i = 0; i < dots.length; i++) {
      dots[i] = new TextView(this);
      dots[i].setText(fromHtml("&#8226;"));
      dots[i].setTextSize(35);
      dots[i].setTextColor(colorsInactive);
      dotsLayout1.addView(dots[i]);
    }

    if (dots.length > 0) dots[currentPage].setTextColor(colorsActive);
  }

  @OnClick(R.id.cv2) public void onViewClicked() {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c.getLocation()));
    startActivity(intent);
  }

  public class MyViewPagerAdapter extends android.support.v4.view.PagerAdapter {
    int sizee;
    ArrayList<String> af;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public MyViewPagerAdapter(int size, ArrayList<String> af) {
      sizee = size;
      this.af = af;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
      layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      View view = layoutInflater.inflate(R.layout.pager_item, container, false);
      ImageView iv = view.findViewById(R.id.imageView);
      GlideApp.with(ClubDetailedActivity.this)
          .load(af.get(position))
          .thumbnail(0.1f)
          .fitCenter()
          .placeholder(R.drawable.ic_picture)
          .transition(withCrossFade())
          .into(iv);
      container.addView(view);

      return view;
    }

    @Override public int getCount() {
      return sizee;
    }

    @Override public boolean isViewFromObject(View view, Object obj) {
      return view == obj;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      View view = (View) object;
      container.removeView(view);
    }
  }
}
