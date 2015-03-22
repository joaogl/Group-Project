package group.project.unknown.gamestates;

import java.util.*;

public class GameStateManager {

	/** List to hold GameStates. */
	private ArrayList<GameState> gameStates;
	/** Holder for the current GameState. */
	private int currentState;

	/**
	 * The constructor of GameStateManager
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
	}

	/**
	 * Adds a state to the List.
	 * 
	 * @param state
	 *            , A new instance of GameState.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void addState(GameState state) {
		gameStates.add(state);
	}

	/**
	 * Sets a GameState.
	 * 
	 * @param state
	 *            , The state.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void setState(int state) {
		gameStates.get(state).init();
		currentState = state;
	}

	/**
	 * Ticks the current GameState (Updates once every second)
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void tick() {
		gameStates.get(currentState).tick();
	}

	/**
	 * Updates the current GameState. (Updates 60 times per second)
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void update() {
		gameStates.get(currentState).update();
	}

	/**
	 * Renders the current GameState.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void render() {
		gameStates.get(currentState).render();
	}

}
