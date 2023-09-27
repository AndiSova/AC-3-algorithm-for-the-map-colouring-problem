package com.ssn;

import java.util.List;

class MapColoringProblem {
	
    private Map map;
    private String acType;
    private static final String OUTPUT_FILE = "result.txt";

    MapColoringProblem(Map map, List<String> colors, String acType){
        this.map = map;
        this.acType = acType;
        map.setDomains(colors);
    }

    void colorMap(){
    	ArcConsistency arcConsistency = new ArcConsistency(this.map);
        Map result = arcConsistency.solve(this.acType);
        FileUtils.writeFile(OUTPUT_FILE, result);
    }
}