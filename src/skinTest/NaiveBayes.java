package skinTest;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class NaiveBayes {
	private String FILENAME = "trainData\\trainData.txt";
	public double[][][] probability = new double[256][256][256];
	int count = 0;

	File file = new File("a3.jpg");

	public void detectSkin() {
		readFromFile_BufferReader();
		try {
			BufferedImage inputImage = ImageIO.read(file);

			int col = inputImage.getWidth();
			int row = inputImage.getHeight();

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					Color c = new Color(inputImage.getRGB(j, i));
					// input image
					int green = (int) c.getGreen();
					int red = (int) c.getRed();
					int blue = (int) c.getBlue();

					if (probability[red][green][blue] > 0.2) {
						Color edited = new Color(255, 255, 255);
						inputImage.setRGB(j, i, edited.getRGB());
					} else {
						Color edited = new Color(0, 0, 0);
						inputImage.setRGB(j, i, edited.getRGB());
					}
				}
			}
			ImageIO.write(inputImage, "png", new File("out" + ".png"));
			System.out.println("successful.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFromFile_BufferReader() {

		Scanner scan;
		File file = new File(FILENAME);
		try {
			scan = new Scanner(file);

			for (int i = 0; i < 256; i++) {
				for (int j = 0; j < 256; j++) {
					for (int k = 0; k < 256; k++) {
						if (scan.hasNextDouble()) {
							probability[i][j][k] = scan.nextDouble();
							count++;
						}
					}
				}
			}
			System.out.println(count);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
