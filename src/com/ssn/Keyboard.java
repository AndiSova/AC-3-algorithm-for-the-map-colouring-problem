package com.ssn;

import java.util.Scanner;

public class Keyboard {
	private Scanner scanner = new Scanner(System.in);
	
	public String readString(String msg) {
		System.out.println(msg);
		return scanner.nextLine();
	}
}
