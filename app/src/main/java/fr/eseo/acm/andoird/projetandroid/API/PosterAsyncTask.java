package fr.eseo.acm.andoird.projetandroid.API;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import java.net.URL;

import fr.eseo.acm.andoird.projetandroid.Fragments.DetailsActivity;

public class PosterAsyncTask extends AsyncTask<URL, Void, Bitmap> {

    private DetailsActivity detailsActivity;
    private int idProject;
    private int confidentialite;

    public PosterAsyncTask (DetailsActivity details, int id, int confid) {
        this.detailsActivity = details;
        this.idProject = id;
        this.confidentialite = confid;
    }

    @Override
    public Bitmap doInBackground(URL... urls) {
        String poster_base_64 = this.detailsActivity.getPoster(this.idProject);
        System.out.println(poster_base_64);
        if(poster_base_64.contains("Error converting poster") || poster_base_64.contains("No Poster")){
            return null;
        }
        byte[] decodedString = Base64.decode(poster_base_64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public void onPostExecute(Bitmap results) {
        this.detailsActivity.updatePoster(results, this.confidentialite);
    }
}
