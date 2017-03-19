package partyyy.com.notadeveloper.app.partyyy;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;

import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;

public class AddAParty extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    @BindView(R.id.picture)
    ImageButton mProfile;
    @BindView(R.id.title)
    AutoCompleteTextView mTitle;
    @BindView(R.id.title1)
    TextInputLayout mTitle1;
    @BindView(R.id.email)
    AutoCompleteTextView mEmail;
    @BindView(R.id.emaila)
    TextInputLayout mEmaila;
    @BindView(R.id.number)
    AutoCompleteTextView mNumber;
    @BindView(R.id.number1)
    TextInputLayout mNumber1;
    @BindView(R.id.tickets)
    AutoCompleteTextView mTickets;
    @BindView(R.id.tickets1)
    TextInputLayout mTickets1;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.confirm)
    Button mConfirm;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.activity_signup)
    LinearLayout activitySignup;
    @BindView(R.id.time1)
    TextView time1;
    @BindView(R.id.location)
    Button location;
    @BindView(R.id.loca)
    TextView loca;
    @BindView(R.id.address1)
    EditText address1;
    @BindView(R.id.add1lt)
    TextInputLayout add1lt;
    @BindView(R.id.address2)
    AutoCompleteTextView address2;
    @BindView(R.id.add2lt)
    TextInputLayout add2lt;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.address3)
    AutoCompleteTextView address3;
    @BindView(R.id.add3lt)
    TextInputLayout add3lt;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.pincode)
    EditText pincode;
    @BindView(R.id.pinlt)
    TextInputLayout pinlt;

    private String photoUrl;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private DatabaseReference ref;
    private TextView dates;
    static String datetxt;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aparty);
        ButterKnife.bind(this);

        dates = (TextView) findViewById(R.id.dates);


        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");

            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker1");
            }
        });
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker2");
            }
        });

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://partyyy-5e773.appspot.com");
        ref = FirebaseDatabase.getInstance().getReference();


        new CroperinoConfig("IMG_" + System.currentTimeMillis() + ".jpg", "/MikeLau/Pictures", Environment.getExternalStorageDirectory().getPath());
        CroperinoFileUtil.setupDirectory(AddAParty.this);

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagesRef = storageRef.child("images").child("picture");
                if (CroperinoFileUtil.verifyStoragePermissions(AddAParty.this))
                    prepareChooser();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int a = view.getId();
        if (a == R.id.time1) {
            time1.setText(hourOfDay + "/" + minute);
        } else
            time.setText(hourOfDay + "/" + minute);
    }

    @OnClick(R.id.location)
    public void onClickloc() {

    }

    @OnClick(R.id.confirm)
    public void onClickcon() {

        boolean cancel = false;
        View focusView = null;


        String a = mTitle.getText().toString();
        String b = dates.getText().toString();
        String c = time.getText().toString();
        String d = time1.getText().toString();
        String e = mEmail.getText().toString();
        String f = mNumber.getText().toString();
        String g = address1.getText().toString();
        String h = address2.getText().toString();
        String i = address3.getText().toString();
        String j = pincode.getText().toString();
        String k = mTickets.getText().toString();
        String l = mText.getText().toString();

        if (isEmpty(a))
        {
            mTitle1.setError("Field cannot be empty");
            focusView = mTitle1;
            cancel = true;
        }
        else mTitle1.setError(null);
        if (b.equals("DD/MM/YYYY"))
        {

            Snackbar.make(findViewById(android.R.id.content), "Please fill date first", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(getResources().getColor(R.color.mdtp_red))
                    .show();
            focusView = null;
            cancel = true;
        }
        if (c.equals("MM:HH"))
        {

            Snackbar.make(findViewById(android.R.id.content), "Please fill from time first", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(getResources().getColor(R.color.mdtp_red))
                    .show();
            focusView = null;
            cancel = true;
        }
        /*if (d.equals("MM:HH"))
        {

            Snackbar.make(findViewById(android.R.id.content), "Please fill to time first", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(getResources().getColor(R.color.mdtp_red))
                    .show();
            focusView = null;
            cancel = true;
        }*/
        if (isEmpty(e))
        {
            mEmaila.setError("Field cannot be empty");
            focusView = mEmaila;
            cancel = true;
        }
        else mEmaila.setError(null);
        if (isEmpty(f))
        {
            mNumber1.setError("Field cannot be empty");
            focusView = mNumber1;
            cancel = true;
        }
        else mNumber1.setError(null);
        if (isEmpty(g))
        {
            add1lt.setError("Field cannot be empty");
            focusView = add1lt;
            cancel = true;
        }
        else add1lt.setError(null);
        if (isEmpty(h))
        {
            add2lt.setError("Field cannot be empty");
            focusView = add2lt;
            cancel = true;
        }
        else add2lt.setError(null);
        if (isEmpty(i))
        {
            add3lt.setError("Field cannot be empty");
            focusView = add3lt;
            cancel = true;
        }
        else add3lt.setError(null);
        if (isEmpty(j))
        {
            pinlt.setError("Field cannot be empty");
            focusView = pinlt;
            cancel = true;
        }
        else pinlt.setError(null);
        if (isEmpty(k))
        {
            mTickets1.setError("Field cannot be empty");
            focusView = mTickets1;
            cancel = true;
        }
        else mTickets1.setError(null);
        if (l.equals("Enter description here..."))
        {
            Snackbar.make(findViewById(android.R.id.content), "Please give a description", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(getResources().getColor(R.color.mdtp_red))
                    .show();
            focusView = null;
            cancel = true;
        }
        if (!isValidPhone(f)) {
            mNumber1.setError("Invalid Phone");
            focusView = mNumber1;
            cancel = true;
        }
        else mNumber1.setError(null);
        if (!isValidEmail(e)) {
            mEmaila.setError("Invalid Email");
            focusView = mEmaila;
            cancel = true;
        }
        else mEmaila.setError(null);
        if (cancel==true) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            if (photoUrl == null) {
                mAuth = FirebaseAuth.getInstance();
                mUser = mAuth.getCurrentUser();
                if (mAuth.getCurrentUser().getPhotoUrl() == null) {
                    photoUrl = null;
                } else {

                    for (UserInfo profile : mUser.getProviderData()) {
                        // check if the provider id matches "facebook.com"
                        if (profile.getProviderId().equals("facebook.com")) {
                            String facebookUserId = profile.getUid();
                            photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?type=large&width=720&height=720";
                        } else if (profile.getProviderId().equals("google.com")) {
                            photoUrl = mAuth.getCurrentUser().getPhotoUrl().toString();
                            photoUrl = photoUrl.replace("/s96-c/", "/s300-c/");
                        }
                    }
                    //photoUrl = mAuth.getCurrentUser().getPhotoUrl().toString();
                }
            }
            DatabaseReference mDatabase = ref.child("parties").child(a);
            final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final String nam = (storageRef.child(userid).child("name")).toString();
            party p = new party(a,photoUrl,b,c,d,e,f,g,h,i,j,null,l,Integer.parseInt(k),userid,nam);
            mDatabase.setValue(p);
            Intent myIntent = new Intent(AddAParty.this, MainActivity.class);
            startActivity(myIntent);
            finish();


        }



    }

    public static class TimePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Activity has to implement this interface
            TimePickerDialog.OnTimeSetListener listener = (TimePickerDialog.OnTimeSetListener) getActivity();

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), listener, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Activity needs to implement this interface
            DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getActivity();

            // Create a new instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dates.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
    }


    private void prepareChooser() {
        Croperino.prepareChooser(AddAParty.this, "Change Picture", ContextCompat.getColor(AddAParty.this, android.R.color.background_dark));
    }

    private void prepareCamera() {
        Croperino.prepareCamera(AddAParty.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CroperinoConfig.REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Croperino.runCropImage(CroperinoFileUtil.getmFileTemp(), AddAParty.this, true, 1, 1, 0, 0);
                }
                break;
            case CroperinoConfig.REQUEST_PICK_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    CroperinoFileUtil.newGalleryFile(data, AddAParty.this);
                    Croperino.runCropImage(CroperinoFileUtil.getmFileTemp(), AddAParty.this, true, 1, 1, 0, 0);
                }
                break;
            case CroperinoConfig.REQUEST_CROP_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Uri i = Uri.fromFile(CroperinoFileUtil.getmFileTemp());
                    mProfile.setImageURI(i);
                    UploadTask uploadTask = imagesRef.putFile(i);

// Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            photoUrl = downloadUrl.toString();
                        }
                    });
                    //Do saving / uploading of photo method here.
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CroperinoFileUtil.REQUEST_CAMERA) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        prepareCamera();
                    }
                }
            }
        } else if (requestCode == CroperinoFileUtil.REQUEST_EXTERNAL_STORAGE) {
            boolean wasReadGranted = false;
            boolean wasWriteGranted = false;

            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasReadGranted = true;
                    }
                }
                if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasWriteGranted = true;
                    }
                }
            }

            if (wasReadGranted && wasWriteGranted) {
                prepareChooser();
            }
        }

    }
    private boolean isValidEmail(String email) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return !isEmpty(email) && pattern.matcher(email).matches();
    }

    private boolean isValidPhone(CharSequence target) {
        return !isEmpty(target) && android.util.Patterns.PHONE.matcher(target).matches() && target.length() == 10;
    }


}


