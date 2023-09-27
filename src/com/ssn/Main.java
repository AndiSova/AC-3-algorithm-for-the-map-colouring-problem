package com.ssn;

import static com.ssn.FileUtils.writeFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	private static final String FILE_NAME = "map.txt";
	private static final String EXIT = "exit";
	private static final String AC_3 = "3";
	private static final String AC_7 = "7";
	private ArrayList<Country> countries = new ArrayList<>();
	private Keyboard kb = new Keyboard();

    public static void main(String[] args) {
    	Main main = new Main();
    	main.run();
    }
    
    private void run() {
    	File f = new File("./" + FILE_NAME);
        if (f.exists()) {
        	countries = FileUtils.readFile(FILE_NAME);
        } else {
        	writeFile(FILE_NAME, FileMapGenerator.createMapLines());
        	countries = FileUtils.readFile(FILE_NAME);
        }
        while(true) {
        	String input = kb.readString("Select type of ArcConsistency: ");
        	if(handleInput(input).equals("0")) {
        		break;
        	}
        }
    	
    }

	private String handleInput(String input) {
    	Map map = new Map(countries);
    	List<String> colors = new ArrayList<>(Arrays.asList("red", "green", "blue"));
    	switch (input) {
		case AC_3: 
			MapColoringProblem mapColoringAC_3 = new MapColoringProblem(map, colors, AC_3);
			mapColoringAC_3.colorMap();
			System.out.println("AC3 was used. Check the result.txt file for the results.");
			break;
		case AC_7:
			System.out.println("I didnt get the algorithm from C. Bessiere, E.C. Freuder, and J.-R. Régin, in Artificial Intelligence 107, pages 125-148, 1999.");
			break;
		case EXIT:
			return "0";
		default:
			System.out.println("Unexpected Value. To exit enter 'exit'.");
		}
		return "1";
	}
}
