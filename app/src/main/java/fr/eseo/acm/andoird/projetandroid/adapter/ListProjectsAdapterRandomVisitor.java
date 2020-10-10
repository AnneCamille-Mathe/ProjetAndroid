package fr.eseo.acm.andoird.projetandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.Fragments.DetailsActivity;
import fr.eseo.acm.andoird.projetandroid.Fragments.VisitorNotesActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class ListProjectsAdapterRandomVisitor extends RecyclerView.Adapter<ListProjectsAdapterRandomVisitor.ProjectViewHolder> {
    private List<Project> projectItemList;
    Context context;

    public ListProjectsAdapterRandomVisitor(List<Project> projectItemList, Context context) {
        this.projectItemList = projectItemList;
        this.context = context;
    }

    @Override
    public ListProjectsAdapterRandomVisitor.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project_random, parent, false);
        ListProjectsAdapterRandomVisitor.ProjectViewHolder pvh = new ListProjectsAdapterRandomVisitor.ProjectViewHolder(projectView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListProjectsAdapterRandomVisitor.ProjectViewHolder holder, final int position) {
        holder.txtProjectName.setText(projectItemList.get(position).getTitle());
        holder.txtProjectSupervisor.setText(projectItemList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VisitorNotesActivity.class);
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
