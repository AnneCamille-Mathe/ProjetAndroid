package fr.eseo.acm.andoird.projetandroid.Navigation;



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

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsAdapter;
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsAdapterCom;
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsAdapterRandom;
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsAdapterRandomVisitor;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class ComNotesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListProjectsAdapterCom mAdapter;
    private List<Project> mProjectList;
    ListProjectsAdapter.ProjectViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_projectRandom_list_random);
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
        mAdapter = new ListProjectsAdapterCom(mProjectList,this);
        mRecyclerView.setAdapter(mAdapter);
    }

}