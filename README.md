# cs2114-project2-group68
# Reflection 
What changed from your specification? 

In our spec we had not quite figured out how our name and description would work together but once implementing we fully committed to Destination and Activity having two fields. The MainMenu class remained responsible for user interaction but additional validation and re-prompts were added before input data is passed to the TripManager. Validation for empty names is preformed in MainMenu and Destination and Activity is now also preventing invalid objects from being created. We also realized for MainMenu testing it did not need as many tests as they were handled by trip manager.

What changed in the LinkedChain? 

No changes were made other than making sure it was in the adventurerGo package. We used LinkedChain to be able to store Destination and Activity and reuse the same class for both

What happened to your unknowns?

We now understand exactly how generic work in this project as the T in LinkedChain class and Node class represents the ability for multiple object types to pass through. We also clarified how node fit into the design and how it is the building block for LinkedChain.

What doesn't work yet? 

We still need to polish the user-interface and add any stretch goals we can add. There is always more testing that can be added as well. 

# Next Steps
What is left to finish the MVP? 

Continuing to test all user input options to make sure all bad inputs are handled. We would like to add a reprompt for any numbers written for name of destination which was not a goal but something we realized we needed. 
Which stretch goals we will attempt? We want to make destination and activity related and cost estimation. We are still iffy about saving and loading the data as well as priority tags. These are things we would get to after the first two stretch goals.

What new test will we need?

We will need more test within trip manager if we relate them and any methods created w cost estimation examples would include trying to estimate a string instead of an integer and etc. 

Who does what?

Caroline: Continuing to test MainMenu as well as updating ReadMe. Working on adding cost estimation and code testing.
Jaden and Noah: work on relating the two Destination and Activity LinkedChains as well as the testing that goes into that. 
Eyal: Helping add cost estimation and experimenting with data saving. 

# Atrribution:
Main menu - Caroline - Wrote, revised, and tested using GenAI to assist in debugging and making sure all is covered in the testing. 

Node - Caroline - Wrote, revised, and tested using GenAI to assist in debugging and making sure all is covered in the testing and the notes to get the general format of the class

Destination and Activity class - Noah - Wrote, revised, and tested using GenAI to review the classes and help identify edge cases that were not thought of

Trip Manager - Eyal and Jaden - Wrote, revised, and tested using GenAI to review the classes and help identify edge cases that were not thought of

ReadMe - Caroline - Wrote and revised 

Gen AI usage overall was used to debug and brainstorm test cases and make sure there were no large errors that would occur. 


# Implemented stretch goals

- Menu 10 links an existing activity to an existing destination. Leave the
  destination blank to unlink it. Renaming a destination keeps its links;
  removing it unlinks its activities without deleting them.
- Menu 11 sets a destination's travel/lodging estimate, excluding activity costs.
  Menu 12 sets an activity's estimate. Menu 13 displays their combined total.
  Costs start at zero and use one currency chosen by the user. Each destination
  and activity is counted once, including unassigned activities. Negative,
  nonnumeric, NaN and infinite costs are rejected. An overflowing total reports
  an error. Display rounds the total to two decimal places.
- Creating or renaming either kind of item rejects numeric-only names, including
  surrounding spaces, signed numbers, decimals and scientific notation. Names
  containing words and numbers, such as Route 66 or 5K Run, remain valid.
  Invalid names are re-prompted in the menu.
- Manager operations identify items by exact, case-sensitive name. Duplicate
  names within each type are rejected to avoid ambiguous links and cost updates.
  Name/description edits preserve existing costs and activity links; the new
  menu actions change costs and links separately.

Validation: all 61 tests in ActivityTest, DestinationTest, TripManagerTest,
NodeTest and MainMenuTest pass with Java 17, JUnit 4.13.2 and the course's
student.jar. Tests cover creation/rename validation, link lifecycle, zero and
decimal totals, edits/removals, invalid costs, overflow and console re-prompts.
MainMenuTest was renamed to MainMenuTest.java and its package corrected;
LinkedChain now declares the same adventurerGo package as the other classes.
The course test library emits Java SecurityManager deprecation warnings.

These additions used AI assistance for implementation, debugging and test cases.
Saving/loading and priority tags are not implemented.
