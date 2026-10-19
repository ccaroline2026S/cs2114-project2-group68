package adventurerGo;
import java.util.Scanner;

public class MainMenu
{
        private TripManager tripManager;
        private Scanner scanner;

        // o(1) because no loops 
        public MainMenu() {
            tripManager = new TripManager();
            scanner = new Scanner(System.in);
        }

        // Menu actions that search or display the trip take O(n).
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
                System.out.println("10: Link or unlink an activity and destination");
                System.out.println("11: Set destination cost (excluding activities)");
                System.out.println("12: Set activity cost");
                System.out.println("13: Estimate total trip cost");
                System.out.println(" ");
                System.out.println("Your Selection: ");
                //reads an entire line of input from user 
                if (!scanner.hasNextLine()) {
                    return;
                }
                String selection = scanner.nextLine();
                try {
                
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
                    else if (selection.equals("10")) {
                        associateActivity();
                    }
                    else if (selection.equals("11")) {
                        setCost(true);
                    }
                    else if (selection.equals("12")) {
                        setCost(false);
                    }
                    else if (selection.equals("13")) {
                        System.out.printf(java.util.Locale.US,
                            "Estimated trip cost: %.2f%n", tripManager.estimateTripCost());
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
                            + " Please select a number from the list.");
                    }
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                catch (java.util.NoSuchElementException e) {
                    return;
                }
            }
        }

        // o(n) for each every destination and activity it prints it
        private void displayTrip()
        {
            tripManager.displayTrip();
            
        }

        // o(n) checks for an existing destination with the same name
        private void addDestination()
        {
            System.out.println("Enter your next destination name: ");
            String name = readName();
            
            System.out.println("Enter your next destination description: ");
            String description = scanner.nextLine();
            
            
            //cast and create a new destination object this may not be needed
            //depending on how trip manager is 
            Destination destination = new Destination(name, description);
            if (!tripManager.addDestination(destination)) {
                System.out.println("Destination name already exists.");
                return;
            }
            
            System.out.println("You are one step closer to your vacation! "
                + name + " has been successfully added!");
            
        }

        // o(n) chain has to check every node when search is called 
        private void removeDestination()
        {
            System.out.println("Please enter what destination you want to remove: ");
            String name = readName();
            
            
            Destination destination = new Destination(name, "");

            if (!tripManager.searchDestination(destination)) {
                System.out.println("Destination cannot be found!");
                return;
            }
            
            //cast
            tripManager.removeDestination(destination);
            
            System.out.println( name + " has been successfully removed!");
            
        }

        // o(n) chain has to check every node when search is called 
        private void editDestination()
        {
            System.out.println("Please enter what destination you want to edit: ");
            String oldName = readName();
            
            
     
            if (!tripManager.searchDestination(new Destination(oldName, ""))) {
                System.out.println("Destination cannot be found!");
                return;
            }
            
            //cast
            System.out.println("Enter the edited destination name: ");
            String newName = readName();
            
            System.out.println("Enter the edited destination description: ");
            String newDescription = scanner.nextLine();
            
            
            //depends on trip manager
            Destination oldDestination = new Destination(oldName, "");
            Destination newDestination = new Destination(newName, newDescription);
            
            if (!tripManager.editDestination(oldDestination, newDestination)) {
                System.out.println("Name already exists or item was not found.");
                return;
            }
            
            System.out.println(oldName + " has been successfully edited to "
                + newName + ".");
            //is there a way to get the new destination 
            //name the user changes it to printed??
            
        }

         // o(n) chain has to check every node when search is called 
        private void searchDestination()
        {
            System.out.println("Please enter what destination you want to search for: ");
            String name = readName();
            
            
            Destination destination = new Destination(name, "");
            
            //if found
            if (tripManager.searchDestination(destination)) {
                System.out.println(name + " was found in your trip.");
            }
            
            else {
                System.out.println(name + " could not be found in your trip.");   
            }
        }

         // o(n) checks for an existing activity with the same name
        private void addActivity()
        {
            System.out.println("Enter your new activity name: ");
            String name = readName();
            
            System.out.println("Enter your new activity decscription: ");
            String description = scanner.nextLine();
            
            
            //cast and create a new destination object this may not be needed
            //depending on how trip manager is 
            Activity activity = new Activity(name, description);
            if (!tripManager.addActivity(activity)) {
                System.out.println("Activity name already exists.");
                return;
            }
            
            System.out.println("You're vacation just got more fun! "
                + name + " has been successfully added!");
            
        }

         // o(n) chain has to check every node when search is called 
        private void removeActivity()
        {
            System.out.println("Please enter what activity you want to remove: ");
            String name = readName();
            
            
            Activity activity = new Activity(name, "");

            if (!tripManager.searchActivity(activity)) {
                System.out.println("Activity cannot be found!");
                return;
            }
            
            //cast
            tripManager.removeActivity(activity);
            
            System.out.println( name + " has been successfully removed!");
          
        }

         // o(n) chain has to check every node when search is called 
        private void editActivity()
        {
            System.out.println("Please enter what activity you want to edit: ");
            String oldName = readName();
            
            
     
            if (!tripManager.searchActivity(new Activity(oldName, ""))) {
                System.out.println("Activity cannot be found!");
                return;
            }
            
            System.out.println("Enter the edited activity name: ");
            String newName = readName();
            
            System.out.println("Enter the edited activity decription: ");
            String newDescription = scanner.nextLine();
            
            //depends on trip manager
            Activity oldActivity = new Activity(oldName, "");
            Activity newActivity = new Activity(newName, newDescription);
            
            if (!tripManager.editActivity(oldActivity, newActivity)) {
                System.out.println("Name already exists or item was not found.");
                return;
            }
            
            System.out.println(oldName + " has been successfully edited to "
                + newName + ".");
        }

         // o(n) chain has to check every node when search is called 
        private void searchActivity()
        {
            System.out.println("Please enter what activity you want to search for: ");
            String name = readName();
            
            
            Activity activity = new Activity(name, "");
            
            //if found
            if (tripManager.searchActivity(activity)) {
                System.out.println(name + " was found in your trip.");
            }
            
            else {
                System.out.println(name + " could not be found in your trip.");   
            }
            
        }   

        // Uses the same name rules as the model and keeps asking on bad input.
        private String readName() {
            while (true) {
                String name = scanner.nextLine();
                try {
                    new Destination(name, "");
                    return name;
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Name cannot be blank or only a number. Try again:");
                }
            }
        }

        private void associateActivity() {
            System.out.println("Activity name:");
            Activity activity = new Activity(readName(), "");
            System.out.println("Destination name (leave blank to unlink):");
            String name = scanner.nextLine();
            Destination destination = name.isBlank() ? null : new Destination(name, "");
            if (tripManager.associateActivity(activity, destination)) {
                System.out.println("Activity destination updated.");
            }
            else {
                System.out.println("Activity or destination was not found.");
            }
        }

        private void setCost(boolean destination) {
            System.out.println(destination ? "Destination name:" : "Activity name:");
            String name = readName();
            while (true) {
                System.out.println("Enter a nonnegative cost in your trip's currency:");
                String input = scanner.nextLine();
                try {
                    double cost = Double.parseDouble(input);
                    if (!Double.isFinite(cost) || cost < 0) {
                        throw new IllegalArgumentException();
                    }
                    boolean updated = destination
                        ? tripManager.setDestinationCost(new Destination(name, ""), cost)
                        : tripManager.setActivityCost(new Activity(name, ""), cost);
                    System.out.println(updated ? "Cost updated." : "Item was not found.");
                    return;
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Invalid cost. Enter a finite, nonnegative number.");
                }
            }
        }

         // o(1) creates one object and starts application
        public static void main(String[] args) {
            MainMenu menu = new MainMenu();
            menu.run();
        }
    }
