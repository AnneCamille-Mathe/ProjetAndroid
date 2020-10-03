package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.Fragments.ProjectDetailsActivity;
import fr.eseo.acm.andoird.projetandroid.Fragments.ProjectsSummaryFragment;
import fr.eseo.acm.andoird.projetandroid.R;

public class AllProjectsActivity extends API {
    private ProjectsSummaryFragment projectsSummaryFragment;
    public static final String MOVIE_ID = "Movie_id";

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.saved_token);
        String token = sharedPref.getString(getString(R.string.saved_token), defaultValue);
        System.out.print("REUSSI ? " + token);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_com);
        Log.d("message = ", "lancement des projets");
        try {
            this.getProjects();
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException | KeyManagementException e) {
            e.printStackTrace();
        }
        this.projectsSummaryFragment = ProjectsSummaryFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.placeholder_projects_summary_list, projectsSummaryFragment);
        transaction.commit();
    }
    public void selectMovie(int movieId){
        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        intent.putExtra(MOVIE_ID,movieId);
        startActivity(intent);
    }

    public void getProjects() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String[] params = new String[] {API.API_USER, username, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_PROJECTS, params);
        System.out.println(url.toString());
        System.out.println("answer: "+ this.getReplyFromHttpUrl(url) + "fin");
    }
}
