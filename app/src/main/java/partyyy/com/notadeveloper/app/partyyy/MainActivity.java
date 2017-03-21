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
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.book)
    Button book;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partycard2);
        ButterKnife.bind(this);
        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);


        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                } else if (id == R.id.settings) {

                } else if (id == R.id.add) {
                    Intent i = new Intent(MainActivity.this, AddAParty.class);
                    startActivity(i);
                }

                return true;
            }
        });
        /*FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_car) {
                    Toast.makeText(MainActivity.this, "CAR",
                            Toast.LENGTH_LONG).show();
                } else if (menuItem.getItemId() == R.id.action_food) {
                    Toast.makeText(MainActivity.this, "FOOD",
                            Toast.LENGTH_LONG).show();
                } else if (menuItem.getItemId() == R.id.action_booze) {
                    Toast.makeText(MainActivity.this, "BOOZE",
                            Toast.LENGTH_LONG).show();
                } else if (menuItem.getItemId() == R.id.action_shisha) {
                    Toast.makeText(MainActivity.this, "SHISHA",
                            Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (abdt.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.book)
    public void onClick() {
        Intent i = new Intent(MainActivity.this, detailedpartyactivity.class);
        startActivity(i);
    }
}
