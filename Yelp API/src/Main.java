import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Main {
	public static void main(String[] args) throws Exception {
		YelpAPI yAPI = new YelpAPI("ePWwBShfp1NQ-KSA5rRYRQ",
				"SqC3_uo9197BNTpDiSXkTorPtSk",
				"KYx8fBlg-6S3RW5lw9-iufnlWvRt9BQL",
				"3vz32YmOZU4WPqasopZaAVLfsNE");
		searchForBusinesses(yAPI);
		System.out.println("Search again? (y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = "";
		do {
			string = br.readLine();
			if (string.equalsIgnoreCase("y")) {
				// TODO: Make search for more businesses
				System.out.println();
				main(args);
			} 
			if (string.equalsIgnoreCase("n")) {
				System.out.println("Closing...");
				System.out.println("Closed with system code 0");
				System.exit(0);
			}
		} while (string!="y" || string!="n");
	}

	private static void searchForBusinesses(YelpAPI yAPI) throws Exception {
		String term;
		String business;
//		term = JOptionPane.showInputDialog("What do you want to eat?");
		term = input();
		business = yAPI.searchForBusinessesByLocation(term,
				yAPI.getDefaultLocation());
		System.out.println("Searching for " + yAPI.myTerm + "...");
		System.out.println("Limiting results to: 1 result");

		System.out.println(getName(business));
//		System.out.println(getRating(business));
		System.out.print(" stars");
		System.out.println(business);
	}

	private static void createJFrame() {
		JFrame mainWindow = new JFrame(); // Creates the main window
		mainWindow.setSize(350, 500); // Sets the size of the main window
		mainWindow.setBackground(new Color(250, 250, 250)); // Ensures that the
															// background of the
															// main window is
															// white
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // When
																			// the
																			// main
																			// window
																			// is
																			// closed,
																			// the
																			// application
																			// will
																			// also
																			// close
		mainWindow.setVisible(true); // Sets the main window visible
	}

	public static String input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = "";
		System.out.println("What do you want to eat?");
		do {
			string = br.readLine();
		} while (string.isEmpty());
		return string;
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
		return "No businesses found...";
	}

	public static String getRating(String business) {
		int substring1 = 0;
		int substring2 = 0;
		System.out.println(business);
		for (int i = 0; i < business.length() - 6; i++) {
			if (business.substring(i, i + 10).equalsIgnoreCase("is_claimed")) {
				substring1 = i + 29;
			}
		}
		for (int j = substring1; j < business.length() - 7; j++) {
			if (business.substring(j, j + 1).equals(",")) {
				substring2 = j - 1;
				return business.substring(substring1, substring2);
			}
		}
		return "Failed";
	}
}
