Froyo Games
====
This is a game that can play Connect 4, Othello, and Checkers. It can also create artificial players
and interact with Firebase RealtimeDB for online play. The AI portion of the code and algorithm used
for turn-taking could use some cleaning, but otherwise we're happy with how it turned out.

Authors: Nate Mela, Robert Hellinga, Lucas Carter, Yixuan Li, Jincheng He

### Timeline

Start Date: October 23

Finish Date: November 19

Hours Spent: 100+ (Cumulative)

### Primary Roles

Robert - Project Leader/Data + Resources
Jincheng - Model
Yixuan - Model
Lucas - Controller
Nate - View

### Resources Used

* Used [this](https://github.com/dicolar/jbootx) GitHub repo for bootstrap CSS theme
* Used [this](https://stackoverflow.com/questions/46603948/javafx-position-dialog-and-stage-in-center-of-screen) StackOverflow post for code to center screen in FroyoController
* Used [this](https://stackoverflow.com/questions/41634789/javafx-combobox-display-text-but-return-id-on-selection) StackOverflow post for code to Locale display names in language selection dropdown
* Used [this](https://stackoverflow.com/questions/46835087/prevent-a-toggle-group-from-not-having-a-toggle-selected-java-fx) StackOverflow post for code to ensure ToggleButtonGroups always have at least one button selected after being initially selected

### Running the Program

Main class: Main.java

Data files needed:
 * Resource bundles for Splash Screen, Game Screen, and for game piece colors
 * CSV files containing board configurations 

Features implemented:

 * Load Games (can change starting configurations by altering CSV files)
 * Preferences (can change displayed piece colors via ResourceBundle)
 * Artificial Player (using variations of minmax algorithm)
 * Online Play (can play Checkers or Connect 4 against another player)

### Notes/Assumption

Assumptions or Simplifications:
 * When playing online, wait for opponent player to join before making the first move. Otherwise, turn cycle might be broken
 * Win popup is only in English. 

Required libraries:
 * If there are any library related bugs, make sure to have these two Maven repositories added:
    * com.google.firebase:firebase-admin:7.0.1
    * org.slf4j:slf4j-simple:1.6.2

Interesting data files: 

Known Bugs: 
 * Names do not display properly on top/bottom with online (both players see the same name on top and bottom)
 * Othello does not work properly with online feature. 
 * Save button does not work
 * Kings can only jump in a normal piece's direction after the first jump
 
### Impressions

