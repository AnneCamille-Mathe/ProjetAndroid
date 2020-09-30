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
        selectConnexion();
    }

    public void selectConnexion(){
        Intent intent = new Intent(this, Connexion.class);
        startActivity(intent);
    }
}