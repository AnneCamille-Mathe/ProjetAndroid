package fr.eseo.acm.andoird.projetandroid.Fragments;
import fr.eseo.acm.andoird.projetandroid.Navigation.AllProjectsActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ProjectDetailsActivity extends AppCompatActivity {

    private ProjectDetailsFragment movieDetailsFragment;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_details);
        this.movieDetailsFragment = ProjectDetailsFragment.newInstance(getIntent().getIntExtra(AllProjectsActivity.MOVIE_ID,1));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.placeholder_projects_details, movieDetailsFragment);
        transaction.commit();
    }



}
