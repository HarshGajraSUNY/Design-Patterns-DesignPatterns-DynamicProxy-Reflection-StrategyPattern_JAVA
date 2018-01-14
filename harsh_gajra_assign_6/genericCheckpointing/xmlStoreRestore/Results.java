package genericCheckpointing.xmlStoreRestore;



import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * 
 */
public class Results {
    /**
    *
    * Method to write the output of the test to output file
    */
    public ArrayList<String> output=new ArrayList<String>();
    
    public void writeToFile(String file) {
    
    try{
	PrintWriter writer = new PrintWriter(file, "UTF-8");
       // Collections.sort(output);
        for(String x: output)
            writer.println(x);
        writer.close();
        }
        catch(IOException ex){
        System.err.print("Output file name does not exist");  
        }    
    }
 
    /*
    *
    * Method to write the output of the test to console
    */
    public void writeToStdout() {
       for(String x: output)
            System.out.println(x);
    } 

}


