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

public class DetailsActivity extends AppCompatActivity {

    private  String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_project_details);
        if(getIntent().hasExtra("position")) {
            position = (getIntent().getStringExtra("position"));
        }

        List<Project> mProjectList = null;
        if(getIntent().hasExtra("json")) {
            try {
                JSONObject jsonProjectLists = new JSONObject(getIntent().getStringExtra("json"));
                mProjectList = UserUtils.parseForProjectsWithDescrip(jsonProjectLists);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(mProjectList==null){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String array = sharedPref.getString("projectsFromJury", "Projets non trouvés !");
            mProjectList = UserUtils.parseForProjectsFromJury(array);
        }

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
    }
}
