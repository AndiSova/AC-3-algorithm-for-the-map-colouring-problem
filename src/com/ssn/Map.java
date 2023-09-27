package com.ssn;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Country> countries = new ArrayList<>();

    public Map(List<Country> countries){
        this.countries = countries;
    }

    public Map (Map mapCopy) {
        for(Country country : mapCopy.getCountries()){
            Country copiedCountry = new Country(country);
            this.countries.add(copiedCountry);
        }
    }
    
    public List<Country> getCountries() {
        return countries;
    }

    public void setDomains(List<String> colors){
        for (Country country : countries) {
            List<String> domain = new ArrayList<>(colors);
            country.setDomain(domain);
        }
    }
    

    public boolean fullyColoredMap(){
        for (Country country : countries) {
           if(!country.hasColor()){
               return false;
           }
        }
        return true;
    }

    public Country getUncoloredCountry(){
        for (Country country : countries) {
            if(!country.hasColor()){
                return country;
            }
        }
        return null;
    }

    public String toString() {
    	String mapString = "";
    	for(Country country : countries){
             mapString = mapString + country.toString() + "\n";
        }
        return mapString;
    }
}

