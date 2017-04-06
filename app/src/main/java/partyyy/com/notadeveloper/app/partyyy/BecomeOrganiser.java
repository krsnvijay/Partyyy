package partyyy.com.notadeveloper.app.partyyy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_organiser);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.register)
    public void onViewClicked() {

        boolean cancel = false;
        View focusView = null;

        final String a = orgname.getText().toString();


        if (isEmpty(a)) {
            orgname1.setError("Field cannot be empty");
            focusView = orgname1;
            cancel = true;
        } else orgname1.setError(null);

        if (cancel) {
            // form field with an error.
            if (focusView != null)
                focusView.requestFocus();
        } else {

        Intent i = new Intent(BecomeOrganiser.this, AddAParty.class);
        startActivity(i);}
    }
}
