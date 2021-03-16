/**
 *  Sergio Saraiva
 * 	111950948
 *      sergio.saraiva@stonybrook.edu
 *	CSE214 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class WebPage extends ArrayList<String> {

	private String url;
	private int index;
	private int rank;
	private ArrayList<String> keywords;
	
	public WebPage() {
		
	}
	
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	public ArrayList<String> getKeywords(){
		return keywords;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	public String toString(){
		String result = String.format("   %-3d | %-19s |    %-4d |", this.index, this.url, this.rank);
				
		return result;
	}

}
