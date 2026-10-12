# cs2114-project2-group68
Reflection 
What changed from your specification? In our spec we had not quite figured out how our name and description would work together but once implementing we fully committed to Destination and Activity having two fields. The MainMenu class remained responsible for user interaction but additional validation and re-prompts were added before input data is passed to the TripManager. Validation for empty names is preformed in MainMenu and Destination and Activity is now also preventing invalid objects from being created. We also realized for MainMenu testing it did not need as many tests as they were handled by trip manager.
What changed in the LinkedChain? No changes were made other than making sure it was in the adventurerGo package. We used LinkedChain to be able to store Destination and Activity and reuse the same class for both
What happened to your unknowns? We now understand exactly how generic work in this project as the T in LinkedChain class and Node class represents the ability for multiple object types to pass through. We also clarified how node fit into the design and how it is the building block for LinkedChain. 
What doesn't work yet? We still need to polish the user-interface and add any stretch goals we can add. There is always more testing that can be added as well. 

Next Steps
What is left to finish the MVP? Continuing to test all user input options to make sure all bad inputs are handled. We would like to add a reprompt for any numbers written for name of destination which was not a goal but something we realized we needed. 
Which stretch goals we will attempt? We want to make destination and activity related and cost estimation. We are still iffy about saving and loading the data as well as priority tags. These are things we would get to after the first two stretch goals.
What new test will we need? We will need more test within trip manager if we relate them and any methods created w cost estimation examples would include trying to estimate a string instead of an integer and etc. 

Who does what?
Caroline: Continuing to test MainMenu as well as updating ReadMe. Working on adding cost estimation and code testing.
Jaden and Noah: work on relating the two Destination and Activity LinkedChains as well as the testing that goes into that. 
Eyal: Helping add cost estimation and experimenting with data saving. 

Atrribution:
Main menu - Caroline - Wrote, revised, and tested using GenAI to assist in debugging and making sure all is covered in the testing. 
Node - Caroline - Wrote, revised, and tested using GenAI to assist in debugging and making sure all is covered in the testing and the notes to get the general format of the class
Destination and Activity class - Noah - Wrote, revised, and tested using GenAI to review the classes and help identify edge cases that were not thought of
Trip Manager - Eyal and Jaden - Wrote, revised, and tested using GenAI to review the classes and help identify edge cases that were not thought of
ReadMe - Caroline - Wrote and revised 

Gen AI usage overall was used to debug and brainstorm test cases and make sure there were no large errors that would occur. 
