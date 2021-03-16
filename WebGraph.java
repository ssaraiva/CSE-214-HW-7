/**
 *  Sergio Saraiva
 * 	111950948
 *      sergio.saraiva@stonybrook.edu
 *	CSE214 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebGraph extends ArrayList<WebPage> {
	public static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages;
	private int[][] edges;
	
	/*
	 * Constructs a WebGraph object using the indicated files as the source for pages and edges
	 * 
	 * Parameters:
	 * 	pagesFile - String of the relative path to the file containing the page information
	 * 	linksFile - String of the relative path to the file containing the link information
	 * 
	 * Preconditions:
	 * 	Both parameters reference text files which exist
	 * 	The files follow proper format as outlined in the "Reading Graph from File" section below.
	 * 
	 * Postconditions:
	 * 	A WebGrapg has been constructed and initialized based on the text files.
	 * 
	 * Returns:
	 * 	The WebGraph constructed from the text files.
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws FileNotFoundException {
		WebGraph graph = new WebGraph();
		graph.pages = new ArrayList<WebPage>();
		graph.edges = new int[MAX_PAGES][MAX_PAGES];
		Scanner pInput = new Scanner(new File(pagesFile));
		Scanner lInput = new Scanner(new File(linksFile));

		while(pInput.hasNextLine()) {
			ArrayList<String> keywords = new ArrayList<String>();
			String pLine = pInput.nextLine().trim();
			String url = pLine.substring(0, pLine.indexOf(" "));
			String[] fileKeywords = pLine.substring(pLine.indexOf(" ") + 1).split(" ");		
			for(int i = 0; i < fileKeywords.length; i++)
				keywords.add(fileKeywords[i]);
			graph.addPage(url,keywords);
		}
		
		while(lInput.hasNextLine()) {
			String lLine = lInput.nextLine().trim();
			String sourceLink = lLine.substring(0, lLine.indexOf(" "));
			String destLink = lLine.substring(lLine.indexOf(" ") + 1);
			graph.addLink(sourceLink, destLink);
		}
		
		return graph;
	}
	
	/*
	 * Adds a page to the WebGraph
	 * 
	 * Parameters:
	 * 	url - The URL of the webpage
	 * 	keywords - The keywords associated with the WebPage
	 * 
	 * Preconditions:
	 * 	url is unique and does not exist as the URL of a WebPage already in the graph
	 * 	url and keywords are not null
	 * 
	 * Postconditions:
	 * 	The page has been added to the pages at index 'i' and links has been logically
	 * extended to include the new row and column indexed by 'i'.
	 */
	public void addPage(String url, ArrayList<String> keywords) {
		WebPage web = new WebPage();
		web.setUrl(url);
		web.setKeywords(keywords);
		pages.add(web);
		pages.get(pages.size() - 1).setIndex(pages.size() - 1);
		this.updatePageRanks();
	}
	
	/*
	 * Adds a link from the WebPage with the URL indicated by source to the WebPage with the URL
	 * indicated by destination
	 * 
	 * Parameters:
	 * 	source - the URL of the page which contains the hyperlink to destination
	 * 	destination - the URL of the page which the hyperlink points to.
	 * 
	 * Preconditions:
	 * 	Both parameters reference WebPages which exist in the graph.
	 */
	public void addLink(String source, String destination) {
		int sourceIndex = 0;
		int destIndex = 0;
		for(int i = 0; i < pages.size(); i++){
			if(pages.get(i).getUrl().equals(source)){
				sourceIndex = i;
			}
			else if(pages.get(i).getUrl().equals(destination)){
				destIndex = i;
			}
		}
		edges[sourceIndex][destIndex] = 1;
		this.updatePageRanks();
	}
	
	/*
	 * Method created to remove elements from WebPage array.
	 * 
	 * Parameters:
	 * 	arr - The array of pages to be removed from
	 *  index - The specific location in which to remove the element from array
	 *  
	 * Precondition:
	 * 	array is not null.
	 * 
	 * Postcondition:
	 * 	Specified element located in the array is removed.
	 */
	public static void removeElement(WebPage[] arr, int index ){
        WebPage[] arrOut = new WebPage[arr.length - 1];
        int remainingElements = arr.length - ( index + 1 );
        System.arraycopy(arr, 0, arrOut, 0, index);
        System.arraycopy(arr, index + 1, arrOut, index, remainingElements);
    }
	
	 /*
	  * Removes the WebPage from the graph with the given URL
	  * 
	  * Parameters:
	  *   url - The URL of the page to remove from the graph
	  *   
	  * Postconditions:
	  * 	The WebPage with the indicated URL has been removed from the graph, and 
	  * 		it's corresponding row and column has been removed from the adjacency matrix.
	  *		All pages that has an index greater than the index that was removed should decrease their index value by 1.
	  *		If url is null or could not be found in pages, the method ignores the input and does nothing.
	  */
	public void removePage(String url) {
		WebPage[] pagesList = pages.toArray(new WebPage[pages.size()]);
		for(int i = 0; i < pages.size(); i++) {
			if(pagesList[i].getUrl().equals(url)) {
                removeElement(pagesList, i);
			}
		}	
	}
	
	public void removeLink(String source, String destination) {
	
	}
	
	/*
	 * Calculates and assigns the PageRank for every page in the WebGraph
	 * 
	 * Postconditions:
	 * 	All WebPages in the graph have been assigned their proper PageRank
	 */
	public void updatePageRanks() {
		for(int i = 0; i < pages.size(); i++){
			int pageRank = 0;
			for(int j = 0; j < pages.size(); j++){
				if(edges[j][i] == 1)
					pageRank++;
			}
			pages.get(i).setRank(pageRank);
		}
	}
	
	/*
	 * Prints the WebGraph in tabular form
	 */
	public void printTable() {
		String linksDest = "";
		String line = "";
		System.out.printf(" %-5s   %-19s  %-8s   %-24s   %s \n", "Index", "URL", "PageRank", "Links", "Keywords");
		for(int i = 1; i <= 111; i++)
			line += "-";
		System.out.println(" " + line);
		for(int i = 0; i < pages.size(); i++){
			String k = pages.get(i).getKeywords().toString();
			k = k.substring(1, k.length()-1);
			System.out.println(pages.get(i).toString() + String.format(" %-24s | %s", linksDest, k));
		}
	}
}
