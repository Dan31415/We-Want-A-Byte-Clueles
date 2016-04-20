package CluelessPackage;


import java.util.ArrayList;

public class Location {
	ArrayList<Location> adjacencyList = new ArrayList<Location>();
	boolean isHallway;
	int locNumber;
	
	
	Location(int locNumber, boolean b){
		isHallway=b;
		this.locNumber = locNumber;
	}


	void addLinks(ArrayList<Location> links) {
		for (Location l: links){
			adjacencyList.add(l);
		}
	}
}
