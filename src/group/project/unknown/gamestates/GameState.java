package group.project.unknown.gamestates;

public abstract class GameState {

	/** Holder for the instance of GameStateManager. */
	protected GameStateManager gsm;

	/** Inits the state. */
	public abstract void init();

	/** Updates once per second. */
	public abstract void tick();

	/** Updates 60 times per second */
	public abstract void update(float delta);

	/** Updates as fast as possible, or at the set limit. */
	public abstract void render();

}
