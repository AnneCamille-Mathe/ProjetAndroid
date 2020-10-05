package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.adapter.ListJuryAdapter;
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsAdapter;
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsFromJuryAdapter;
import fr.eseo.acm.andoird.projetandroid.room.Jury;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class JuryDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListProjectsFromJuryAdapter mAdapter;
    private List<Project> mProjectList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_details);
        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_jury_project_list);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        JSONObject jury = null;
        try {
            jury = new JSONObject(getIntent().getStringExtra("json"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(jury != null){
            try {
                mProjectList = UserUtils.parseProjectsForJury(jury);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter = new ListProjectsFromJuryAdapter(mProjectList,this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
