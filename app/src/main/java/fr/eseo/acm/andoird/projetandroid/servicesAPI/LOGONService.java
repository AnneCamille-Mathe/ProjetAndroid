package fr.eseo.acm.andoird.projetandroid.servicesAPI;

import org.json.JSONObject;

public class LOGONService {
    public String getToken(String url){
        String token = "";
        String[] details = url.split("\"token\": \"");
        String[] detail = details[1].split("\"");
        token = detail[0];
        return token;
    }
}
