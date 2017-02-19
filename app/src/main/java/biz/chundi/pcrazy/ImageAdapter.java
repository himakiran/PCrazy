package biz.chundi.pcrazy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

/**
 * Created by userhk on 10/02/17.
 */

public class ImageAdapter extends BaseAdapter {

    // Keep all Images in array
    private String[] mThumbIds;
    private Context mContext;
    private LayoutInflater inflater;
    private int[] movIDArray;
    private String searchString = "mithun";
    public ImageAdapter IMG;
    public GridView gridview;


    // Constructor
    public ImageAdapter(Context c , String s) {
        mContext = c;
        inflater = LayoutInflater.from(c);
        this.searchString = s;


        FetchImage fetch = new FetchImage(c);

        try {

                mThumbIds = fetch.execute(searchString).get().getWrapperThumbUrl();


            }

         catch (java.util.concurrent.ExecutionException | java.lang.InterruptedException k) {
            Log.e("CHK-IMG-ADP-Fetch", "fetchtask", k);
        }




    }


    /*
    we r not using the below getCount() , getItem() and getItemId()
     */
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;

    }


    @Override
    public void notifyDataSetChanged() // Create this function in your adapter class
    {

        IMG = new ImageAdapter(this.mContext,searchString);

        gridview.setAdapter(IMG);
        super.notifyDataSetChanged();
    }

    // create a new ImageView for each item referenced by the Adapter
    /*
     Reference : http://www.androidtrainee.com/picasso-image-loader-with-gridview-for-android/
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);

            //imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            /*
            The below code allows you to set the dimens in dimens.xml and then use it here
            https://myskillset.wordpress.com/2013/07/05/android-setting-size-of-grid-view-items-according-to-screensize/
             */
            //imageView.setLayoutParams(new GridView.LayoutParams((int)mContext.getResources().getDimension(R.dimen.width), (int)mContext.getResources().getDimension(R.dimen.height)));
            /*
            The below code solves the layout problem to show the images in equalsized tiles..
            http://stackoverflow.com/questions/23204755/how-to-set-image-size-in-grid-view
             */
            imageView.setLayoutParams(new RelativeLayout.LayoutParams((int) mContext.getResources().getDimension(R.dimen.width), (int) mContext.getResources().getDimension(R.dimen.height)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        //Log.v("CHK-IMG-ADAPTER", "GET-VIEW");
        Picasso
                .with(mContext)
                .load(mThumbIds[position])
                .fit()
                .into(imageView);


        return imageView;
    }

}
