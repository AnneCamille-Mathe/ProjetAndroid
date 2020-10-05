package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.eseo.acm.andoird.projetandroid.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsAdapter;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class ListProjectsFragment extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListProjectsAdapter mAdapter;
    private List<Project> mProjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_projects);

        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_projects_list);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Populate the list of projects
        mProjectList = new ArrayList<Project>();
        Intent intent = getIntent();
        if(getIntent().hasExtra("json")) {
            try {
                JSONObject jsonProjectLists = new JSONObject(getIntent().getStringExtra("json"));
                mProjectList = UserUtils.parseForProjects(jsonProjectLists);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //set adapter to recyclerview
        mAdapter = new ListProjectsAdapter(mProjectList,this);
        mRecyclerView.setAdapter(mAdapter);
    }


}
