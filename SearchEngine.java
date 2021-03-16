/**
 *  Sergio Saraiva
 * 	111950948
 *      sergio.saraiva@stonybrook.edu
 *	CSE214 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private static WebGraph web;
	
	public static void main(String[] args) {
		String choice = "";
		boolean cont = true;
		Scanner input = new Scanner(System.in);
		
		try {
			web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		System.out.println("Loading WebGraph data...");
		System.out.println("Success!");
		
		while(cont) {
			
			System.out.println("\nMenu:");
			System.out.println("    (AP) - Add a new page to the graph.");
			System.out.println("    (RP) - Remove a page from the graph.");
			System.out.println("    (AL) - Add a link between pages in the graph.");
			System.out.println("    (RL) - Remove a link between pages in the graph.");
			System.out.println("    (P) - Print the graph");
			System.out.println("    (S) - Search for pages with a keyword.");
			System.out.println("    (Q) - Quit.");
			System.out.print("Please select an option: ");

			choice = input.nextLine();
		
			switch(choice) {
				case "AP": // Add a new page to the graph
				case "ap":
					String newUrl;
					String newKeywords;
					ArrayList<String> keywords = new ArrayList<String>();
					
					System.out.print("\nEnter a URL: ");
					newUrl = input.nextLine();
					System.out.println("\nEnter keywords (space-seperated): ");
					newKeywords = input.nextLine();
					String[] inputKeywords = newKeywords.substring(0).split(" ");		
					for(int i = 0; i < inputKeywords.length; i++)
						keywords.add(inputKeywords[i]);
					
					web.addPage(newUrl, keywords);
				
					System.out.println(newUrl + " successfully added to the WebGraph!");
					cont = true;
					break;
				
				case "RP": // Remove a page from the graph
				case "rp":
					String removeUrl;
					System.out.print("Enter a URL: ");
					removeUrl = input.nextLine();
					web.removePage(removeUrl);
					
					cont = true;
					break;
				
				case "AL": // Add a link between pages in the graph
				case "al":
					String sourceUrl;
					String destUrl;
					System.out.print("Enter a source URL: ");
					sourceUrl = input.nextLine();
					System.out.print("\nEnter a destination URL: ");
					destUrl = input.nextLine();
				
					System.out.println("Link successfully added from " + sourceUrl + " to " + destUrl + "!");
				
					cont = true;
					break;
				
				case "RL": // Remove a link between pages in the graph
				case "rl":
					System.out.print("Enter a source URL: ");
					sourceUrl = input.nextLine();
					System.out.print("\n Enter a destination URL: ");
					destUrl = input.nextLine();
					System.out.println("\nLink removed from " + sourceUrl + " to " + destUrl + "!");
				
					cont = true;
					break;
				
				case "P": // Print the graph
				case "p":
					String secondChoice = "";
					System.out.println("\n    (I) Sort based on index (ASC)");
					System.out.println("    (U) - Sort based on URL (ASC)");
					System.out.println("    (R) - Sort based on rank (DSC)");
					System.out.print("Please select an option: ");
					secondChoice = input.nextLine();
					
					cont = true;
					
					switch(secondChoice) {
						case "I": // Sort based on index (ASC)
						case "i":
							cont = true;
							web.printTable();
							break;
					
						case "U": // Sort based URL (ASC)
						case "u":
							cont = true;
							String line = "";
							System.out.printf(" %-5s   %-19s  %-8s   %-24s   %s \n", "Index", "URL", "PageRank", "Links", "Keywords");
							for(int i = 1; i <= 111; i++)
								line += "-";
							System.out.println(" " + line);
							break;
					
						case "R": // Sort based on rank (DSC)
						case "r":
							cont = true;
							String line1 = "";
							System.out.printf(" %-5s   %-19s  %-8s   %-24s   %s \n", "Index", "URL", "PageRank", "Links", "Keywords");
							for(int i = 1; i <= 111; i++)
								line1 += "-";
							System.out.println(" " + line1);
							break;
							
						default:
							break;
					}
				
					break;
				
				case "S": // Search for pages with a keyword
				case "s":
					String searchKeyword;
					System.out.print("Search keyword: ");
					searchKeyword = input.nextLine();
					String line = "";
					System.out.printf(" %-5s   %-19s  %-8s   %-24s   %s \n", "Index", "URL", "PageRank", "Links", "Keywords");
					for(int i = 1; i <= 111; i++)
						line += "-";
					System.out.println(" " + line);
					
					cont = true;
					break;
				
				case "Q": // Quit
				case "q":
					System.out.println("Goodbye");
					cont = false;
					break;
				
				default:
					break;
			}
		
		}
		
	}
}
