
package genericCheckpointing.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * 
 */
public class FileProcessor {
    private Scanner sc;

    
   
  
    public FileProcessor(String path) {
 
     File f = new File(path);
     try
     {
             sc= new Scanner(f);
     }
     catch(FileNotFoundException ex){
        System.err.print("input file name does not exist");
        System.exit(0);
    }   
  }
    
	/* 
    * Read a single line from the file 
	*
    */ 
    public  String readLine(){
      if(sc.hasNext()){
          String s= sc.nextLine();
          return s;
      }
      else
          return null;
    }

}
