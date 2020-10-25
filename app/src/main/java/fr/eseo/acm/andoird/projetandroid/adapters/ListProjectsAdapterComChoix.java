package fr.eseo.acm.andoird.projetandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.api.UserUtils;
import fr.eseo.acm.andoird.projetandroid.activities.com.NotesComActivityChoix;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.entites.Project;

public class ListProjectsAdapterComChoix extends RecyclerView.Adapter<ListProjectsAdapterComChoix.ProjectViewHolder> {
    private List<Project> projectItemList;
    Context context;
    private static final String TAG = "MyActivity";

    public ListProjectsAdapterComChoix(List<Project> projectItemList, Context context) {
        this.projectItemList = projectItemList;
        this.context = context;
    }

    @Override
    public ListProjectsAdapterComChoix.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project_random, parent, false);
        ListProjectsAdapterComChoix.ProjectViewHolder pvh = new ListProjectsAdapterComChoix.ProjectViewHolder(projectView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListProjectsAdapterComChoix.ProjectViewHolder holder, final int position) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.context);
        String projetsRandom = sharedPref.getString("randomProjects", "");

        String projetSansEtoile = projetsRandom.substring(1);
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

        String projects = sharedPref.getString("projets", "les projets ne sont pas trouvés");

        List<Project> mProjectList = null;
        try {
            mProjectList = UserUtils.parseForProjectsWithDescrip(new JSONObject(projects));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Project> listProjets = new ArrayList<>();

        for (int i = 0; i < idProjets.size(); i++) {
            for (int j = 0; j < mProjectList.size(); j++) {
                if (idProjets.get(i) == mProjectList.get(j).getIdProject()) {
                    listProjets.add(mProjectList.get(j));
                }
            }
        }

        holder.txtProjectName.setText(listProjets.get(position).getTitle());
        holder.txtProjectSupervisor.setText(listProjets.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NotesComActivityChoix.class);
                String emplacement = position + "";
                intent.putExtra("position", emplacement);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectItemList.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView txtProjectName;
        TextView txtProjectSupervisor;
        public ProjectViewHolder(View view) {
            super(view);
            txtProjectName=view.findViewById(R.id.idProjectTitleRandom);
            txtProjectSupervisor = view.findViewById(R.id.idProjectDescriptionRandom);
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
