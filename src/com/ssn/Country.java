package com.ssn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Country {
	
    private String name;
    private String color;
    private List<Country> neighbours = new ArrayList<>();
    private List<String> domains = new ArrayList<>();

    public Country(String name){
        this.name = name;
    }
    
    public Country(Country copiedCountry){
        this.name = copiedCountry.name;
        List<String> dom = new ArrayList<>(copiedCountry.getDomain());
        this.domains = dom;
        List<Country> neighbours = new ArrayList<>();
        for(Country neighbour : copiedCountry.getNeighbours()){
            neighbours.add(new Country(neighbour.name));
        }
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }
    
    public void addColor(String color) {
        domains.add(color);
    }

    public void removeColor(String receivedColor) {
        for (Iterator<String> iter = domains.iterator(); iter.hasNext(); ) {
            String color = iter.next();
            if (color.equals(receivedColor)) {
                iter.remove();
            }
        }
    }

    public boolean hasColor(){
        return this.color != null;
    }

    public List<Country> getUncoloredNeighbours() {
        List<Country> uncoloredNeighbours = new ArrayList<>();
        for(Country neighbour : neighbours){
            if(!neighbour.hasColor()){
                uncoloredNeighbours.add(neighbour);
            }
        }
        return uncoloredNeighbours;
    }

    public boolean liableForColor(String color){
        for(Country neighbour : neighbours){
            if(color.equals(neighbour.getColor())){
                return false;
            }
        }
        return true;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        List<String> dom = new ArrayList<>();
        dom.add(color);
        this.domains = dom;
    }
    
    public void setNeighbours(List<Country> neighbours) {
        this.neighbours = neighbours;
    }
    
    public void setNeighbours(Country... neighbours) {
        this.neighbours = Arrays.asList(neighbours);
    }

    public List<Country> getNeighbours() {
        return neighbours;
    }

    public List<String> getDomain() {
        return domains;
    }

    public String parseForFile() {
    	String line = this.name;
    	for(Country country : neighbours) {
    		line = line + "," + country.getName();
    	}
		return line;
	}

    public String toString() {
    	String text = "Country: " + this.name + " with color: " + this.getColor() + "\n" + "Neighbours:\n";
    	for(Country neighbour : neighbours){
    		text = text + neighbour.getName() + ", " + "color: " + neighbour.getColor() + "\n";
        }
    	return text;
    }

	public void setDomain(List<String> dom) {
		this.domains = dom;
	}
}