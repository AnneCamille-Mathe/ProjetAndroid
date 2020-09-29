package fr.eseo.acm.andoird.projetandroid.API;

public enum Enum {
    API_LOGON("q=LOGON"),
    API_LIPRJ ("q=API_LIPRJ"), // Pour récupérer un projet grâce à son slug
    API_MYPRJ ("q=MYPRJ"), // Pour récupérer un projet grâce à son ID
    API_LIJUR ("q=LIJUR"), // Pour rechercher un projet grâce à son ID
    API_MYJUR ("q=MYJUR"), // Pour récupérer un scope grâce à son ID
    API_JYINF ("q=JYIN"), // Pour récupérer une US grâce à son ID
    API_POSTR ("q=POSTR"), // Pour récupérer un champ "custom" d'une US grâce à son ID
    API_NOTES ("q=NOTES"), // Pour lister les tâches d'une US grâce à son ID
    API_NEWNT ("q=NEWNT"),
    API_PORTE ("q=PORTE");

        /**
         * Chemin de l'API
         */
        private String api;

        /**
         *
         * @param api Chemin de l'API
         */
        private Enum(String api) {
            this.api = api;
        }



    /**
         * Retourne le chemin de l'API
         */
        public String toString(){
            return this.api;
        }
}
