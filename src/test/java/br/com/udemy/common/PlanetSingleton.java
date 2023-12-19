package br.com.udemy.common;

import br.com.udemy.domain.Planet;

public class PlanetSingleton {
    private static Planet instance;
    public static Planet getInstance(){
        if (instance == null){
            instance =  new Planet("name","climate","terrain");
        }
        return instance;
    }

    public static Planet getInvalidInstance(){
        if (instance == null){
            instance =  new Planet("","","");
        }
        return instance;
    }
}
