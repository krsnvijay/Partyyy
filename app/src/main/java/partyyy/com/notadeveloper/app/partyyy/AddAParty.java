package partyyy.com.notadeveloper.app.partyyy;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Html.fromHtml;

public class AddAParty extends AppCompatActivity implements OnDateSelectedListener{
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

    private String photoUrl;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private DatabaseReference ref;
    private TextView dates;
    private MaterialCalendarView calendarView;
    Dialog dialog;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aparty);
        ButterKnife.bind(this);

        dates = (TextView) findViewById(R.id.dates);

        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
                calendarView.setOnDateChangedListener(this);
                /*calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                        dates.setText(calendarView.getSelectedDates().toString());
                        dialog.dismiss();
                    }
                });*/


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog = new Dialog(AddAParty.this, R.style.dialogthemez);
                } else {
                    dialog = new Dialog(AddAParty.this);
                }
                dialog.setContentView(R.layout.calenderlayout);
                dialog.setTitle(fromHtml("<font color='#c83737'>Select Dates</font>"));
                dialog.show();

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

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        dates.setText(getSelectedDatesString());
    }
    private String getSelectedDatesString() {
        calendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }
}

