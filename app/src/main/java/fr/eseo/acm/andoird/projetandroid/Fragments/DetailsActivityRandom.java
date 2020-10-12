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
    private String projetEnregistres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_project_details);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        position = sharedPref.getString("position", "position non trouvée !");
        projet = sharedPref.getString("randomProjects", "projets non trouvés !");
        projetEnregistres = sharedPref.getString("projets", "projets non trouvés !");

        List<Project> mProjectList = null;
        JSONObject jsonProjectLists = null;
        JSONObject jsonProjectListsEnregistres = null;

        List<Project> mProjectListEnregistres = null;

        List<Project> mProjectListDetails = new ArrayList<Project>();


        try {
            jsonProjectLists = new JSONObject(projet);
            jsonProjectListsEnregistres = new JSONObject(projetEnregistres);
            System.out.println(jsonProjectLists);
            mProjectList = UserUtils.parseForProjectsForPorte(jsonProjectLists);
            mProjectListEnregistres = UserUtils.parseForProjectsWithDescrip(jsonProjectListsEnregistres);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //TODO - Afficher les détails en fonction de l'ID du projet (récupéré dans mProjectList, "id")

        //Enregistrement des details des projets
        for(int i=0; i<mProjectListEnregistres.size(); i++){
            for(int j=0; j< mProjectList.size(); j++){
                if(mProjectList.get(j).getIdProject() == mProjectListEnregistres.get(i).getIdProject()){
                    mProjectListDetails.add(mProjectListEnregistres.get(i));
                }
            }
        }

        Project projetAAfficher = null;
        for(int i = 0; i<mProjectListDetails.size(); i++){
            if((mProjectListDetails.get(i).getIdProject()) == (mProjectList.get(Integer.parseInt(position)).getIdProject())){
                projetAAfficher = mProjectListDetails.get(i);
            }
        }

        String titre = projetAAfficher.getTitle();
        int confidentialite = projetAAfficher.getConfidentiality();
        String description = projetAAfficher.getDescription();
        String superviseur =  projetAAfficher.getSuperviseur();
        ArrayList<String> members = projetAAfficher.getMembers();

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
