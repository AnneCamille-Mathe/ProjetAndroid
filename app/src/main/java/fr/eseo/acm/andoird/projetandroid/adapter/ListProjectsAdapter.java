package fr.eseo.acm.andoird.projetandroid.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class ListProjectsAdapter extends RecyclerView.Adapter<ListProjectsAdapter.ProjectViewHolder> {

    private List<Project> projectItemList;
    Context context;

    public ListProjectsAdapter(List<Project> projectItemList, Context context) {
        this.projectItemList = projectItemList;
        this.context = context;
    }

    @Override
    public ListProjectsAdapter.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project, parent, false);
        ListProjectsAdapter.ProjectViewHolder pvh = new ListProjectsAdapter.ProjectViewHolder(projectView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListProjectsAdapter.ProjectViewHolder holder, final int position) {
        holder.txtProjectName.setText(projectItemList.get(position).getTitle());
        holder.txtProjectSupervisor.setText(projectItemList.get(position).getSuperviseur());
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
            txtProjectName=view.findViewById(R.id.idProjectTitle);
            txtProjectSupervisor = view.findViewById(R.id.idProjectSupervisor);
        }
    }
}

