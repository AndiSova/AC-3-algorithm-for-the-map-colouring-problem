package com.ssn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	private static ArrayList<Country> countries = new ArrayList<>();
 	
	public static void writeFile(String fileName, Object... data) {
		try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
			for(Object obj : data) {
				pw.println(obj.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Country> readFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			while(true) {
				String s = br.readLine();
				if(s == null) {
					break;
				}
				readLine(s);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return countries;
	}

	private static void readLine(String line) {
		if(!line.contains(",")) {
			countries.add(new Country(line));
		}
		parseLines(line);
	}

	private static void parseLines(String line) {
		String[] splits = line.split(",");
		if(countryAlreadyExists(splits[0])) {
			setNeighbours(splits, getCountryByName(splits[0]));
		} else {
			Country country = new Country(splits[0]);
			countries.add(country);
			setNeighbours(splits, country);
		}
	}

	private static Country setNeighbours(String[] splits, Country country) {
		List<Country> neighbouringCountries = new ArrayList<>();
		for(int i = 1 ; i < splits.length ; i++) {
			if(countryAlreadyExists(splits[i])) {
				neighbouringCountries.add(getCountryByName(splits[i]));
			} else {
				Country c = new Country(splits[i]);
				countries.add(c);
				neighbouringCountries.add(c);
			}
		}
		country.setNeighbours(neighbouringCountries);
		return country;
	}

	private static Country getCountryByName(String name) {
		for(Country c : countries) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	private static boolean countryAlreadyExists(String name) {
		for(Country c : countries) {
			if(c.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
