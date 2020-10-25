package fr.eseo.acm.andoird.projetandroid.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;

import fr.eseo.acm.andoird.projetandroid.api.API;
import fr.eseo.acm.andoird.projetandroid.activities.ListProjectChoix;
import fr.eseo.acm.andoird.projetandroid.R;

public class ChoixNbProjects extends API {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_nb_projects);
        Button button_valider = (Button) findViewById(R.id.validerNbProjects);
        button_valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChoixNbProjects.this.lanceChoixProjects(v);
            }
        });
    }

    public void lanceChoixProjects(View v){
        Intent intent = new Intent(this, ListProjectChoix.class);
        intent.putExtra("json", this.getProjects());
        EditText projets = (EditText)findViewById(R.id.nombreProjets);
        String nbProjects = projets.getText().toString();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("projets", this.getProjects());
        editor.putString("nbProjects", nbProjects);
        editor.commit();
        startActivity(intent);
    }


    public String getProjects() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String[] params = new String[] {API.API_USER, username, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_PROJECTS, params);
        return this.getReplyFromHttpUrl(url);
    }

}
