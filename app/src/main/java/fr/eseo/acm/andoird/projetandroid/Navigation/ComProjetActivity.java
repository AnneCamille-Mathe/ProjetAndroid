package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import fr.eseo.acm.andoird.projetandroid.Fragments.ProjectDetailsActivity;
import fr.eseo.acm.andoird.projetandroid.Fragments.ProjectsSummaryFragment;
import fr.eseo.acm.andoird.projetandroid.R;

public class ComProjetActivity extends AppCompatActivity {
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
        Log.d("message = ", "lancement des projets");
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.saved_token);
        String token = sharedPref.getString(getString(R.string.saved_token), defaultValue);
        Log.d("TOKEN = ", token);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_com);
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
}
