package partyyy.com.notadeveloper.app.partyyy;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

import static partyyy.com.notadeveloper.app.partyyy.MainActivity.fromHtml;

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
    @BindView(R.id.noticket)
    TextView noticket;
    @BindView(R.id.stagprice)
    TextView stagprice;
    @BindView(R.id.coupleprice)
    TextView coupleprice;

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
                p = dataSnapshot.getValue(party.class);


                Glide.with(detailedpartyactivity.this).load(p.getPicture()).into(iv);

                String d = p.getDates();


                String a[] = d.split("/");

                if (a[0].length() < 2)
                    a[0] = "0" + a[0];

                date.setText(a[0]);
                if (a[1].equals("1"))
                    month.setText("JAN");
                else if (a[1].equals("2"))
                    month.setText("FEB");
                else if (a[1].equals("3"))
                    month.setText("MAR");
                else if (a[1].equals("4"))
                    month.setText("APR");
                else if (a[1].equals("5"))
                    month.setText("MAY");
                else if (a[1].equals("6"))
                    month.setText("JUN");
                else if (a[1].equals("7"))
                    month.setText("JLY");
                else if (a[1].equals("8"))
                    month.setText("AUG");
                else if (a[1].equals("9"))
                    month.setText("SEP");
                else if (a[1].equals("10"))
                    month.setText("OCT");
                else if (a[1].equals("11"))
                    month.setText("NOV");
                else if (a[1].equals("12"))
                    month.setText("DEC");


                pname.setText(p.getTitle());
                loct.setText(p.getAddress3());
                timet.setText(p.getTime() + " to " + p.getTime1());
                descrip.setText(p.getDescription());
                add1.setText(p.getAddress1());
                add2.setText(p.getAddress2());
                city.setText(p.getAddress3());
                pin.setText(p.getPincode());
                phone.setText(p.getNumber());
                email.setText(p.getEmail());
                noticket.setText(p.getTickets() + " Available");
                stagprice.setText("(Stag) ₹"+p.getPricestag());
                coupleprice.setText("(Couple) ₹"+p.getPricecouple());
                book.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final Dialog dialog;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            dialog = new Dialog(detailedpartyactivity.this, R.style.dialogthemez);
                        } else {
                            dialog = new Dialog(detailedpartyactivity.this);
                        }
                        dialog.setContentView(R.layout.ticketdialog);


                        Button dialogbutton = (Button) dialog.findViewById(R.id.okedittext);
                        final EditText edittextdial1 = (EditText) dialog.findViewById(R.id.address1);
                        final AutoCompleteTextView edittextdial2 = (AutoCompleteTextView) dialog.findViewById(R.id.address2);
                        final AutoCompleteTextView edittextdial3 = (AutoCompleteTextView) dialog.findViewById(R.id.address3);
                        final EditText edittextdial4 = (EditText) dialog.findViewById(R.id.pincode);
                        final TextInputLayout edittexttil1 = (TextInputLayout) dialog.findViewById(R.id.add1lt);
                        final TextInputLayout edittexttil2 = (TextInputLayout) dialog.findViewById(R.id.add2lt);
                        final TextInputLayout edittexttil3 = (TextInputLayout) dialog.findViewById(R.id.add3lt);
                        final TextInputLayout edittexttil4 = (TextInputLayout) dialog.findViewById(R.id.pinlt);
                        final ImageView iv1= (ImageView)dialog.findViewById(R.id.imageView2);
                        final ImageView iv2= (ImageView)dialog.findViewById(R.id.imageView3);

                        iv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edittextdial2.showDropDown();
                            }
                        });
                        iv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edittextdial3.showDropDown();
                            }
                        });
                        edittextdial1.setText("");


                        edittextdial2.setText("");


                        edittextdial3.setText("");

                        edittextdial4.setText("");

                        dialogbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {View focusView = null;
                                edittexttil4.setError(null);
                                edittexttil3.setError(null);
                                edittexttil2.setError(null);
                                edittexttil1.setError(null);
                                String pin = edittextdial4.getText().toString();
                                String a1 = edittextdial1.getText().toString();
                                String a2 = edittextdial2.getText().toString();
                                String a3 = edittextdial3.getText().toString();





                                    dialog.dismiss();


                            }
                        });

                        dialog.show();
                    }
                });
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
