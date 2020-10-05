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

import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class ListProjectsFromJuryAdapter extends RecyclerView.Adapter<ListProjectsFromJuryAdapter.JuryViewHolder> {

    private List<Project> projectItemList;
    Context context;

    public ListProjectsFromJuryAdapter(List<Project> projectItemList, Context context) {
        this.projectItemList = projectItemList;
        this.context = context;
    }

    @Override
    public ListProjectsFromJuryAdapter.JuryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View juryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project, parent, false);
        ListProjectsFromJuryAdapter.JuryViewHolder pvh = new ListProjectsFromJuryAdapter.JuryViewHolder(juryView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListProjectsFromJuryAdapter.JuryViewHolder holder, final int position) {
        holder.projectTitle.setText(projectItemList.get(position).getTitle().toString());
        holder.projectSupervisor.setText("Superviseur : "+projectItemList.get(position).getSuperviseur());

    }

    @Override
    public int getItemCount() {
        return projectItemList.size();
    }

    public class JuryViewHolder extends RecyclerView.ViewHolder {
        TextView projectTitle;
        TextView projectSupervisor;

        public JuryViewHolder(View view) {
            super(view);
            projectTitle =view.findViewById(R.id.idProjectTitle);
            projectSupervisor = view.findViewById(R.id.idProjectSupervisor);
        }
    }
}
