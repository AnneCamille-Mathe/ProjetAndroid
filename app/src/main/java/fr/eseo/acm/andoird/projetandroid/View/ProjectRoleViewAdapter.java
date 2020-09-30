package fr.eseo.acm.andoird.projetandroid.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.eseo.acm.andoird.projetandroid.R;

import androidx.recyclerview.widget.RecyclerView;

public class ProjectRoleViewAdapter extends RecyclerView.Adapter<ProjectRoleViewAdapter.MovieRoleViewHolder> {

    public int getItemCount(){
        return 5;
    }

    public MovieRoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new MovieRoleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_roles_details, parent, false));
    }

    public void onBindViewHolder(MovieRoleViewHolder holder, int position){

    }



    class MovieRoleViewHolder extends RecyclerView.ViewHolder {
        final TextView actorName;
        final TextView actorRole;

        public MovieRoleViewHolder(View view){
            super(view);
            this.actorName = view.findViewById(R.id.role_artist);
            this.actorRole = view.findViewById(R.id.role_title);
        }
    }
}
