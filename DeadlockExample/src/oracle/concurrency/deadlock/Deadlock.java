package oracle.concurrency.deadlock;

import static java.lang.System.out;

/**
 * @author Chris Wong
 * <p>
 * 	<a href="http://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html">Deadlock</a>
 * </p>
 */
public class Deadlock {
	/**
	 * @author Chris Wong
	 * Inner class Friend
	 */
	static class Friend {
		private final String name;
		
		/**
		 * Constructor
		 * @param name
		 */
		public Friend(String name){
			this.name = name;
		}
		
		/**
		 * Get friend name
		 * @return name
		 */
		public String getName(){
			return this.name;
		}
		
		/**
		 * Bow to friend
		 * @param bower
		 */
		public synchronized void bow(Friend bower){
			out.format("%s: %s" + " has bowed to me!%n", this.name, bower.getName());
			bower.bowBack(this);
		}
		
		/**
		 * Bow back to friend
		 * @param bower
		 */
		public synchronized void bowBack(Friend bower){
			out.format("%s: %s" + " has bowed backed to me!%n", this.name, bower.getName());
		}
	}
	
	/**
	 * Main program
	 * @param args
	 */
	public static void main(String[] args){
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				alphonse.bow(gaston);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				gaston.bow(alphonse);
			}
		}).start();
	}
}
