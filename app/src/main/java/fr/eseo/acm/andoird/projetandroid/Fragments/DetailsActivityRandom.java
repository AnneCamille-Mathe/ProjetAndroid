package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class DetailsActivityRandom extends AppCompatActivity {

    private  String position;
    private String projet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_project_details);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        position = sharedPref.getString("position", "position non trouvée !");
        projet = sharedPref.getString("randomProjects", "projets non trouvés !");

        List<Project> mProjectList = null;
        JSONObject jsonProjectLists = null;
        try {
            jsonProjectLists = new JSONObject(projet);
            System.out.println(jsonProjectLists);
            mProjectList = UserUtils.parseForProjectsForPorte(jsonProjectLists);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //TODO - Afficher les détails en fonction de l'ID du projet (récupéré dans mProjectList, "id")

        /*
        String titre = mProjectList.get(Integer.parseInt(position)).getTitle();
        int confidentialite = mProjectList.get(Integer.parseInt(position)).getConfidentiality();
        String description = mProjectList.get(Integer.parseInt(position)).getDescription();
        String superviseur =  mProjectList.get(Integer.parseInt(position)).getSuperviseur();

        ArrayList<String> members = mProjectList.get(Integer.parseInt(position)).getMembers();
        String m = "";
        for(int i=0; i<members.size(); i++){
            if(i==0){
                m += members.get(i);
            } else{
                m += ", "+members.get(i);
            }
        }

        TextView titreView = findViewById(R.id.title);
        titreView.setText(titre);

        TextView confidentialiteView = findViewById(R.id.confidentiality);
        confidentialiteView.setText("Confidentialité : "+confidentialite);

        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(description);

        TextView superviseurView = findViewById(R.id.supervisor);
        superviseurView.setText("Superviseur : "+superviseur);

        TextView membersView = findViewById(R.id.students);
        membersView.setText("Elèves : "+m);

         */
    }
}
