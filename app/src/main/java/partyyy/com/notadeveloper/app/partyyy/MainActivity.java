package partyyy.com.notadeveloper.app.partyyy;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.toggle;


public class MainActivity extends AppCompatActivity implements PartyFragment.OnFragmentInteractionListener, ClubFragment.OnFragmentInteractionListener {

    users u= new users();
    private Menu menu;
    private FirebaseRecyclerAdapter<party, PartyHolder> mAdapter;


    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);





        dl.addDrawerListener(abdt);
        abdt.syncState();
     Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Parties"));
        tabLayout.addTab(tabLayout.newTab().setText("Clubs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.logout) {


                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
                    builder.setMessage("Are You Sure?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AuthUI.getInstance()
                                    .signOut(MainActivity.this)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        public void onComplete(@NonNull Task<Void> task) {
                                            // user is now signed out
                                            Intent i = new Intent(MainActivity.this, Login
                                                    .class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);
                                            finish();
                                        }
                                    });
                            Toast.makeText(MainActivity.this, "Logout",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                } else if (id == R.id.tac) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
                    builder.setTitle("Terms and conditions");
                    builder.setMessage("T & C");
                    builder.setPositiveButton("OK", null);
                    // builder.setNegativeButton("Cancel", null);
                    builder.show();
                } else if (id == R.id.settings)
                {

                }
                else if (id == R.id.add) {
                    boolean p;
                    if (u.isorganizer!=null)
                    p = u.getIsorganizer();
                    else p=false;
                    if(p)
                    {
                        Intent i = new Intent(MainActivity.this, OrganizerActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i = new Intent(MainActivity.this, BecomeOrganiser.class);
                        startActivity(i);
                    }
                }
//                else if (id == R.id.action_car)
//                {
//                    Toast.makeText(MainActivity.this, "CAR",
//                            Toast.LENGTH_LONG).show();
//                }
//                else if (id == R.id.mytick)
//                {
//                    Intent i = new Intent(MainActivity.this, MyTickets.class);
//                    startActivity(i);
//                }
                else if (id == R.id.action_sheesha)
                {
                    Intent i = new Intent(MainActivity.this, SheeshaActivity.class);
                    startActivity(i);
                }

                return true;
            }
        });




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (abdt.onOptionsItemSelected(item)) {
            return true;

        }

//        switch (item.getItemId()) {
//            case R.id.datehigh:
//                setmAdapter(1);
//                return true;
//
//            case R.id.datelow:
//                setmAdapter(2);
//                return true;
//            case R.id.costhigh:
//                setmAdapter(3);
//                return true;
//            case R.id.costlow:
//                setmAdapter(4);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }


        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu=menu;

        getMenuInflater().inflate(R.menu.disptoolmenu, menu);
        return true;
    }
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
//    void setmAdapter(int x)
//
//    {
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        Query q=mDatabase.child("parties");
//        LinearLayoutManager LayoutManager = new LinearLayoutManager(MainActivity.this);
//
//        if (x==1)
//        {q=mDatabase.child("parties").orderByChild("pid");
//         LayoutManager.setStackFromEnd(true);
//            LayoutManager.setReverseLayout(true);}
//     else   if (x==2)
//        {
//            q=mDatabase.child("parties").orderByChild("pid");
//        }
//        else if (x==3)
//        {
//            q=mDatabase.child("parties").orderByChild("tickets");
//            LayoutManager.setStackFromEnd(true);
//            LayoutManager.setReverseLayout(true);
//        }
//        else if (x==4)
//        {
//            q=mDatabase.child("parties").orderByChild("tickets");
//        }
//        mAdapter = new FirebaseRecyclerAdapter<party, PartyHolder>(party.class, R.layout.partycard2, PartyHolder.class, q) {
//            @Override
//            public void populateViewHolder(PartyHolder ViewHolder, final party product, int position) {
//                ViewHolder.setData(product, MainActivity.this);
//            }
//        };
//        recyclerview.setLayoutManager(LayoutManager);
//        recyclerview.setAdapter(mAdapter);
//    }
//
    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
        builder.setMessage("Are You Sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
        //  super.onBackPressed();
    }
    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
