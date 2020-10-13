package fr.eseo.acm.andoird.projetandroid.servicesAPI;

public class LOGONService {
    public String getToken(String url){
        if(url.contains("KO")){
            return null;
        }
        String token = "";
        String[] details = url.split("\"token\": \"");
        String[] detail = details[1].split("\"");
        token = detail[0];
        return token;
    }
}
