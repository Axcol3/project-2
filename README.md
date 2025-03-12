# **Elevator Simulation**  

## **Documentation**  

### **1. Project Description**  
This project is an **elevator simulation**. It shows how an elevator works in a building with **many floors** and **many passengers**.  

### **2. Design Choices**  
- The program **reads input from a file** (`input1.txt`).  
- The elevator moves **to random floors** where passengers call it.  
- The program **writes results to a file** (`output1.txt`).  

### **3. Algorithms and Data Structures**  
- The program uses **TreeMap** to count how many times each floor calls the elevator.  
- It uses **Random numbers** to decide which floor calls the elevator.  
- It calculates **waiting time** based on the number of passengers and the floor distance.  

### **4. Improvements**  
- The program **checks if the input is correct** before using it.  
- It **prevents too many passengers** from entering the elevator.  
- It **ensures all floors appear** in the final report, even if no one calls from them.  

### **5. Input and Output Files**  
- **Input File (`input1.txt`)**:  
  - Number of floors  
  - Maximum passengers  
  - Simulation time (in minutes)  
  - (Optional) "yes" or "no" to restart the simulation  

- **Output File (`output1.txt`)**:  
  - Simulation details (floors, passengers, waiting time)  
  - Total time for the simulation  
  - Number of calls from each floor  

### **6. Additional Notes**  
- If the input file is missing, the program asks the user for input.  
- The simulation runs **until the user decides to stop**.  
- The program helps understand **how an elevator works in real life**.  

# Elevator Simulation Project

This is a screenshot of the elevator simulation in action:

![Elevator Simulation Screenshot](images/screenshot.png)
![Elevator Simulation Screenshot](images/screenshot1.png)
