package fr.eseo.acm.andoird.projetandroid.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.api.UserUtils;
import fr.eseo.acm.andoird.projetandroid.activities.com.ComJuryActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.entites.Project;

public class ListProjectsAdapterChoix extends RecyclerView.Adapter<ListProjectsAdapterChoix.ProjectViewHolder> {
    private List<Project> projectItemList;
    private ArrayList<Integer> projectsChoisis = new ArrayList<Integer>();
    private static final String TAG = "MyActivity";
    Context context;

    public ListProjectsAdapterChoix(List<Project> projectItemList, Context context) {
        this.projectItemList = projectItemList;
        this.context = context;
    }

    @Override
    public ListProjectsAdapterChoix.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project, parent, false);
        ListProjectsAdapterChoix.ProjectViewHolder pvh = new ListProjectsAdapterChoix.ProjectViewHolder(projectView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListProjectsAdapterChoix.ProjectViewHolder holder, final int position) {
        holder.txtProjectName.setText(projectItemList.get(position).getTitle());
        holder.txtProjectSupervisor.setText(projectItemList.get(position).getSuperviseur());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                String nbProjects = sharedPref.getString("nbProjects", "le nbProject n'est' pas trouvé");

                if (!projectsChoisis.contains(position) && !((Integer.parseInt(nbProjects) == (projectsChoisis.size())))) {
                    Toast.makeText(context, "Projet enregistré", Toast.LENGTH_LONG).show();
                    projectsChoisis.add(position);
                } else if (projectsChoisis.contains(position)) {
                    Toast.makeText(context, "Vous avez déjà sélectionné ce projet", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(nbProjects) == (projectsChoisis.size())) {
                    Toast.makeText(context, "La liste est complète", Toast.LENGTH_LONG).show();
                    try {
                        enregistrerLesProjets(view);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectItemList.size();
    }

    public void enregistrerLesProjets(View view) throws JSONException {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String projects = sharedPref.getString("projets", "les projets ne sont pas trouvés");

        List<Project> mProjectList = UserUtils.parseForProjectsWithDescrip(new JSONObject(projects));
        List<String> projetsChoisis = new ArrayList<String>();

        for (int j = 0; j < projectsChoisis.size(); j++) {
            for (int i = 0; i < mProjectList.size(); i++) {
                if (mProjectList.get(i).getIdProject() == projectsChoisis.get(j)) {
                    projetsChoisis.add(mProjectList.get(i).getIdProject() + " ");
                }
            }
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("randomProjects", "*" + projetsChoisis.toString());
        editor.putString("notes", "");
        editor.putString("commentaires", "");
        editor.commit();

        Intent intent = new Intent(view.getContext(), ComJuryActivity.class);
        view.getContext().startActivity(intent);
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView txtProjectName;
        TextView txtProjectSupervisor;

        public ProjectViewHolder(View view) {
            super(view);
            txtProjectName = view.findViewById(R.id.idProjectTitle);
            txtProjectSupervisor = view.findViewById(R.id.idProjectSupervisor);
        }
    }
}

