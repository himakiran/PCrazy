package biz.chundi.pcrazy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        //Log.v("chk-bool",String.valueOf(isConnected));
        if (isConnected) {
            //Log.v("chk-fetch", "chk-isconn");


        } else {
            //Log.v("chk-fetch", "chk-isnotcon");
            TextView t = new TextView(this);
            t.setText(R.string.no_internet);
            t.setTextSize(30);
            Toast tt = new Toast(this);
            tt.setGravity(Gravity.CENTER, 0, 0);
            tt.setDuration(Toast.LENGTH_LONG);
            tt.setView(t);
            tt.show();


        }


    }
}
