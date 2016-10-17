/* CRITTERS Controller.java
 * EE422C Project 4 submission by
 * <Ahsan Khan>
 * <ajk2723>
 * <16445>
 * <Cedric Debelle>
 * <cfd363>
 * <16445>
 * Slip days used: <0>
 * Fall 2016
 */

package assignment4;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

public class Controller {

	public boolean quit;
	Scanner keyboard;

	public Controller(Scanner kb) {
		quit = false;
		keyboard = kb;
	}

	/**
	 * Prompts the user for a command through Scanner Handles the various
	 * commands
	 */
	public void promptInput() {
		System.out.print("critters>");
		String input = keyboard.nextLine();

		try {
			String[] commands = input.split(" ");

			for (String command : commands) {
				command.trim();
			}

			switch (commands[0]) {
			case "s": // TODO: REMOVE
				Critter.worldTimeStep();
				Critter.displayWorld();
				break;
			case "quit":
				if (commands.length > 1)
					throw new IllegalArgumentException();
				quit = true;
				break;
			case "show":
				if (commands.length > 1)
					throw new IllegalArgumentException();
				Critter.displayWorld();
				break;
			case "seed":
				if (commands.length > 2)
					throw new IllegalArgumentException();
				Critter.setSeed(Long.parseLong(commands[1]));
				break;
			case "stats":
				if(commands.length > 2) throw new IllegalArgumentException();
				List<Critter> instances = Critter.getInstances(commands[1]);
				if (instances.size() < 1)
					return;
				Critter c = instances.get(0);
				Class<?>[] types = { List.class };
				c.getClass().getMethod("runStats", types).invoke(c, instances);
				break;
			case "make":
				int count = 1;
				if(commands.length > 3) throw new IllegalArgumentException();
				if (commands.length > 2) {
					count = Integer.parseInt(commands[2]);
				}
				while (count > 0) {
					count--;
					Critter.makeCritter(commands[1]);
				}
				break;
			case "step":
				count = 1;
				if(commands.length > 2) throw new IllegalArgumentException();
				if (commands.length > 1) {
					count = Integer.parseInt(commands[1]);
				}
				while (count > 0) {
					count--;
					Critter.worldTimeStep();
				}
				break;
			default:
				throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			System.out.println("error processing: " + input);
		} catch (Error e){
			System.out.println("error processing: " + input);
		}
	}

}
