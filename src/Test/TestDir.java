package Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestDir {

	public static void main(String[] args) {
		File f = null;
	      File[] paths;
	      
	      try {  
	      
	         // create new file
//	         f = new File("nothinginput\\");
//	    	  f = new File("image\\");
	    	  f = new File("mask\\");
	         
	         // returns pathnames for files and directory
	         paths = f.listFiles();
	         int count=0;
	         System.out.println(paths.length);
	         for(int i=0; i<paths.length; i++) {
	        	 System.out.println(paths[i]);
	         }
	         
	         
	 		try {
				File file = new File(paths[1].toString());
				BufferedImage inputImage = ImageIO.read(file);
				
				ImageIO.write(inputImage, "png", new File("out1" + ".png"));

			} catch (IOException e) {
				System.out.println("File can not open");
			}

	         
	         // for each pathname in pathname array
//	         for(File path:paths) {
//	         
//	            // prints file and directory paths
//	        	 BufferedImage inputImage = ImageIO.read(path);
//	        	 //ImageIO.write(inputImage, "png", new File("nothing\\" + count + ".png"));
//	        	 count++;
//	            System.out.println(path);
//	         }
	         
	      } catch(Exception e) {
	         
	         // if any error occurs
	         e.printStackTrace();
	      }

	}

}
