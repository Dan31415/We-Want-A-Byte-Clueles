package CluelessPackage;


import java.util.ArrayList;

/**
 * 
 * Modified: Adam Turbiville, 5/1/16
 *
 */
public class Location {
	ArrayList<Location> adjacencyList = new ArrayList<Location>();
	boolean isHallway;
	int locNumber;
	private String LocationName;
	
	
	Location(int locNumber, boolean b){
		this.isHallway=b;
		this.locNumber = locNumber;
		setLocationName();
	}


	void addLinks(ArrayList<Location> links) {
		for (Location l: links){
			adjacencyList.add(l);
		}
	}
	
	/**
	 * Added function to associate a name to a location
	 * @author Adam Turbiville
	 * 5/1/16
	 */
	private void setLocationName()  {
	   switch (this.locNumber)   {
	      case 0:
	         this.LocationName = "Study";
	         break;
         case 1:
            this.LocationName = "Study/Hall Hallway";
            break;
         case 2:
            this.LocationName = "Hall";
            break;
         case 3:
            this.LocationName = "Hall/Lounge Hallway";
            break;
         case 4:
            this.LocationName = "Lounge";
            break;
         case 5:
            this.LocationName = "Study/Library Hallway";
            break;
         case 6:
            this.LocationName = "Hall/Billiard Hallway";
            break;
         case 7:
            this.LocationName = "Lounge/Dining Hallway";
            break;
         case 8:
            this.LocationName = "Library";
            break;
         case 9:
            this.LocationName = "Library/Billiard Hallway";
            break;
         case 10:
            this.LocationName = "Billiard Room";
            break;
         case 11:
            this.LocationName = "Billiard/Dining Hallway";
            break;
         case 12:
            this.LocationName = "Dining Room";
            break;
         case 13:
            this.LocationName = "Library/Conservatory Hallway";
            break;
         case 14:
            this.LocationName = "Billiard/Ballroom Hallway";
            break;
         case 15:
            this.LocationName = "Dining/Kitchen Hallway";
            break;
         case 16:
            this.LocationName = "Conservatory";
            break;
         case 17:
            this.LocationName = "Conservatory/Ball Hallway";
            break;
         case 18:
            this.LocationName = "Ballroom";
            break;
         case 19:
            this.LocationName = "Ballroom/Kitchen Hallway";
            break;
         case 20:
            this.LocationName = "Kitchen";
            break;
	      default:
	         break;
	   }
	}
	
	public String getLocationName() {
	   return this.LocationName;
	}
}
