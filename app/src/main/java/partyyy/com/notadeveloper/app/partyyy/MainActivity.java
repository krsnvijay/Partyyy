package partyyy.com.notadeveloper.app.partyyy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.address2)
    TextView address2;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private FirebaseRecyclerAdapter<party, PartyHolder> mAdapter;
    private List<party> pli = new ArrayList<>();
    String cat;
    DatabaseReference ref;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(mLayoutManager);




        dl.addDrawerListener(abdt);
        abdt.syncState();
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
        //myToolbar.setTitle(fromHtml("<font color='#ffffff'>Partyyy</font>"));
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view1);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        ref = mDatabase.child("parties");


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
                } else if (id == R.id.settings) {

                } else if (id == R.id.add) {
                    Intent i = new Intent(MainActivity.this, AddAParty.class);
                    startActivity(i);
                } else if (id == R.id.action_car) {
                    Toast.makeText(MainActivity.this, "CAR",
                            Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });


        mAdapter = new FirebaseRecyclerAdapter<party, PartyHolder>(party.class, R.layout.partycard2, PartyHolder.class, ref) {
            @Override
            public void populateViewHolder(PartyHolder ViewHolder, final party product, int position) {
                ViewHolder.setData(product, MainActivity.this);
            }
        };

        recyclerview.setAdapter(mAdapter);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (abdt.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.namesort) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.disptoolmenu, menu);
        return true;
    }


}
