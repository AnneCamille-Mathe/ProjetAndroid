package fr.eseo.acm.andoird.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import fr.eseo.acm.andoird.projetandroid.Navigation.Connexion;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        /*
        Button connexion = findViewById(R.id.connexion);
        connexion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                selectConnexion();
            }
        });*/
        selectConnexion();
    }

    public void selectConnexion(){
        Intent intent = new Intent(this, Connexion.class);
        startActivity(intent);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu_com est l'id de notre menu_com
        inflater.inflate(R.menu.menu_com, menu_com);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.item1:
                openActivityProject();
                break;
            case R.id.item2:
                openActivityJury();
                break;
                case R.id.item3:
                    openActivityNotesCom();
                    break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openActivityProject(){
        Intent intent = new Intent(this, ProjetActivity.class);
        startActivity(intent);
    }

    public void openActivityJury(){
        Intent intent = new Intent(this, JuryActivity.class);
        startActivity(intent);
    }

    public void openActivityNotesCom(){
        Intent intent = new Intent(this, ComNotesActivity.class);
        startActivity(intent);
    }
*/


}