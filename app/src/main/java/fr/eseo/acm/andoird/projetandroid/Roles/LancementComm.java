package fr.eseo.acm.andoird.projetandroid.Roles;

import android.content.Intent;
import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.Navigation.ChoixMenus;

public class LancementComm extends API {
    public void lancementMenu(){
        Intent intent = new Intent(this, ChoixMenus.class);
        startActivity(intent);
    }
}
