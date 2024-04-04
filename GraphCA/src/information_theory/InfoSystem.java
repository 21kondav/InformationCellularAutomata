package information_theory;

/**
 * An abstract definition of a theoretical 'information system'
 * @param <S>
 */
public interface InfoSystem {
	//schedules tasks related to the movement of information in the system
	public abstract void taskManager() throws IllegalStateException, IllegalAccessException;
	//transfers information around the channels over one time step
	public abstract void transfer() throws IllegalStateException, IllegalAccessException;
	public int getRunTime();

	/**
	 * Reads source and splits string to convertable symbols and adds to queue, and takes input from
	 * @throws IllegalStateException 
	 * @throws IllegalAccessException 
	 * 
	 */
	public abstract void updateEndpoints() throws IllegalAccessException, IllegalStateException;
}
