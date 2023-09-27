package com.ssn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ArcConsistency {
	
	private Map map;
	private int numberOfOperations = 0;
	
    public ArcConsistency(Map map){
        this.map = map;
    }

    public Map solve(String arcType){
    	if(arcType.equals("3")) {
    		return backtrackingWithAC3(map);
    	} else if (arcType.equals("7")) {
    		// Still doesn't work.
    	}
    	return null;
    }
    
    private boolean hasValuesAssignedToDomain(List<Country> neighbours){
    	for(Country neighbour : neighbours){
    		if(neighbour.getDomain().isEmpty()){
    			return false;
    		}
    	}
    	return true;
    }

	private Map backtrackingWithAC3(Map map) {
		numberOfOperations++;
		if(map.fullyColoredMap()){
			System.out.println("Number of operations is: " + numberOfOperations);
            return map;
        }
        Country country = map.getUncoloredCountry();
        List<String> domain = new ArrayList<>(country.getDomain());
        for(String color : domain){
            if(country.liableForColor(color)){
                country.setColor(color);
                List<Country> unassignedNeighbours = country.getUncoloredNeighbours();
                for(Country neighbour : unassignedNeighbours){
                    neighbour.removeColor(color);
                }
                Map copyMap = new Map(map);
                map = this.ac3(map);
                if(this.hasValuesAssignedToDomain(country.getUncoloredNeighbours())){
                    Map result = backtrackingWithAC3(map);
                    if(result != null) {
                    	return result;
                    }
                }
                country.setColor(null);
                for(Country neighbour : unassignedNeighbours){
                    neighbour.addColor(color);
                }
                map = copyMap;
            }
        }
        return null;
	}

    private Map ac3(Map map){
        Queue<Pair<Country,Country>> arcs = this.getInitialQueue(map);
        Pair<Country,Country> arc;
        while(!arcs.isEmpty()){
            arc = arcs.remove();
            if(this.revise(arc)){
                List<Country> neighbours = arc.first.getNeighbours();
                for(Country neighbour : neighbours){
                    arcs.add(new Pair<>(neighbour, arc.first));
                }
            }
        }
        return map;
    }
    
    private Queue<Pair<Country,Country>> getInitialQueue(Map map){
        Queue<Pair<Country,Country>> arcs = new LinkedList<>();
        for(Country country : map.getCountries()){
            for(Country neighbour : country.getNeighbours()){
                Pair<Country,Country> arc = new Pair<>(country, neighbour);
                arcs.add(arc);
            }
        }      
        return arcs;
    }
    
    private boolean revise(Pair<Country,Country> arc){
        boolean change = false;
        List<String> domain = new ArrayList<>(arc.first.getDomain());
        for(String colorFirst : domain){
            boolean isTheSame = true;
            for(String colorSecond : arc.second.getDomain()){
                if(!colorSecond.equals(colorFirst)){
                    isTheSame = false;
                    break;
                }
            }
            if(isTheSame){
                arc.first.removeColor(colorFirst);
                change = true;
            }
        }
        return change;
    }
    
//   private Map ac7(Map map) {
//  	   Set<String> seekSupporSet = new HashSet<String>();
//		   Queue<Pair<Country,Country>> arcs = this.getInitialQueue(map);
//		   Pair<Country,Country> arc;
//     	   //seekSupporSet = arcs;
//     	   for(Country country : map.getCountries()) {
//     		   seekSupporSet.add(country.getName());
//     		   //?
//     	   }
//		   while(!arcs.isEmpty() && seekSupporSet){
//		        arc = arcs.remove();
//		        if(this.removeUnusedDomainValues(arc)){
//		            List<Country> neighbours = arc.first.getNeighbours();
//		            for(Country neighbour : neighbours){
//		                arcs.add(new Pair<>(neighbour, arc.first));
//		            }
//		        }
//		    }
//		    return map;
//    }
//		
//    private Queue<Pair<Country,Country>> SeekSupportSet(Map map){
//    	  boolean found = false;
//        Queue<Pair<Country,Country>> arcs = new LinkedList<>();
//        for(Country country : map.getCountries()){
//            for(Country neighbour : country.getNeighbours()){
//                Pair<Country,Country> arc = new Pair<>(country, neighbour);
//                arcs.add(arc);
//            }
//        }      
//        return arcs;
//    }
	
    
}
