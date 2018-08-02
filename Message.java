import java.io.Serializable;
import java.net.*;

public class Message implements Serializable {

    
    public String   theMessage;  // a string
    public String[] someLines;   // an array
    public int      someNumber;  // a primitive

    public String toString() {
        String s = "Message: " + theMessage +
                "\nwith an array: ";
        for ( int i = 0 ; i < someLines.length ; i++ ) {
            s += someLines[i] + " ";
        } 

        s += "\nand a magic #: " + someNumber;

        return s;
    } 

} 