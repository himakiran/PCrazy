package biz.chundi.pcrazy;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import static biz.chundi.pcrazy.R.id.gv;
import static biz.chundi.pcrazy.R.layout.fragment_main;

/**
 * Created by userhk on 09/02/17.
 */

public class MainActivityFragment extends Fragment {

    public ImageAdapter IMG;
    public GridView gridview;
    public String searchString;
    public Context c;
    public MainActivityFragment() {

    }
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);}

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);}

    public void updateView() {
        IMG.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // first we get the rootView

        View rootView = inflater.inflate(fragment_main, container, false);


        // The we use the rootview to get the gridview

        gridview = (GridView) rootView.findViewById(gv);

        // we set the image adapter in the gridview and then return the rootview
        // the image adapter is initialized with the movieStrs in the postexecute of fetch task.
        //Log.v("CHK-MAIN-ACTVITY-FRAGT",fetch.imgAdapter.toString());


        IMG = new ImageAdapter(this.getActivity(),searchString);


        IMG.notifyDataSetChanged();



        gridview.setAdapter(IMG);


//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//                /*The code below illustrates making a new intent, declaring the second activity to open
//                ie DetailActivity and then pass a string parameter ie forecast. which will be used
//                by the onCreateView() in detailActivity to set the weataher string.
//                It also passes geo which shall be used by the if (id == R.id.detail_see_map)
//                function in detailActivity to set the Uri.
//                 */
//                Log.v("CHK-MAINACTFRAG", String.valueOf(movieIDArray[position]));
//                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra("mov_ID", movieIDArray[position]);
//
//                startActivity(intent);
//            }
//        });

        return rootView;

    }

}
