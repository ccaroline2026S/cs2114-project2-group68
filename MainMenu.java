package adventurerGo;

import java.util.Scanner;

public class MainMenu
{
        private TripManager tripManager;
        private Scanner scanner;
        
        public MainMenu() {
            tripManager = new TripManager();
            scanner = new Scanner(System.in);
        }
        
        public void run() {
            boolean run = true;
            System.out.println("*    Welcome to AdventurerGo!    *");
            System.out.println("The app that makes traveling easy!");
            
            //will run until run is no longer true
            while(run) {
                System.out.println(" ");
                System.out.println("Please select a number to start your adventure");
                System.out.println("0:  Show my Adventure");
                System.out.println("1:  Add Destination");
                System.out.println("2:  Remove Destination");
                System.out.println("3:  Edit Destination");
                System.out.println("4:  Search Destination");
                System.out.println("5:  Add Activity");
                System.out.println("6:  Remove Activity");
                System.out.println("7:  Edit Activity");
                System.out.println("8:  Search Activity");
                System.out.println("9:  exit");
                System.out.println(" ");
                System.out.println("Your Selection: ");
                //reads an entire line of input from user 
                String selection = scanner.nextLine();
                
                if (selection.equals("0")) {
                    displayTrip();
                }
                else if (selection.equals("1")) {
                    addDestination();
                }
                else if (selection.equals("2")) {
                    removeDestination();
                }
                else if (selection.equals("3")) {
                    editDestination();
                }
                else if (selection.equals("4")) {
                    searchDestination();
                }
                else if (selection.equals("5")) {
                    addActivity();
                }
                else if (selection.equals("6")) {
                    removeActivity();
                }
                else if (selection.equals("7")) {
                    editActivity();
                }
                else if (selection.equals("8")) {
                    searchActivity();
                }
                else if (selection.equals("9")) {
                    //if user selects 9...
                    //changes run to false which breaks out of while loop
                    run = false;
                    System.out.println(" ");
                    System.out.println("Thanks for creating your next adventure "
                        + "with AdventurerGo");
                    System.out.println(" ");
                }
                else {
                    System.out.println("Invalid Input!"
                        + "Please select a number from the list.");
                }
            }
        }

        private void displayTrip()
        {
            tripManager.displayTrip();
            
        }

        private void addDestination()
        {
            System.out.println("Enter your next destination name: ");
            String name = scanner.nextLine();
            
            System.out.println("Enter your next destination description: ");
            String description = scanner.nextLine();
            
            if (name.isBlank()) {
                System.out.println("Destination cannot be an empty name!"
                    + "Please enter a destination name: ");
                return;
            }
            
            //cast and create a new destination object this may not be needed
            //depending on how trip manager is 
            Destination destination = new Destination(name, description);
            tripManager.addDestination(destination);
            
            System.out.println("You are one step closer to your vacation!"
                + name + " has been successfully added!");
        }

        private void removeDestination()
        {
            System.out.println("Please enter what destination you want to remove: ");
            String name = scanner.nextLine();
            
            
            if (name.isBlank()) {
                System.out.println("Destination cannot be an empty name!"
                    + "Please enter a destination name you want to remove: ");
                return;
            }
            Destination destination = new Destination(name, "");

            if (!tripManager.searchDestination(destination)) {
                System.out.println("Destination cannot be found!"
                    + "Please enter a valid destination you want to remove: ");
                return;
            }
            
            //cast
            tripManager.removeDestination(destination);
            
            System.out.println( name + " has been successfully removed!");
            
        }

        private void editDestination()
        {
            System.out.println("Please enter what destination you want to edit: ");
            String oldName = scanner.nextLine();
            
            
            if (oldName.isBlank()) {
                System.out.println("Destination name cannot be an empty!"
                    + "Please enter a destination name you want to edit: ");
                return;
            }
     
            if (!tripManager.searchDestination(new Destination(oldName, ""))) {
                System.out.println("Destination cannot be found!"
                    + "Please enter a valid destination you want to edit: ");
                return;
            }
            
            //cast
            System.out.println("Enter the edited destination name: ");
            String newName = scanner.nextLine();
            
            System.out.println("Enter the edited destination description: ");
            String newDescription = scanner.nextLine();
            
            if (newName.isBlank()) {
                System.out.println("New name cannot be empty");
                return;
            }
            
            //depends on trip manager
            Destination oldDestination = new Destination(oldName, "");
            Destination newDestination = new Destination(newName, newDescription);
            
            tripManager.editDestination(oldDestination, newDestination);
            
            System.out.println(oldName + " has been successfully edited "
                + newName + ".");
            //is there a way to get the new destination 
            //name the user changes it to printed??
            
        }

        private void searchDestination()
        {
            System.out.println("Please enter what destination you want to search for: ");
            String name = scanner.nextLine();
            
            if (name.isBlank()) {
                System.out.println("Destination cannot be an empty name!"
                    + "Please enter a destination name you want to search for: ");
                return;
            }
            
            Destination destination = new Destination(name, "");
            
            //if found
            if (tripManager.searchDestination(destination)) {
                System.out.println(name + " was found in your trip.");
            }
            
            else {
                System.out.println(name + " could not be found in your trip.");   
            }
        }

        private void addActivity()
        {
            System.out.println("Enter your new activity name: ");
            String name = scanner.nextLine();
            
            System.out.println("Enter your new activity decscription: ");
            String description = scanner.nextLine();
            
            if (name.isBlank()) {
                System.out.println("Activity cannot be an empty name!"
                    + "Please enter a activity name: ");
                return;
            }
            
            //cast and create a new destination object this may not be needed
            //depending on how trip manager is 
            Activity activity = new Activity(name, description);
            tripManager.addActivity(activity);
            
            System.out.println("You're vacation just got more fun! "
                + name + " has been successfully added!");
            
        }

        private void removeActivity()
        {
            System.out.println("Please enter what activity you want to remove: ");
            String name = scanner.nextLine();
            
            
            if (name.isBlank()) {
                System.out.println("Activity cannot be an empty name!"
                    + "Please enter a activity name you want to remove: ");
                return;
            }
            Activity activity = new Activity(name, "");

            if (!tripManager.searchActivity(activity)) {
                System.out.println("Activity cannot be found!"
                    + "Please enter a valid activity you want to remove: ");
                return;
            }
            
            //cast
            tripManager.removeActivity(activity);
            
            System.out.println( name + " has been successfully removed!");
          
        }

        private void editActivity()
        {
            System.out.println("Please enter what activity you want to edit: ");
            String oldName = scanner.nextLine();
            
            
            if (oldName.isBlank()) {
                System.out.println("Activity name cannot be an empty!"
                    + "Please enter a activity name you want to edit: ");
                return;
            }
     
            if (!tripManager.searchActivity(new Activity(oldName, ""))) {
                System.out.println("Activity cannot be found!"
                    + "Please enter a valid activity you want to edit: ");
                return;
            }
            
            System.out.println("Enter the edited activity name: ");
            String newName = scanner.nextLine();
            
            System.out.println("Enter the edited activity decription: ");
            String newDescription = scanner.nextLine();
            
            if (newName.isBlank()) {
                System.out.println("New name cannot be empty");
                return;
            }
            //depends on trip manager
            Activity oldActivity = new Activity(oldName, "");
            Activity newActivity = new Activity(newName, newDescription);
            
            tripManager.editActivity(oldActivity, newActivity);
            
            System.out.println(oldName + " has been successfully edited "
                + newName + ".");
        }

        private void searchActivity()
        {
            System.out.println("Please enter what activity you want to search for: ");
            String name = scanner.nextLine();
            
            if (name.isBlank()) {
                System.out.println("Activity cannot be an empty name!"
                    + "Please enter a activity name you want to search for: ");
                return;
            }
            
            Activity activity = new Activity(name, "");
            
            //if found
            if (tripManager.searchActivity(activity)) {
                System.out.println(name + " was found in your trip.");
            }
            
            else {
                System.out.println(name + " could not be found in your trip.");   
            }
            
        }   
        public static void main(String[] args) {
            MainMenu menu = new MainMenu();
            menu.run();
        }
    }
