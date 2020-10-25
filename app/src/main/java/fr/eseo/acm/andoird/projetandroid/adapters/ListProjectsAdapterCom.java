package fr.eseo.acm.andoird.projetandroid.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.activities.com.NotesComActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.entites.Project;

public class ListProjectsAdapterCom extends RecyclerView.Adapter<ListProjectsAdapterCom.ProjectViewHolder> {
    private List<Project> projectItemList;
    Context context;

    public ListProjectsAdapterCom(List<Project> projectItemList, Context context) {
        this.projectItemList = projectItemList;
        this.context = context;
    }

    @Override
    public ListProjectsAdapterCom.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project_random, parent, false);
        ListProjectsAdapterCom.ProjectViewHolder pvh = new ListProjectsAdapterCom.ProjectViewHolder(projectView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListProjectsAdapterCom.ProjectViewHolder holder, final int position) {
        holder.txtProjectName.setText(projectItemList.get(position).getTitle());
        holder.txtProjectSupervisor.setText(projectItemList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NotesComActivity.class);
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
}
