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
import fr.eseo.acm.andoird.projetandroid.adapter.ListProjectsAdapterComChoix;
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

        if(project_random.contains("*")){
            String projetSansEtoile = project_random.substring(1);
            String projetSansAccoladeG = projetSansEtoile.replace("[", "");
            String projetSansAccoladeD = projetSansAccoladeG.replace("]", "");
            String projetSansVirgule = projetSansAccoladeD.replace(",", "");
            String[] idProjetsList = projetSansVirgule.split(" ");

            ArrayList<Integer> idProjets = new ArrayList<>();
            for (int i = 0; i < idProjetsList.length; i++) {
                if (isNumeric(idProjetsList[i])) {
                    idProjets.add(Integer.parseInt(idProjetsList[i]));
                    mProjectList.add(new Project(Integer.parseInt(idProjetsList[i])));
                }
            }

            //set adapter to recyclerview
            ListProjectsAdapterComChoix mAdapter = new ListProjectsAdapterComChoix(mProjectList,this);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            JSONObject jsonProjectLists = null;
            try {
                jsonProjectLists = new JSONObject(project_random);
                mProjectList = UserUtils.parseForProjectsForPorte(jsonProjectLists);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //set adapter to recyclerview
            mAdapter = new ListProjectsAdapterCom(mProjectList,this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}