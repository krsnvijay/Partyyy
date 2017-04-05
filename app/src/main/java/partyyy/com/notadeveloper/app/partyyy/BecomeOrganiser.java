package partyyy.com.notadeveloper.app.partyyy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BecomeOrganiser extends AppCompatActivity {

    @BindView(R.id.register)
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_organiser);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        Intent i = new Intent(BecomeOrganiser.this, AddAParty.class);
        startActivity(i);
    }
}
