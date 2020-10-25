package fr.eseo.acm.andoird.projetandroid.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import fr.eseo.acm.andoird.projetandroid.api.API;
import fr.eseo.acm.andoird.projetandroid.activities.DetailsActivityRandom;
import fr.eseo.acm.andoird.projetandroid.activities.visitor.VisitorComActivity;
import fr.eseo.acm.andoird.projetandroid.activities.visitor.VisitorNotesActivity;
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

        Button buttonCommentaire = (Button) findViewById(R.id.buttonCommentaire);
        buttonCommentaire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChoixMenusVisitor.this.commentaire(v);
            }
        });

        Button buttonEmplacement = (Button) findViewById(R.id.buttonEmplacement);
        buttonEmplacement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChoixMenusVisitor.this.emplacement(v);
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

    public void commentaire(View v){
        Intent intent = new Intent(this, VisitorComActivity.class);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String position = sharedPref.getString("position", "position non trouvée !");
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void emplacement(View v){
        Intent intent = new Intent(this, EmplacementPlan.class);
        startActivity(intent);
    }


}
