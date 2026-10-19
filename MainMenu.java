package adventurerGo;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 *  Shows all the information for main menu which calls all the methods 
 *  and prompts the user for input and shows the display screen
 * 
 *  @author CS Group
 *  @version Oct 19, 2026
 */
public class MainMenu
{
        private TripManager tripManager;
        private Scanner scanner;
        
        // ----------------------------------------------------------
        /**
         * Create a new MainMenu object.
         */
        public MainMenu() {
            tripManager = new TripManager();
            scanner = new Scanner(System.in);
        }
        
        // ----------------------------------------------------------
        /**
         * Runs the program by setting it to true and create the screen for
         * the user to select what the want to do and calls methods based on 
         * selection
         */
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
                System.out.println("9:  Connect Activity to Destination");
                System.out.println("10: Add Cost to Destination");
                System.out.println("11: Add Cost to Activity");
                System.out.println("12: Estimate Trip Cost");
                System.out.println("13: Exit");
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
                    associateActivity();
                }
                else if (selection.equals("10")) {
                    setDestinationCost();
                }
                else if (selection.equals("11")) {
                    setActivityCost();
                }
                else if (selection.equals("12")) {
                    estimateTripCost();
                }
                else if (selection.equals("13")) {
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
                        + " Please select a number from the list.");
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
            
            if (name.isEmpty()) {
                System.out.println("Destination cannot be an empty name!");
                return;
            }
            
            System.out.println("Enter your next destination description: ");
            String description = scanner.nextLine();
            
            Destination destination = new Destination(name, description);
            tripManager.addDestination(destination);
            
            System.out.println("You are one step closer to your vacation! "
                + name + " has been successfully added!");
            
        }

        private void removeDestination()
        {
            System.out.println("Please enter what destination you want to remove: ");
            String name = scanner.nextLine();
            
            
            if (name.isEmpty()) {
                System.out.println("Destination cannot be an empty name!");
                return;
            }
            Destination destination = new Destination(name, "");

            if (!tripManager.searchDestination(destination)) {
                System.out.println("Destination cannot be found!");
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
            
            
            if (oldName.isEmpty()) {
                System.out.println("Destination name cannot be an empty!");
                return;
            }
     
            if (!tripManager.searchDestination(new Destination(oldName, ""))) {
                System.out.println("Destination cannot be found!");
                return;
            }
            
            //cast
            System.out.println("Enter the edited destination name: ");
            String newName = scanner.nextLine();
            
            System.out.println("Enter the edited destination description: ");
            String newDescription = scanner.nextLine();
            
            if (newName.isEmpty()) {
                System.out.println("New name cannot be empty");
                return;
            }
            
            //depends on trip manager
            Destination oldDestination = new Destination(oldName, "");
            Destination newDestination = new Destination(newName, newDescription);
            
            tripManager.editDestination(oldDestination, newDestination);
            
            System.out.println(oldName + " has been successfully edited to "
                + newName + ".");
            //is there a way to get the new destination 
            //name the user changes it to printed??
            
        }

        private void searchDestination()
        {
            System.out.println("Please enter what destination you want to search for: ");
            String name = scanner.nextLine();
            
            if (name.isEmpty()) {
                System.out.println("Destination cannot be an empty name!");
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
            
            if (name.isEmpty()) {
                System.out.println("Activity cannot be an empty name!");
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
            
            
            if (name.isEmpty()) {
                System.out.println("Activity cannot be an empty name!");
                return;
            }
            Activity activity = new Activity(name, "");

            if (!tripManager.searchActivity(activity)) {
                System.out.println("Activity cannot be found!");
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
            
            
            if (oldName.isEmpty()) {
                System.out.println("Activity name cannot be an empty!");
                return;
            }
     
            if (!tripManager.searchActivity(new Activity(oldName, ""))) {
                System.out.println("Activity cannot be found!");
                return;
            }
            
            System.out.println("Enter the edited activity name: ");
            String newName = scanner.nextLine();
            
            System.out.println("Enter the edited activity decription: ");
            String newDescription = scanner.nextLine();
            
            if (newName.isEmpty()) {
                System.out.println("New name cannot be empty");
                return;
            }
            //depends on trip manager
            Activity oldActivity = new Activity(oldName, "");
            Activity newActivity = new Activity(newName, newDescription);
            
            tripManager.editActivity(oldActivity, newActivity);
            
            System.out.println(oldName + " has been successfully edited to "
                + newName + ".");
        }

        private void searchActivity()
        {
            System.out.println("Please enter what activity you want to search for: ");
            String name = scanner.nextLine();
            
            if (name.isEmpty()) {
                System.out.println("Activity cannot be an empty name! ");
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
        
        private void associateActivity() {
            System.out.println("Please enter which activity you would to "
                + "connect to a destination: ");
            String activityName = scanner.nextLine();
        
            if (activityName.isEmpty()) {
                System.out.println("Activity cannot be an empty name! ");
                return; 
            }
            
            System.out.println("Please enter the destination name you would to "
                + "connect the activity to: ");
            String destinationName = scanner.nextLine();
            
            if (destinationName.isEmpty()) {
                System.out.println("Destination cannot be an empty name! ");
                return; 
            }
            
            Activity activity = new Activity(activityName, "");
            Destination destination = new Destination(destinationName, "");
            
            
            if (tripManager.associateActivity(activity, destination)) {
                System.out.println("Activity connected successfully!");
            }
            else {
                System.out.println("Activity or Destination could not be found.");
            }
        }
        
        private void setDestinationCost() {
            System.out.println("Enter destination name: ");
            String name = scanner.nextLine();
            
            if (name.isEmpty()) {
                System.out.println("Destination cannot be an empty name! ");
                return; 
            }
            
            System.out.println("Enter destination cost: ");

            try {
                double cost = Double.parseDouble(scanner.nextLine());

                Destination destination = new Destination(name, "");

                if (tripManager.setDestinationCost(destination, cost)) {
                    System.out.println("Destination cost updated!");
                }
                else {
                    System.out.println("Destination not found!");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(
                    e.getMessage());
            }
        }
        
        private void setActivityCost() {
            System.out.println("Enter activity name: ");
            String name = scanner.nextLine();
            
            if (name.isEmpty()) {
                System.out.println("Activity cannot be an empty name! ");
                return; 
            }
            
            System.out.println("Enter activity cost: ");

            try {
                double cost = Double.parseDouble(scanner.nextLine());

                Activity activity = new Activity(name, "");

                if (tripManager.setActivityCost(activity, cost)) {
                    System.out.println("Activity cost updated!");
                }
                else {
                    System.out.println("Activity not found!");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        private void estimateTripCost() {
            double cost = tripManager.estimateTripCost();
            System.out.println("Estimated Trip Cost: $" + cost);
        }
        
        // ----------------------------------------------------------
        /**
         * Calls the entire program and makes run true on main
         * @param args
         */
        public static void main(String[] args) {
            MainMenu menu = new MainMenu();
            menu.run();
        }
    }


