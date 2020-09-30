package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import fr.eseo.acm.andoird.projetandroid.R;

public class VisitorProjetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_com);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_visitor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.projects:
                setContentView(R.layout.activity_project_visitor);
                break;
            case R.id.posters:
                setContentView(R.layout.activity_posters_visitor);
                break;
            case R.id.my_jury:
                setContentView(R.layout.activity_jury_visitor);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
