* run the program from the master branch through a planned series of steps/interactions (including showing how bad data or interactions are handled — if any are yet)
NOTE, as each feature is shown, someone on the team should briefly describe how work they did over the past week relates to it
    * Made display UI
        * Can select from toggle buttons type of game/players
        * Click start to load game
    * Game screen
        * Where you play the game, will have some more buttons like reset game (rn home doesn't work)
    * Playing the game
        * Click on piece to make it active
        * Shows potential moves in green
        * Click potential move to move there, switches whose turn it is
        * King pieces work
        * Still need to add double jump ability
* show any data files that have been created and describe how they are used within your program (focus on the file content rather than the code that uses it)
    * CSV files for board state
        * Right now just have default config, but will be used to save games in the middle and record board config
    * Resources folder
        * Images for certain things (title and king piece logo)
        * CSS file for styling the GUI
        * Properties file for button text
* show a variety of JUnit or TestFX tests (including both happy and sad paths) and discuss how comprehensively they cover the feature(s) and why you chose the values used in the tests
    * Don't have any yet. Oops.
    * Testing plan
        * JUnit for model, with boards
        * JUnit for file opening
        * TestFX for running program
            * Test pressing start without selecting anything


* describe how much of the planned features were completed this Sprint and what helped or impeded progress (or if the estimate was just too much)
    * Got everything done from Sprint 1 except for Checkers model
        * Decided during last sprint to move to Sprint 2, should be done this weekend
    * Impeded progress
        * Weren't all on same page design wise
        * Refactored design

* describe a specific significant event that occurred this Sprint and what was learned from it
    * We weren't adhering as much as we should've to creating APIs that other people can work with
        * Had to meet and discuss working within our own packages/classes and providing public methods for people to work with
        * Had to refactor to make this work, now all on same page.
* describe what worked regarding your teamwork and communication, what did not, and something specific that is planned for improvement next Sprint
    * What worked
        * Worked well within our timezones
            * Able to discuss design questions and pair program some
        * Everyone was understanding of design concerns
    * What didn't
        * Still struggled some with timezone differences and communicating big changes
        * Weren't always sticking to design plan
    * Specific
        * Having people look over merge requests before merging
        * Working within our classes and communicating/working together if it involves broad design and interaction between classes
            * Making public APIs that other members can work with
* what features are planned to be completed during the next Sprint (taking into account what was, or was not, done this Sprint), who will work on each feature, and any concerns that may complicate the plan
    * Sprint 2
        * Checkers double jump (Yixuan/Jincheng)
        * Connect 4 game (Yixuan/Jincheng)
        * Player classes (Lucas)
        * Beginning server functionality (Nate)
        * Saving games (Robert)