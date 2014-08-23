import java.awt.Color;
import java.io.ObjectInputStream.GetField;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Main {
	public static void main(String[] args) {
		YelpAPI yAPI = new YelpAPI("ePWwBShfp1NQ-KSA5rRYRQ", "SqC3_uo9197BNTpDiSXkTorPtSk", "KYx8fBlg-6S3RW5lw9-iufnlWvRt9BQL", "3vz32YmOZU4WPqasopZaAVLfsNE");
		String term;
		String business;
		term = JOptionPane.showInputDialog("What do you want to eat?");
		business = yAPI.searchForBusinessesByLocation(term, yAPI.getDefaultLocation());
		System.out.println("Searching for " + yAPI.myTerm + "...");
		System.out.println("Limiting results to: 1 result");
		
		System.out.println(getName(business));
		System.out.println(getRating(business));
		System.out.print(" stars");
		System.out.println(business);
	}

	private static void createJFrame() {
		JFrame mainWindow = new JFrame(); // Creates the main window
		mainWindow.setSize(350, 500); // Sets the size of the main window
		mainWindow.setBackground(new Color(250, 250, 250)); // Ensures that the background of the main window is white
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // When the main window is closed, the application will also close
		mainWindow.setVisible(true); // Sets the main window visible
	}
	
	public static String getName(String business) {
		int substring1 = 0;
		int substring2 = 0;
		for (int i = 0; i < business.length() - 4; i++) {
			if (business.substring(i, i + 4).equals("name")) {
				substring1 = i + 8;
			}
		}
		for (int j = substring1; j < business.length() - 7; j++) {
			if (business.substring(j, j + 7).equals("snippet")) {
				substring2 = j - 4;
				return business.substring(substring1, substring2);
			}
		}
		return "You're !@#ked";
	}
	
	public static String getRating(String business) {
		int substring1 = 0;
		int substring2 = 0;
		for (int i = 0; i < business.length() - 4; i++) {
			if (business.substring(i, i + 4).equals("rating")) {
				substring1 = i + 9;
			}
		}
		for (int j = substring1; j < business.length() - 7; j++) {
			if (business.substring(j, j + 10).equals("mobile_url")) {
				substring2 = j - 3;
				return business.substring(substring1, substring2);
			}
		}
		return "You're !@#ked";
	}
}