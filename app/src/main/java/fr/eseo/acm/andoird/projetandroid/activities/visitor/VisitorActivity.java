package fr.eseo.acm.andoird.projetandroid.activities.visitor;


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
import fr.eseo.acm.andoird.projetandroid.adapters.ListProjectsAdapterRandomVisitor;
import fr.eseo.acm.andoird.projetandroid.entites.Project;

public class VisitorActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListProjectsAdapterRandomVisitor mAdapter;
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

        if (project_random.contains("*")) {
            String projetSansEtoile = project_random.substring(1);
            String projetSansAccoladeG = projetSansEtoile.replace("[", "");
            String projetSansAccoladeD = projetSansAccoladeG.replace("]", "");
            String projetSansVirgule = projetSansAccoladeD.replace(",", "");
            String[] idProjetsList = projetSansVirgule.split(" ");

            ArrayList<Integer> idProjets = new ArrayList<>();
            for (int i = 0; i < idProjetsList.length; i++) {
                if (isNumeric(idProjetsList[i])) {
                    idProjets.add(Integer.parseInt(idProjetsList[i]));
                }
            }

            //LIST PROJECT FROM ID
            ArrayList<Project> listProjets = new ArrayList<>();

            String projects = sharedPref.getString("projets", "les projets ne sont pas trouvés");

            List<Project> mProjectList = null;
            try {
                mProjectList = UserUtils.parseForProjectsWithDescrip(new JSONObject(projects));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < idProjets.size(); i++) {
                for (int j = 0; j < mProjectList.size(); j++) {
                    if (idProjets.get(i) == mProjectList.get(j).getIdProject()) {
                        listProjets.add(mProjectList.get(j));
                    }
                }
            }


            mAdapter = new ListProjectsAdapterRandomVisitor(listProjets, this);
            mRecyclerView.setAdapter(mAdapter);

        } else {
            JSONObject jsonProjectLists = null;
            try {
                jsonProjectLists = new JSONObject(project_random);
                mProjectList = UserUtils.parseForProjectsForPorte(jsonProjectLists);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //set adapter to recyclerview
            mAdapter = new ListProjectsAdapterRandomVisitor(mProjectList, this);
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
