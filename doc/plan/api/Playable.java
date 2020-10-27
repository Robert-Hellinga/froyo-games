
import java.util.*;

/**
 * 
 */
public interface Playable {

    /**
     * 
     */
    public void startGame();

    /**
     * 
     */
    public void saveGame();

    /**
     * @param observer
     */
    public void addObserver(void observer);

}