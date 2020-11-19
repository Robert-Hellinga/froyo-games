Key changes made to original API created for the Plan:
 * Final project has no game-specific players. Player objects work with all games, thanks to abstract player class
    * This decision was made because it makes it easier to add new games without changing player objects
 * Removed Display object, which was originally intended to receive and display various panes. 
    * This object was removed because the overarching FroyoController object is now responsible for switching between different screens. 
    * Handling switching screens in FroyoController is beneficial because FroyoController has the Stage and acts as the macro-level controller for the view
 * All games use the same UI objects
    * Making the view more flexible to work with any different model makes it easier to add new games
 * Removed Languageable interface, passed selected Locale and Resources file along instead
    * Upon trying to implement Languageable interface, we realized there would be no class-specific methods that we would use repeatedly. 
 * Removed Styleable interface, replaced with Util.applyStyleSheet(Pane pane)
    * Styleable interface has effectively already been implemented in JavaFX, so we decided this was a cleaner way to easily apply CSS to the screens that we wanted to have CSS
 * No separate controllers for different games, just one controller for all games (GameController), and one controller to control view (FroyoController)
    * We decided having separate controllers for different games would be redundant, as we already have polymorphic Game classes. 
    * Having one GameController that interacts with the Game objects makes it easier to add more games– no longer have to create new controllers for new games
 * No more Playable interface. Anything that would be Playable could extend Game, so we decided this was redundant.  
 * In general, class names were different from planned API class names. 

Things that stayed the same:
 * Having separate screens for different UI layouts
 * Having a GameController 
 * Having HumanPlayer and SocialPlayer classes with abstract Player class
 * Having generic Game class with more specific implementations for each game