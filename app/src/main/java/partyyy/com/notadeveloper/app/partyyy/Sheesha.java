package partyyy.com.notadeveloper.app.partyyy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class Sheesha extends AppCompatActivity {
    public static TextView total;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    ArrayList<shesha> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheesha);

        total = (TextView) findViewById(R.id.total);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        RecyclerView rv=(RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list.add(new shesha("GrapeFruit","200"));
        list.add(new shesha("Lemon and Mint","600"));
        list.add(new shesha("Peach","400"));
        list.add(new shesha("Kiwi","900"));
        list.add(new shesha("Blueberry","300"));
        SheeshaAdapter s=new SheeshaAdapter(list,Sheesha.this);
        rv.setAdapter(s);
    }

}
