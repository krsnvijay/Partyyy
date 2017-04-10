package partyyy.com.notadeveloper.app.partyyy;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.image.ImageType;

import java.io.File;
import java.util.Locale;

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
    users u= new users();
    DatabaseReference ref;
    party p = new party();
    @BindView(R.id.noticket)
    TextView noticket;
    @BindView(R.id.stagprice)
    TextView stagprice;
    @BindView(R.id.coupleprice)
    TextView coupleprice;
    private String photoUrl;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedpartyactivity);
        ButterKnife.bind(this);
        getUser();
        String s = getIntent().getStringExtra("party_id");
        storage = FirebaseStorage.getInstance();


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        ref = mDatabase.child("parties").child(s);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                p = dataSnapshot.getValue(party.class);


                Glide.with(detailedpartyactivity.this).load(p.getPicture()).into(iv);

                final String d = p.getDates();


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
                    TextView tictext;

                    TextView pricesta;
                    ImageView minusicon;
                    TextView notic;
                    ImageView addicon;
                    TextView stprice;

                    TextView pricecoup;
                    ImageView minuscoup;
                    TextView noticcoup;
                    ImageView addiconcoup;
                    TextView coupprice;
                    RelativeLayout rl;
                    TextView total;
                    Button book1;


                    @Override
                    public void onClick(View v) {

                        final Dialog dialog;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            dialog = new Dialog(detailedpartyactivity.this, R.style.dialogthemez);
                        } else {
                            dialog = new Dialog(detailedpartyactivity.this);
                        }
                        dialog.setContentView(R.layout.ticketdialog);
                        notic=(TextView)dialog.findViewById(R.id.notic);
                        noticcoup=(TextView)dialog.findViewById(R.id.noticcoup);
                        pricesta=(TextView)dialog.findViewById(R.id.pricesta);
                        pricesta.setText("₹"+p.getPricestag()+" x");
                        pricecoup=(TextView)dialog.findViewById(R.id.pricecoup);
                        pricecoup.setText("₹"+p.getPricecouple()+" x");
                        stprice=(TextView)dialog.findViewById(R.id.stprice);
                        coupprice=(TextView)dialog.findViewById(R.id.coupprice);
                        total=(TextView)dialog.findViewById(R.id.total);
                      book1=(Button)dialog.findViewById(R.id.book1);
                        addicon=(ImageView)dialog.findViewById(R.id.addicon);
                        minusicon=(ImageView)dialog.findViewById(R.id.minusicon);
                        addiconcoup=(ImageView)dialog.findViewById(R.id.addiconcoup);
                        minuscoup=(ImageView)dialog.findViewById(R.id.minuscoup);

                        addicon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Integer.parseInt(notic.getText().toString())>=0) {
                                    notic.setText(String.valueOf(Integer.parseInt(notic.getText().toString()) + 1));
                                    double tamt=Integer.parseInt(notic.getText().toString())*Double.parseDouble(p.getPricestag());
                                    stprice.setText(String.format(Locale.UK,"%.2f",tamt));
                                    total.setText(String.valueOf(Double.parseDouble(stprice.getText().toString())+Double.parseDouble(coupprice.getText().toString())));
                                }

                            }
                        });
                        minusicon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Integer.parseInt(notic.getText().toString())>0){
                                    notic.setText(String.valueOf(Integer.parseInt(notic.getText().toString()) - 1));
                                double tamt=Integer.parseInt(notic.getText().toString())*Double.parseDouble(p.getPricestag());
                                stprice.setText(String.format(Locale.UK,"%.2f",tamt));}
                                total.setText(String.valueOf(Double.parseDouble(stprice.getText().toString())+Double.parseDouble(coupprice.getText().toString())));

                            }
                        });
                        addiconcoup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Integer.parseInt(noticcoup.getText().toString())>=0){
                                    noticcoup.setText(String.valueOf(Integer.parseInt(noticcoup.getText().toString())+1));
                                double tamt=Integer.parseInt(noticcoup.getText().toString())*Double.parseDouble(p.getPricecouple());
                                coupprice.setText(String.format(Locale.UK,"%.2f",tamt));}
                                total.setText(String.valueOf(Double.parseDouble(stprice.getText().toString())+Double.parseDouble(coupprice.getText().toString())));

                            }
                        });
                        minuscoup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Integer.parseInt(noticcoup.getText().toString())>0){
                                    noticcoup.setText(String.valueOf(Integer.parseInt(noticcoup.getText().toString())-1));
                                double tamt=Integer.parseInt(noticcoup.getText().toString())*Double.parseDouble(p.getPricecouple());
                                coupprice.setText(String.format(Locale.UK,"%.2f",tamt));}
                                total.setText(String.valueOf(Double.parseDouble(stprice.getText().toString())+Double.parseDouble(coupprice.getText().toString())));

                            }
                        });








                        book1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Double.parseDouble(total.getText().toString())!=0)
                                {
                                    DatabaseReference offsetRef = FirebaseDatabase.getInstance().getReference(".info/serverTimeOffset");
                                    offsetRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            long offset = snapshot.getValue(Long.class);
                                           final long esttime = System.currentTimeMillis() + offset;
                                            storageRef = storage.getReferenceFromUrl("gs://partyyy-5e773.appspot.com").child("tickets").child(String.valueOf(p.getPid())).child(String.valueOf(esttime)+".jpg");
//                                            File f = QRCode.from(String.valueOf(esttime)).to(ImageType.JPG).file();

                                            Uri qr= Uri.fromFile(QRCode.from(String.valueOf(esttime)).to(ImageType.JPG).withSize(512,512).file());
                                            UploadTask uploadTask = storageRef.putFile(qr);
                                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                                    photoUrl = downloadUrl.toString();
                                                   DatabaseReference mDb=FirebaseDatabase.getInstance().getReference().child("users").child(u.getUid()).child("mytickets").child(String.valueOf(esttime));
                                                    DatabaseReference mDb2=FirebaseDatabase.getInstance().getReference().child("parties").child(String.valueOf(p.getPid())).child("ticketsBooked").child(String.valueOf(esttime));
                                                    party.BookedTickets b=new party.BookedTickets(String.valueOf(esttime),u.getUid(),u.getName(),String.valueOf(p.getPid()),Double.parseDouble(total.getText().toString()),Integer.parseInt(notic.getText().toString())+Integer.parseInt(noticcoup.getText().toString()),false,photoUrl);
                                                   mDb.setValue(b);
                                                    mDb2.setValue(b);
                                                    dialog.dismiss();
                                                    finish();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });



                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            System.err.println("Listener was cancelled");
                                        }
                                    });
                                }
                                else Toast.makeText(detailedpartyactivity.this, "Invalid Tickets",
                                        Toast.LENGTH_LONG).show();

                                View focusView = null;




                                Toast.makeText(detailedpartyactivity.this, "Payment and then verification(OTP)",
                                        Toast.LENGTH_LONG).show();

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
    void getUser()
    {
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid=mUser.getUid();
        final DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("users").child(uid);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                u=dataSnapshot.getValue(users.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
