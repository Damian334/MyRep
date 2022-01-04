package com.revature.datatype;

public class FlowControl {
	
	public void flowControl() {
		
		boolean myBoolean = true; 
		int i = 5;
		
		if (myBoolean) {
			// This code runs if myBoolean is true
		} else if (i > 5) {
			// Code runs if i is greater than 5
		} else {
			// This code runs if the previous if-block did not, ie the if was false
			// This is the "catch-all" block of code that runs if no other block runs
		}
		
		// While loops are meant to run some number of loops until the boolean check is false
		while (myBoolean) {
			// This code will run indefinitely, since myBoolean will remain true
			
			// Unless we do 1 of 2 things
			
			myBoolean = false;
			// OR
			break; // Stops and exits a loop/block
		}
		
		// Do-while loops always run at least once
		do {
			// Loop code here
		} while (myBoolean);
		
		// Loops with a numerical requirement, eg x number of iterations, require a for-loop
		String[] names = { "Jacob", "Daniel", "Allison" }; // This is called array initialization and allows us to create 
		//   an array and populate it with data.
		String[] otherNames = new String[10]; // Old array construction requires the size to be specified (10 here)
		
		for (int iteration = 0; iteration < names.length; ++iteration) { // for (variable initialization; condition to continue looping; increment variable
			System.out.println(names[iteration]); // names[iteration] -> element of names with index equal to the value of iteration
		}
		
		// Enhanced for-loop; runs for each element in the array, collection, etc
		for (String s : names) { // for (type arbitraryVariableName : collection/array of same type) 
			System.out.println(s);
		}
		
		// Switch statement -> think of a switchboard
		// Useful when you have a limited number of expected values
		// Replaces an extended if-else-if block
		String name = names[0];
		switch (name) {
		case "Jacob":
			System.out.println("Wow its Jacob!");
			// jacob();
			// break;
		
		// If there is no break, the switch statement will "fall through" to the next case
			
		case "Daniel":
			System.out.println("Woah its Daniel!");
			// daniel();
			break;
			
		default:
			// Code for when no applicable case was found; think error handling
			// Not required in order to compile
			System.out.println("Oh no, no other case was found!");
		}
	}
}
