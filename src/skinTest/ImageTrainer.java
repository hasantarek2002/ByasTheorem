package skinTest;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTrainer {

	public int[][][] skin = new int[256][256][256];
	public int[][][] nonSkin = new int[256][256][256];
	public double[][][] probability = new double[256][256][256];
	File imageFolder = null;
	File[] imagePaths;
	File maskFolder = null;
	File[] maskPaths;

	private String FILENAME = "trainData\\trainData.txt";

	public void makeListOfFiles() {
		initializeArray();
		try {

			imageFolder = new File("image\\");
			maskFolder = new File("mask\\");

			// returns pathnames for files and directory
			imagePaths = imageFolder.listFiles();
			maskPaths = maskFolder.listFiles();
			// System.out.println(imagePaths.length+" "+maskePaths.length);
			if (imagePaths.length != maskPaths.length) {
				System.out.println("number of two different test directories are different");
			} else {
				// System.out.println("number of two different test directories are not
				// different");
				for (int k = 0; k < imagePaths.length; k++) {
					BufferedImage img1 = ImageIO.read(new File(imagePaths[k].toString())); // rgb image
					BufferedImage img2 = ImageIO.read(new File(maskPaths[k].toString())); // mask image
					// System.out.println(imagePaths[i].toString());

					int col = img1.getWidth();
					int row = img1.getHeight();

					for (int i = 0; i < row; i++) {
						for (int j = 0; j < col; j++) {
							Color c1 = new Color(img1.getRGB(j, i));
							Color c2 = new Color(img2.getRGB(j, i));
							// RGB
							int green1 = (int) c1.getGreen();
							int red1 = (int) c1.getRed();
							int blue1 = (int) c1.getBlue();
							// MASK
							int green2 = (int) c2.getGreen();
							int red2 = (int) c2.getRed();
							int blue2 = (int) c2.getBlue();

							if (red2 > 100 && green2 > 100 && blue2 > 100) {
								skin[red1][green1][blue1]++;
							} else {
								nonSkin[red1][green1][blue1]++;
							}

							// int sum = (red + green + blue) / 3;
							// Color edited = new Color(sum, sum, sum);
							// img.setRGB(j, i, edited.getRGB());
						}
					}

				}
				createTrainFile();

			}

		} catch (Exception e) {

			// if any error occurs
			e.printStackTrace();
		}
	}

	public void createTrainFile() {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			// open filewriter as append mode
			fw = new FileWriter(FILENAME, true);

			bw = new BufferedWriter(fw);

			for (int i = 0; i < 256; i++) {
				for (int j = 0; j < 256; j++) {
					for (int k = 0; k < 256; k++) {
						if (((double) skin[i][j][k] + (double) nonSkin[i][j][k]) == 0) {
							probability[i][j][k] = 0;
						} else {
							probability[i][j][k] = (double) skin[i][j][k]
									/ ((double) skin[i][j][k] + (double) nonSkin[i][j][k]);
						}

						bw.write(probability[i][j][k] + "\n");
						// System.out.println("Writting");
					}
				}
			}
			System.out.println("Write Done");

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initializeArray() {
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				for (int k = 0; k < 256; k++) {
					skin[i][j][k] = 0;
					nonSkin[i][j][k] = 0;
				}
			}
		}
	}

}
