package skinTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String[] args) {
		
		//ImageTrainer im=new ImageTrainer();
		//im.makeListOfFiles();
		NaiveBayes nv =new NaiveBayes();
		nv.detectSkin();
//		try {
//			File file = new File("test.jpg");
//			BufferedImage inputImage = ImageIO.read(file);
//			
//			//ImageIO.write(inputImage, "png", new File("out" + ".png"));
//
//		} catch (IOException e) {
//			System.out.println("File can not open");
//		}

	}

}
