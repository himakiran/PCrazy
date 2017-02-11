package biz.chundi.pcrazy;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        //Log.v("chk-bool",String.valueOf(isConnected));
        if (isConnected) {
            //Log.v("chk-fetch", "chk-isconn");

            setContentView(R.layout.activity_main);


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

    public void displayImg(View view){


        /*
            Retrieving search term input by user
         */
        EditText editText = (EditText) findViewById(R.id.SearchString);
        String ss = editText.getText().toString();
        /*
        Passing the search term to and calling DisplayAactivity
         */

        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("searchSTR", ss);
        startActivity(intent);
    }
}