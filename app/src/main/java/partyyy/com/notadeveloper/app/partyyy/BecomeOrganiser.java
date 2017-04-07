package partyyy.com.notadeveloper.app.partyyy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;

public class BecomeOrganiser extends AppCompatActivity {

    @BindView(R.id.register)
    Button register;
    @BindView(R.id.orgname)
    AutoCompleteTextView orgname;
    @BindView(R.id.orgname1)
    TextInputLayout orgname1;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.terms)
    TextView terms;
    private DatabaseReference mDatabase;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_organiser);
        ButterKnife.bind(this);

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BecomeOrganiser.this, R.style.pop);
                builder.setTitle("Terms and conditions");
                builder.setMessage("T & C");
                builder.setPositiveButton("OK", null);
                // builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

    }

    @OnClick(R.id.register)
    public void onViewClicked() {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        boolean cancel = false;
        View focusView = null;

        final String a = orgname.getText().toString();


        if (isEmpty(a)) {
            orgname1.setError("Field cannot be empty");
            focusView = orgname1;
            cancel = true;
        } else orgname1.setError(null);
        if(check.isChecked()==false)
        {
            cancel = true;
            focusView = null;
            Toast.makeText(BecomeOrganiser.this, "Accept the terms and conditions first!",
                    Toast.LENGTH_LONG).show();
        }

        if (cancel) {
            // form field with an error.
            if (focusView != null)
                focusView.requestFocus();
        } else {
            mDatabase.child("users").child(uid).child("orgname").setValue(a);
            mDatabase.child("users").child(uid).child("b").setValue(true);


            Intent i = new Intent(BecomeOrganiser.this, AddAParty.class);
        startActivity(i);}
    }
}
