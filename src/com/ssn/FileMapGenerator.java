package com.ssn;

import java.util.ArrayList;

final public class FileMapGenerator {
	
	private static ArrayList<Country> countries = new ArrayList<>();
    
    public static String[] createMapLines() {
    	createCountries();
    	String[] lines = new String[countries.size()];
    	int i = 0;
    	for(Country country : countries) {
    		lines[i] = country.parseForFile();
    		i++;
    	}
    	return lines;
    }

    private static void createCountries(){
    	Country WA = new Country("WA");
        countries.add(WA);
        Country NT = new Country("NT");
        countries.add(NT);
        Country SA = new Country("SA");
        countries.add(SA);
        Country Q = new Country("Q");
        countries.add(Q);
        Country NSW = new Country("NSW");
        countries.add(NSW);
        Country V = new Country("V");
        countries.add(V);
        Country T = new Country("T");
        countries.add(T);
        SA.setNeighbours(WA, NT, Q, NSW, V);
        Q.setNeighbours(SA, NT, NSW);
        NSW.setNeighbours(SA, Q, V);
        WA.setNeighbours(NT, SA);
        V.setNeighbours(SA, NSW);
        T.setNeighbours();
        NT.setNeighbours(SA, Q);
    }
}