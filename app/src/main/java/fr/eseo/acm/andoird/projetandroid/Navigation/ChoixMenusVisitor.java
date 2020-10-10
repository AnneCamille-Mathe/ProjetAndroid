package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.Fragments.DetailsActivityRandom;
import fr.eseo.acm.andoird.projetandroid.Fragments.VisitorNotesActivity;
import fr.eseo.acm.andoird.projetandroid.R;

public class ChoixMenusVisitor extends API {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_choice);
        Button button_details = (Button) findViewById(R.id.button_details);
        button_details.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    ChoixMenusVisitor.this.details(v);
            }
        });

        Button button_notes = (Button) findViewById(R.id.button_notes);
        button_notes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                  ChoixMenusVisitor.this.notes(v);
            }
        });
    }

    public void details(View v){
        Intent intent = new Intent(this, DetailsActivityRandom.class);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String position = sharedPref.getString("position", "position non trouvée !");
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void notes(View v){
        Intent intent = new Intent(this, VisitorNotesActivity.class);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String position = sharedPref.getString("position", "position non trouvée !");
        intent.putExtra("position", position);
        startActivity(intent);
    }


}
