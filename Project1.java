import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Project1 {
	public static boolean isNumeric(String str) { //Ckeck if input is number
        try {
            Integer.parseInt(str);  
            return true; //return true if it is number
        } catch (NumberFormatException e) {
            return false; // return false if it is not number  
        }
    }
	static ArrayList <Integer> Simulate(int controlTime, int numOfFloors, int maxPass, PrintWriter writer){//function which start simulate
		TreeMap <Integer, Integer> countCalls = new TreeMap<>(); // I use TreeMap to control the order of the floors
		int time = 0; // time of simulation
		int timePassin = 2; // time for each passenger to come in
		int timePassout = 1; // time for each passenger to go out
		int timeDelay = 1; //time to pass 1 floor with each of passenger
		int timeFloor = 3;//time to pass 1 floor
		int passenger = 1;// current number of passenger
		int floor = 1;// current floor
		int minute = 0;// time in minute
		int seconds = 0;//time in seconds
		for(int i = 0; i < numOfFloors; i++){// create lists of calls every floor
			countCalls.put(i+1, 0);
		}
			while(time/60 <= controlTime){
				Random random = new Random(); // to call from random floor
				int randomFloor = random.nextInt(numOfFloors) + 1; // add 1 to start from floor 1 and include last floor
				int randomPassengerIn = random.nextInt(maxPass) + 1;// number of passenger which want to enter the elevator
				int randomPassengerOut = random.nextInt(passenger);// number of passenger which want to get into the elevator
				writer.println("____________________________________________________________________________________");
				writer.println("Passengers currently in the elevator: " + passenger);
				writer.println("Currently floor: " + floor);
				writer.println("Passenger called elevator from floor: " + randomFloor);
				int timeForWait = passenger * timeDelay + Math.abs(randomFloor - floor)*timeFloor;// time for waiting elevator with current passenger from current floor
				writer.println("Please, wait: " + timeForWait + " seconds");
				writer.println("How many passengers came out: " + randomPassengerOut);
				writer.println("How many passengers came in: " + randomPassengerIn);
				int allow = passenger - randomPassengerOut + randomPassengerIn; // control maximum passenger in the elevator
				countCalls.put(randomFloor, countCalls.getOrDefault(randomFloor, 0) + 1); // if there was already a call from the floor then counter is growing
				if(allow > maxPass){
					writer.println("!!!Sorry, the elevator can't handle so many passengers.\nMaximum passengers per trip: " + maxPass+" !!!");
					writer.println("Will only be included in the allowed values: " + (maxPass-(allow - randomPassengerIn)));
					passenger = maxPass;// because if "allow" more than maximum passenger then only MaxPass can enter the elevator
				}else{
					passenger = allow;	
				}
				floor = randomFloor; //to save current floor
				time += timeForWait + randomPassengerIn * timePassin + randomPassengerOut * timePassout;//all time in simulation
				minute = time / 60;
                seconds = time % 60;
				writer.println("Time of simulation: " + minute + " minute, " + seconds + " seconds" );
			}
		
		Collection<Integer> values = countCalls.values(); //to save all calls and return with time
        ArrayList<Integer> result = new ArrayList<>(values);
        result.add(minute);
		result.add(seconds);
		
		return result; // return result
	} 
    public static void main(String[] args) {
		boolean control = true;// to stop programm if user doesn't want continue 
		while (control){
			System.out.println("Welcome to Elevator Simulation!\n\nInitializing elevator system...");
			Scanner input = new Scanner(System.in);
			ArrayList<Integer> arr = new ArrayList<>(); // to save input from user
        	Path filePath = Path.of("input3.txt");
			String finish = ""; //string for saving decision of "Do you want try again?" (y/n)
        	try {
            	List<String> lines = Files.readAllLines(filePath);
				int i = 1;
            	for (String line : lines) {
					if (i == 4) break;
					if(isNumeric(line)){ //this function check if it is num 
                	     arr.add(Integer.parseInt(line.trim()));
					}else if(i == 4){
						finish += line.trim();
					}
					i++;
            	}
        	} catch (IOException e) {
            	System.out.println("Error: " + e.getMessage());
			}
			System.out.println("Number of floors: ");
			int numOfFloors = arr.get(0);//get from the array
			System.out.println("Maximum passengers per trip: ");
			int maxPass = arr.get(1);
			System.out.println("Simulation duration in minutes: ");
			int controlTime = arr.get(2);
	    	System.out.println("Simulating passenger calls...");
			
			try (PrintWriter writer = new PrintWriter("output3.txt")) {
				ArrayList<Integer> result = new ArrayList<>(Simulate(controlTime, numOfFloors, maxPass, writer));//call function Simulate()
				writer.println("\n\nSimulation completed.\n\nAnalysis:\nTotal time needed for all calls: " + (result.get(result.size() - 2)) + " minute " + (result.get(result.size() - 1)) + " seconds\nFrequency of calls from each floor:");
            	for(int i = 0; i < result.size() - 2; i++){//loop for saving result in file
					writer.println("Floor "+(i+1)+" : " + result.get(i));
				}
				writer.println("\n\nDo you want try again?(y/n): ");//if user want to stop programm
				if(finish.equalsIgnoreCase("n") || finish.equalsIgnoreCase("no")){
					control = false;
					writer.println("Thank you for using Elevator Simulation!");
				}else if(finish.equalsIgnoreCase("y") || finish.equalsIgnoreCase("yes")){
					control = true;
				}else{
					writer.println("Please enter correct input!");//if user input chars or num 
				}
			}catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
		}
    }
}
