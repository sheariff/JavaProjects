import java.io.*;


public class MymediaPlayer
{
public MymediaPlayer() throws FileNotFoundException {
}
 try {
        File file = new File ("C:\Users\User\Documents\Distr programming\MediaPlayer");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try{
        Player player = new Player(bis);
        player.play();
        }catch(JavaLayerException ex){System.out.println("Player problem");}

 } catch (IOException e){System.out.println("File io problem"+e);
 }
}