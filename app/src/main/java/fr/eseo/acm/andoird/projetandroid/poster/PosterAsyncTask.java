package fr.eseo.acm.andoird.projetandroid.poster;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.net.URL;

import fr.eseo.acm.andoird.projetandroid.activities.DetailsActivity;
import fr.eseo.acm.andoird.projetandroid.activities.DetailsActivityRandom;

public class PosterAsyncTask extends AsyncTask<URL, Void, Bitmap> {

    private DetailsActivity detailsActivity = null;
    private DetailsActivityRandom detailsActivityRandom = null;
    private int idProject;
    private int confidentialite;

    public PosterAsyncTask (DetailsActivity details, int id, int confid) {
        this.detailsActivity = details;
        this.idProject = id;
        this.confidentialite = confid;
    }

    public PosterAsyncTask (DetailsActivityRandom details, int id, int confid) {
        this.detailsActivityRandom = details;
        this.idProject = id;
        this.confidentialite = confid;
    }

    @Override
    public Bitmap doInBackground(URL... urls) {
        String poster_base_64;
        SharedPreferences sharedPref;
        if(this.detailsActivityRandom == null){
            poster_base_64 = this.detailsActivity.getPoster(this.idProject);
            sharedPref = PreferenceManager.getDefaultSharedPreferences(detailsActivity.getApplicationContext());
        }
        else{
            poster_base_64 = this.detailsActivityRandom.getPoster(this.idProject);
            sharedPref = PreferenceManager.getDefaultSharedPreferences(detailsActivityRandom.getApplicationContext());
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("saved_base_64_image", poster_base_64);
        editor.commit();
        if(poster_base_64.contains("Error converting poster") || poster_base_64.contains("No Poster")){
            return null;
        }
        byte[] decodedString = Base64.decode(poster_base_64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public void onPostExecute(Bitmap results) {
        if(this.detailsActivityRandom == null){
            this.detailsActivity.updatePoster(results, this.confidentialite);
        }
        else {
            this.detailsActivityRandom.updatePoster(results, this.confidentialite);
        }
    }
}
