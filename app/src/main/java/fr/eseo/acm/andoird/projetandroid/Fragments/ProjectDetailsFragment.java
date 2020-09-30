package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.eseo.acm.andoird.projetandroid.Navigation.ComProjetActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.View.ProjectRoleViewAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectDetailsFragment  extends Fragment{
    private RecyclerView roles;
    private Button reviewsButton;
    private TextView title;
    private TextView country;
    private TextView year;
    private TextView genre;
    private TextView director;
    private TextView synopsis;
    private TextView score;
    private ImageView poster;

    private int movieId;

    private ProjectRoleViewAdapter roleAdapter;



    public static ProjectDetailsFragment newInstance(int movieId){
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ComProjetActivity.MOVIE_ID, movieId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater,parent,savedInstanceState);
        return inflater.inflate(R.layout.fragment_project_details, parent, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            this.movieId = getArguments().getInt(ComProjetActivity.MOVIE_ID,0);
            this.reviewsButton = view.findViewById(R.id.movie_details_review_button);
            this.roles = view.findViewById(R.id.movie_details_roles);
            this.roles.setHasFixedSize(true);
            roleAdapter = new ProjectRoleViewAdapter();
            this.roles.setAdapter(roleAdapter);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            this.roles.setLayoutManager(llm);
            this.title = view.findViewById(R.id.movie_details_title);
            this.country = view.findViewById(R.id.movie_details_country);
            this.year = view.findViewById(R.id.movie_details_year);
            this.director = view.findViewById(R.id.movie_details_director);
            this.synopsis = view.findViewById(R.id.movie_details_synopsis);
            this.score = view.findViewById(R.id.movie_details_average_score);
            this.genre = view.findViewById(R.id.movie_details_Genres);
            this.poster = view.findViewById(R.id.movie_details_poster);

        }
    }


}
