package Server;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.collections.ObservableList;
//uses folderObserverable Updates the list if theres a change
public class FileObservable implements Observer {

    /*
    Our observer has an observable list so that we
    can update the list within this class
    and the listview will also update as
    They both contain a reference to the same
    observable list. We also store the contents
    of the directory we are watching so that
    we can compare to see differences
     */
    ObservableList<String> list;
    File[] files;

    /*
    Takes in a reference to an observable list
    Updates the class level observable list with
    the reference passed in so that we can update
    our list view
     */
    public FileObservable(ObservableList list) {
        if(list != null) {
            this.list = list;
        }
        else {
            System.out.println("Invalid List");
        }
    }

    /*
    When the observable updates its observers
    This block of code is executed, It clears the
    observable list and updates the contents of the
    list with the new directory contents.
    Since we will be running this seperately
    to the UI thread we must use Platform.runLater()
     */
    @Override
    public void update(Observable arg0, Object file) {
        Platform.runLater(() -> {

            files = ((File[]) file);

            list.clear();

            for(File each : files) {
                list.add(each.getName());
            }
        });

    }


}
