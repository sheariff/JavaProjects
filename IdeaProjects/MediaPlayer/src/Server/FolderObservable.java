
package Server;

import java.io.File;
import java.util.Arrays;
import java.util.Observable;
//Uses WatchFile to watch the files and then updates the observer
public class FolderObservable extends Observable implements Runnable {

	/*
	Our observable will need a folder to watch
	To do this we instantiate a watchfile object,
	by having the code for accessing the directory
	in a seperate class we can decouple our code and
	re-use classes in other situations
	 */

    //Creating Variables
    private File[] directory = null;
    private WatchFileInterface fileWatcher = null;
    private String directoryName = null;

    /*
    When our FolderObservable is created we have
    to pass in a directory path to monitor
    We set the global directory name equal to the local scope
    variable.
    We also instantiate our WatchFile object and pass in
    the path of the folder we wish to monitor
     */
    public FolderObservable(String directoryName) {

        this.directoryName = directoryName;

        fileWatcher = new WatchFile(this.directoryName);

    }

    /**
     * This is the method that will be spun off onto it's own
     * thread.
     * It overrides the run method of the Runnable interface.
     * When this method is ran it will run within a loop indefinitely
     * From there on each loop it will call on the WatchFile object to
     * return the contents of the directory being observed and compare them
     * to it's own.
     * If there is a difference between the two, then it will update its observers
     * wait 500 milliseconds and repeat.
     */
    @Override
    public void run(){

        while(true) {
            //Takes in the list from File Watcher
            File[] temp = fileWatcher.ReturnFiles();

            if(Arrays.equals(temp, directory) ==  false) {

                directory = temp;

                setChanged();
                notifyObservers(directory);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("Error the thread has been interrupted");
                }
            }
        }
    }

}