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

import fr.eseo.acm.andoird.projetandroid.Navigation.JuryDetailsActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Jury;

public class ListJuryAdapter extends RecyclerView.Adapter<ListJuryAdapter.JuryViewHolder> {

    private List<Jury> juryItemList;
    Context context;

    public ListJuryAdapter(List<Jury> juryItemList, Context context) {
        this.juryItemList = juryItemList;
        this.context = context;
    }

    @Override
    public ListJuryAdapter.JuryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View juryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jury, parent, false);
        ListJuryAdapter.JuryViewHolder pvh = new ListJuryAdapter.JuryViewHolder(juryView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListJuryAdapter.JuryViewHolder holder, final int position) {
        holder.juryDate.setText(juryItemList.get(position).getDate().toString());
        holder.juryId.setText("Jury n°" + juryItemList.get(position).getIdJury());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JuryDetailsActivity.class);
                String emplacement = position + "";
                intent.putExtra("position", emplacement);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                String jury = sharedPref.getString("jury", "les jurys ne sont pas trouvés");
                intent.putExtra("json", jury);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return juryItemList.size();
    }

    public class JuryViewHolder extends RecyclerView.ViewHolder {
        TextView juryDate;
        TextView juryId;

        public JuryViewHolder(View view) {
            super(view);
            juryId =view.findViewById(R.id.jury_id);
            juryDate = view.findViewById(R.id.jury_date);
        }
    }
}
