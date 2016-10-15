package assignment4;

import java.util.Scanner;

public class Controller {

	public boolean quit;
	Scanner keyboard;
	
	public Controller (Scanner kb){
		quit = false;
		keyboard = kb;
	}

	/*
	 * Prompts the user for a command through Scanner
	 * Handles the various commands
	 */
	public void promptInput(){
		System.out.print("critters>");
		String input = keyboard.nextLine();
		
		String[] commands = input.split(" ");
		
		for (String command : commands){
			command.trim();
		}
		
		//TODO: handle exceptions
		
		switch (commands[0]){
		case "quit":
			quit = true;
			break;
		case "show":
			Critter.displayWorld();
			break;
		case "seed":
			Critter.setSeed(Long.parseLong(commands[1]));
			break;
		case "step":
			int count = 1;
			if (commands.length > 1){
				count = Integer.parseInt(commands[1]);
			}
			while (count > 0){
				count--;
				Critter.worldTimeStep();
			}
			break;
		}
	}
	
	
}
