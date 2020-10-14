package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.API.PosterAsyncTask;
import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.Navigation.FullScreenPosterActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class DetailsActivityRandom extends API {

    private  String position;
    private String projet;
    private String projetEnregistres;
    private String superviseurUsername;

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

        String[] supName = superviseur.split(" ");
        if(supName[1].length()<5){
            this.superviseurUsername = supName[1].toLowerCase() + supName[0].substring(0, 3).toLowerCase();
        }
        else if(supName[0].length()<3){
            this.superviseurUsername = supName[1].substring(0, 5).toLowerCase() + supName[0].toLowerCase();
        }
        else{
            this.superviseurUsername = supName[1].substring(0, 5).toLowerCase() + supName[0].substring(0, 3).toLowerCase();
        }

        String m = "";
        for(int i=0; i<members.size(); i++){
            if(i==0){
                m += members.get(i);
            } else{
                m += ", "+members.get(i);
            }
        }

        new PosterAsyncTask(DetailsActivityRandom.this, mProjectList.get(Integer.parseInt(position)).getIdProject(), confidentialite).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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

    public String getPoster(int id){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String style = "FLB64";
        String[] params = new String[] {API.API_USER, username, API_PROJECT_ID, String.valueOf(id), API.API_STYLE, style, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_POSTER, params);
        return this.getReplyFromHttpUrl(url);
    }

    public void updatePoster (final Bitmap poster, int confidentialite) {
        TextView noPoster = findViewById(R.id.noposter);
        boolean myJury = false;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        if(getIntent().hasExtra("originClass")){
            myJury = getIntent().getStringExtra("originClass").equals("jury");
        }
        if (confidentialite != 0 && !myJury && !username.equals(this.superviseurUsername)){
            noPoster.setText("Poster confidentiel !");
        }
        else if(poster == null){
            noPoster.setText("Pas de poster à afficher !");
        }
        else {
            ImageView postr = findViewById(R.id.poster);
            postr.setImageBitmap(poster);
            postr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailsActivityRandom.this, FullScreenPosterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
