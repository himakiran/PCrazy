package biz.chundi.pcrazy;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by userhk on 10/02/17.
 */

public class FetchImage extends AsyncTask<String, String, Wrapper> {

    // Will contain the raw JSON response as a string.
    private String imagesJSONString;
    public String[] imageUrlArray;
    public int[] imageIDArray;

    private Context fcontext;


    // Will store the context

    public FetchImage(Context c) {

        fcontext = c;
    }

    @Override
    protected Wrapper doInBackground(String... params) {


        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        /*

        here we define that doInBackground takes one string parameter search term ie " person name "
        */

        String search_term = params[0];

            /*
                We will run the background task only if interent connectivity exists
             */

        try {
            /*
            Google custom image search URL

            https://www.googleapis.com/customsearch/v1?key=AIzaSyAe_HCffeEuTeXvXdqGrDzvkCBw8pNReJQ&cx=006773999774991798198:p14zg8dtwuo&q=sridevi&searchType=image


                         */
            Uri.Builder imageURL = new Uri.Builder();
            String mUrl = "www.googleapis.com";
            imageURL.scheme("https")
                    .authority(mUrl)
                    .appendPath("customsearch")
                    .appendPath("v1")
                    .appendQueryParameter("api_key", BuildConfig.GOOGLE_CUSTOM_SEARCH_API_KEY)
                    .appendQueryParameter("cx","006773999774991798198:p14zg8dtwuo")
                    .appendQueryParameter("q",search_term)
                    .appendQueryParameter("searchType","image");

            // Create the request to OpenWeatherMap, and open the connection
            URL url = new URL(imageURL.build().toString());
            Log.v("CHK-IMAGE-URL",url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                imagesJSONString = null;

            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                imagesJSONString = null;
                Log.v("chk-buffer","buffer length is zero");
            }
            imagesJSONString = buffer.toString();
            Log.v("CHK-FETCH", imagesJSONString);
        } catch (IOException e) {
            Log.e("MainActivityFragment", "Error ", e);
            // If the code didn't successfully get the movie data, there's no point in attempting
            // to parse it.
            imagesJSONString = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivityFragment", "Error closing stream", e);
                }
            }
        }


        try {
            Log.v("CHK-MOV-JSON-STR", imagesJSONString);
            //getImageURLsMovieIDs(imagesJSONString);
        } catch (Exception e) {
            Log.e("CHK-DO-IN-BACKGROUND", "CHK-GET-IMG-URL", e);
        }
        Wrapper w = new Wrapper();
        w.setWrapperImgUrl(imageUrlArray);
        w.setWrapperImgId(imageIDArray);
        return w;


    }

    @Override
    protected void onPostExecute(Wrapper w) {
        //You will get your string array result here .
        // do whatever operations you want to do
        super.onPostExecute(w);
        //Log.v("CHK-FETCH", "CHK-postexec");


    }



}
