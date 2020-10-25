package fr.eseo.acm.andoird.projetandroid.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.api.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.adapters.ListProjectsAdapter;
import fr.eseo.acm.andoird.projetandroid.adapters.ListProjectsAdapterRandom;
import fr.eseo.acm.andoird.projetandroid.entites.Project;

public class RandomProjectsActivity  extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListProjectsAdapterRandom mAdapter;
    private List<Project> mProjectList;
    ListProjectsAdapter.ProjectViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_projects_random);

        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_projects_list_random);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Populate the list of projects
        mProjectList = new ArrayList<Project>();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String project_random = sharedPref.getString("randomProjects", "le randomProjects n'est pas trouvé");

        JSONObject jsonProjectLists = null;
        try {
            jsonProjectLists = new JSONObject(project_random);
            mProjectList = UserUtils.parseForProjectsForPorte(jsonProjectLists);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i = 0; i<this.mProjectList.size();i++){
            System.out.println();
        }

        //set adapter to recyclerview
        mAdapter = new ListProjectsAdapterRandom(mProjectList,this);
        mRecyclerView.setAdapter(mAdapter);
    }

}
