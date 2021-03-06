package Server;

import javafx.application.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class WatchFile implements WatchFileInterface{

    //Declaring variables
	/*
	We need to store the path of the directory
	As well as the directory itself
	 */
    String directoryName;
    File directory;

    /*
    When we create a WatchFile object
    we take in the path of the folder
    we wish to watch.
    We store the path and create a new
    File directory
     */
    public WatchFile(String name) {

        directoryName = name;

        directory = new File(directoryName);

    }
    /**
     * If there is files in the directory it returns an array with the name of the files in it otherwise
     * returns nothing
     */
    public File[] ReturnFiles() {

        if (directory != null) {
            File[] contentsOfDirectory = directory.listFiles();

            return contentsOfDirectory;
        }

        else {
            System.err.println("Directory not found");
            return null;
        }

    }
    /**
     * Takes in the name of a file then loops to find that file in the array, returns that file
     */
    public File ReturnFile(String name){
        File returnFile = null;

        for(File file: directory.listFiles()){
            if(file.getName().equals(name)){
                returnFile = file;
                break;
            }
        }

        return returnFile;
    }
    /*
    Moves a file from a source to the current folder being watched
    Using the Files library
     */
    public void CopyFile(File source) throws IOException {

        File destination = new File(directoryName + "\\" + source.getName());

        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

}