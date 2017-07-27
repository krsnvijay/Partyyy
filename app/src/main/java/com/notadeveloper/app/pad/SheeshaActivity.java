package com.notadeveloper.app.pad;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.notadeveloper.app.pad.MainActivity.fromHtml;

public class SheeshaActivity extends AppCompatActivity {
  public static TextView total;

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  public Button book1;
  ArrayList<String> ll = new ArrayList<>();
  ArrayList<shesha> list = new ArrayList<>();
  DatabaseReference ref;
  private ImageView addicon;
  private ImageView minusicon;
  private TextView nopot;
  private boolean cancel;
  private long estimatedServerTimeMs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sheesha);

    total = (TextView) findViewById(R.id.total);
    book1 = (Button) findViewById(R.id.book1);
    addicon = (ImageView) findViewById(R.id.addicon);
    minusicon = (ImageView) findViewById(R.id.minusicon);
    nopot = (TextView) findViewById(R.id.nopot);

    addicon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String no = nopot.getText().toString();
        int noo = Integer.parseInt(no);
        noo = noo + 1;
        String f = Integer.toString(noo);
        nopot.setText(f);

        String no1 = total.getText().toString();
        int noo1 = Integer.parseInt(no1);
        noo1 = noo1 + 360;
        String f1 = Integer.toString(noo1);
        total.setText(f1);
      }
    });

    minusicon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String no = nopot.getText().toString();
        int noo = Integer.parseInt(no);
        if (noo != 0) {
          noo = noo - 1;
          String f = Integer.toString(noo);
          nopot.setText(f);
        }
        String no1 = total.getText().toString();
        int noo1 = Integer.parseInt(no1);

        if (noo1 != 0) {
          noo1 = noo1 - 360;
          String f1 = Integer.toString(noo1);
          total.setText(f1);
        }
      }
    });
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
    rv.setHasFixedSize(false);
    rv.setLayoutManager(new LinearLayoutManager(this));

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase.keepSynced(true);
    ref = mDatabase.child("Sheesha");
    ll = SheeshaAdapter.llist;

    book1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (Integer.parseInt(nopot.getText().toString()) == 0) {
          Toast.makeText(SheeshaActivity.this, "Cannot book 0 pots!",
              Toast.LENGTH_LONG).show();
        } else {
          final Dialog dialog;
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog = new Dialog(SheeshaActivity.this, R.style.dialogthemez);
          } else {
            dialog = new Dialog(SheeshaActivity.this);
          }

          dialog.setContentView(R.layout.addresschangedialog);
          dialog.setTitle(fromHtml("<font color='#c83737'>Edit Address</font>"));

          Button dialogbutton = (Button) dialog.findViewById(R.id.okedittext);
          final EditText edittextdial1 = (EditText) dialog.findViewById(R.id.adda1);
          final AutoCompleteTextView edittextdial2 =
              (AutoCompleteTextView) dialog.findViewById(R.id.adda2);
          final AutoCompleteTextView edittextdial3 =
              (AutoCompleteTextView) dialog.findViewById(R.id.pina);
          final TextView noop = (TextView) dialog.findViewById(R.id.noop);
          final Button editorder = (Button) dialog.findViewById(R.id.editorder);

          final TextInputLayout edittexttil1 = (TextInputLayout) dialog.findViewById(R.id.add1);
          final TextInputLayout edittexttil2 = (TextInputLayout) dialog.findViewById(R.id.add2);
          final TextInputLayout edittexttil3 = (TextInputLayout) dialog.findViewById(R.id.pin);
          final Spinner spinner = (Spinner) dialog.findViewById(R.id.deliverytype);

          noop.setText("No of pots: " + nopot.getText().toString());
          editorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dialog.dismiss();
            }
          });
          dialogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              cancel = false;
              View focusView = null;

              final String a1 = edittextdial1.getText().toString();
              final String a2 = edittextdial2.getText().toString();
              final String pin = edittextdial3.getText().toString();
              final String prescripti;

              if (isEditTextEmpty(pin) || pin.length() < 6) {
                edittexttil3.setError("Field Should be >6!");
                focusView = edittexttil3;
                cancel = true;
              } else {
                edittexttil3.setError(null);
              }
              if (isEditTextEmpty(a2)) {
                edittexttil2.setError("Field cannot be empty!");
                focusView = edittexttil2;
                cancel = true;
              } else {
                edittexttil2.setError(null);
              }
              if (isEditTextEmpty(a1)) {
                edittexttil1.setError("Field cannot be empty!");
                focusView = edittexttil1;
                cancel = true;
              } else {
                edittexttil1.setError(null);
              }
              if (spinner.getSelectedItem().toString().trim().equals("Choose One")) {
                Toast.makeText(SheeshaActivity.this, "Choose delivery type", Toast.LENGTH_SHORT)
                    .show();
                focusView = spinner;
                cancel = true;
              }

              if (cancel) {
                focusView.requestFocus();
              } else {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String spinnertext = spinner.getSelectedItem().toString().trim();
                final Calendar cal = Calendar.getInstance();

                dialog.dismiss();

                FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                final String uid = mUser.getUid();
                final DatabaseReference mDatabase2 =
                    FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                DatabaseReference offsetRef =
                    FirebaseDatabase.getInstance().getReference(".info/serverTimeOffset");
                offsetRef.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot snapshot) {
                    long offset = snapshot.getValue(Long.class);
                    estimatedServerTimeMs = System.currentTimeMillis() + offset;
                    cal.setTimeInMillis(estimatedServerTimeMs);
                    SimpleDateFormat formatter =
                        new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
                    String date = formatter.format(new Date(cal.getTimeInMillis()));
                    String status = "Pending";

                    MySheesha order =
                        new MySheesha(String.valueOf(estimatedServerTimeMs), date,
                            total.getText().toString(), status, ll,
                            spinner.getSelectedItem().toString(), a1, a2, pin, "8563214977",
                            "Name",
                            nopot.getText().toString());
                    mDatabase2.child("myorders")
                        .child(String.valueOf(estimatedServerTimeMs))
                        .setValue(order);

                    Toast.makeText(SheeshaActivity.this, "Order placed!! PAYMENT!!",
                        Toast.LENGTH_LONG).show();
                    finish();
                  }

                  @Override
                  public void onCancelled(DatabaseError error) {
                    System.err.println("Listener was cancelled");
                  }
                });
              }
            }
          });

          dialog.show();
        }
      }
    });

    ref.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot datasnapshot) {

        for (DataSnapshot postSnapshot : datasnapshot.getChildren()) {
          shesha s = postSnapshot.getValue(shesha.class);
          if (!list.contains(s) && list != null) {
            list.add(s);
          }
        }
        SheeshaAdapter s = new SheeshaAdapter(list, SheeshaActivity.this);
        rv.setAdapter(s);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  private boolean isEditTextEmpty(String mInput) {
    return mInput.length() == 0;
  }
}
