package sample;
import java.io.File;
import java.io.IOException;
public interface FileInterface {
    //If there is files in the directory it returns an array with the name of the files in it otherwise
    //returns nothing
    File[] ReturnFiles();

    //Takes in the name of a file then loops to find that file in the array, returns that file
    File ReturnFile(String name);

    //Moves the file from the server folder over to the client folder
    void CopyFile(File source) throws IOException;

}
