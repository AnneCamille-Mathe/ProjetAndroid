package fr.eseo.acm.andoird.projetandroid.vues;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.eseo.acm.andoird.projetandroid.Fragments.ProjectsSummaryFragment;
import fr.eseo.acm.andoird.projetandroid.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectsSummaryListViewAdapter extends RecyclerView.Adapter<ProjectsSummaryListViewAdapter.MovieSummaryListViewHolder>{

    private ProjectsSummaryFragment fragment;

    public ProjectsSummaryListViewAdapter(ProjectsSummaryFragment fragment){
        super();
        this.fragment = fragment;
    }

    @Override
    public MovieSummaryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MovieSummaryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_summary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieSummaryListViewHolder holder, final int position){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ProjectsSummaryListViewAdapter.this.fragment.selectMovie(position);
            }
        });
    }

    public int getItemCount(){
        return 10;
    }

    class MovieSummaryListViewHolder extends RecyclerView.ViewHolder {
        final TextView movieTitle;
        final TextView movieGenre;

        public MovieSummaryListViewHolder(@NonNull View itemView){
            super(itemView);
            this.movieTitle = itemView.findViewById(R.id.summary_movie_title);
            this.movieGenre = itemView.findViewById(R.id.summary_movie_genre);

        }
    }
}
