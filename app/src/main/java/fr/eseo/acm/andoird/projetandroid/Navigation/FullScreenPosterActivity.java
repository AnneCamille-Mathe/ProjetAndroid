package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import fr.eseo.acm.andoird.projetandroid.R;

public class FullScreenPosterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.full_size_image);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String poster_base_64 = sharedPref.getString("saved_base_64_image", "l image n'est pas trouvée");

        Bitmap poster;

        if(poster_base_64.contains("Error converting poster") || poster_base_64.contains("No Poster")){
            poster = null;
        }
        else{
            byte[] decodedString = Base64.decode(poster_base_64, Base64.DEFAULT);
            poster = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }

        ImageView imgDisplay = findViewById(R.id.poster_full);;
        Button btnClose = findViewById(R.id.close);

        imgDisplay.setImageBitmap(poster);

        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FullScreenPosterActivity.this.finish();
            }
        });
    }
}
