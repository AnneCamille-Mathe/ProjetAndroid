package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import fr.eseo.acm.andoird.projetandroid.Fragments.ProjectsSummaryFragment;
import fr.eseo.acm.andoird.projetandroid.R;

public class ProjetActivity extends AppCompatActivity {
    private ProjectsSummaryFragment projectsSummaryFragment;
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
        Log.d("test", "un message");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        this.projectsSummaryFragment = ProjectsSummaryFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.placeholder_projects_summary_list, projectsSummaryFragment);
        transaction.commit();

    }

}
