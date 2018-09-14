import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * A class that reports the statistics of trees in NYC. It opens and reads data files,
 * obtains user input, performs data validation, and handles errors that occur
 * @author Rebecca Hom
 * @since April 22, 2017
 * 
 */

public class NYCStreetTrees {
	/**
	* Splits the given line of a CSV file according to commas and double quotes
	* (double quotes are used to surround multi-word entries that may contain commas).
	*
	* @param textLine line of text to be parsed
	* @return an ArrayList object containing all individual entries/tokens
	* found on the line.
	*/
	
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i< lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes
			if (nextChar =='"' || nextChar == '\u201C' || nextChar =='\u201D') {
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				}
				else {
					insideQuotes = true;
					insideEntry = true;
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if (insideQuotes || insideEntry) {
					//add it to the current entry
					nextWord.append(nextChar);
				}
				else {  //skip all spaces between entries
					continue;
				}
			}
			else if (nextChar == ',') {
				if (insideQuotes) // comma inside an entry
					nextWord.append(nextChar);
				else {
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}	
		}
		//add the last word ( assuming not empty )
		//trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
		return entries;
	}
	
	/**
	 * Reports the popularity of each tree. Displays the number of trees with that species name in that borough,
	 * and the total number of trees in that borough. Displays a percentage of how many trees are of the species name in that
	 * borough.
	 * @param treeName The common species name of the tree
	 */
	public static void cityPopularity(TreeCollection list,String treeName){
		//Get the total number of trees in NYC, and the total number of trees with the given species name in NYC
		//Get the percentage of trees with the given species name in NYC
		int totalNycTrees = list.getTotalNumberOfTrees();
		int totalNycTreeName = list.getCountByTreeSpecies(treeName);
		float nycPercent = 0;
		if (totalNycTrees != 0){
			nycPercent = ((float) totalNycTreeName/totalNycTrees)*100;
		}
		
		//Get the total number of trees in Manhattan, and the total number of trees with the given species name in Manhattan
		//Get the percentage of trees with the given species name in Manhattan
		int totalManhattanTrees = list.getCountByBorough("Manhattan");
		int totalManhattanTreeName = list.getCountByTreeSpeciesBorough(treeName, "Manhattan");
		float manhattanPercent = 0;
		if (totalManhattanTrees != 0){
			manhattanPercent = ((float)totalManhattanTreeName/totalManhattanTrees)*100;
		}
		
		
		//Get the total number of trees in the Bronx, and the total number of trees with the given species name in the Bronx
		//Get the percentage of trees with the given species name in the Bronx
		int totalBronxTrees = list.getCountByBorough("Bronx");
		int totalBronxTreeName = list.getCountByTreeSpeciesBorough(treeName,"Bronx");
		float bronxPercent = 0;
		if (totalBronxTrees != 0){
			bronxPercent = ((float)totalBronxTreeName/totalBronxTrees)*100;
		}
		
		//Get the total number of trees in Brooklyn, and the total number of trees with the given species name in Brooklyn
		//Get the percentage of trees with the given species name in Brooklyn
		int totalBrooklynTrees = list.getCountByBorough("Brooklyn");
		int totalBrooklynTreeName = list.getCountByTreeSpeciesBorough(treeName,"Brooklyn");
		float brooklynPercent = 0;
		if (totalBrooklynTrees != 0){
			brooklynPercent = ((float)totalBrooklynTreeName/totalBrooklynTrees)*100;
		}
		
		//Get the total number of trees in Queens, and the total number of trees with the given species name in Queens
		//Get the percentage of trees with the given species name in Queens
		int totalQueensTrees = list.getCountByBorough("Queens");
		int totalQueensTreeName = list.getCountByTreeSpeciesBorough(treeName,"Queens");
		float queensPercent = 0;
		if (totalQueensTrees != 0){
			queensPercent = ((float)totalQueensTreeName/totalQueensTrees)*100;
		}
		
		//Get the total number of trees in Staten Island, and the total number of trees with the given species name in Staten Island
		//Get the percentage of trees with the given species name in Staten Island
		int totalStatenTrees = list.getCountByBorough("Staten Island");
		int totalStatenTreeName = list.getCountByTreeSpeciesBorough(treeName,"Staten Island");
		float statenPercent = 0;
		if (totalStatenTrees != 0){
			statenPercent = ((float)totalStatenTreeName/totalStatenTrees)*100;
		}
		
		
		//Print out each variable, and format appropriately
		System.out.println("Popularity in the City");
		System.out.printf("NYC: %,20d (%,d) %10.2f",totalNycTreeName,totalNycTrees,nycPercent);
		System.out.print("%\n");
		
		System.out.printf("Manhattan: %,14d (%,d) %11.2f",totalManhattanTreeName,totalManhattanTrees,manhattanPercent);
		System.out.print("%\n");
		
		System.out.printf("Bronx: %,18d (%,d) %11.2f",totalBronxTreeName,totalBronxTrees,bronxPercent);
		System.out.print("%\n");
		
		System.out.printf("Brooklyn: %,15d (%,d) %10.2f",totalBrooklynTreeName,totalBrooklynTrees,brooklynPercent);
		System.out.print("%\n");

		System.out.printf("Queens: %,17d (%,d) %10.2f",totalQueensTreeName,totalQueensTrees,queensPercent);
		System.out.print("%\n");
		
		System.out.printf("Staten Island: %,10d (%,d) %10.2f",totalStatenTreeName,totalStatenTrees,statenPercent);
		System.out.print("%\n");
	}
	public static void main(String args[]) throws FileNotFoundException {
		TreeCollection fullTreeList = new TreeCollection();
		
		try{
			//Read the file
			File userFile = new File(args[0]);
			Scanner fileReader = new Scanner(userFile);
			
			//Create a temporary ArrayList to load the data into from the file
			ArrayList<String> tempTreeList = new ArrayList<String>();
			while (fileReader.hasNextLine()){ //Check if there is any more lines left
				tempTreeList = splitCSVLine(fileReader.nextLine());
				if (tempTreeList.get(0).equals("tree_id")){ //Skip the header row of the file
					continue;
				}
				else{ //Change the types of each element in the ArrayList to the proper type for a Tree object
					int treeID = Integer.parseInt(tempTreeList.get(0));
					int treeDiam = Integer.parseInt(tempTreeList.get(3));
					String treeStatus = tempTreeList.get(6);
					String treeHealth = tempTreeList.get(7);
					String speciesName = tempTreeList.get(9);
					int zipcode = Integer.parseInt(tempTreeList.get(25));
					String boroughName = tempTreeList.get(29);
					double xCoord = Double.parseDouble(tempTreeList.get(39));
					double yCoord = Double.parseDouble(tempTreeList.get(40));
					
					//Create a temporary Tree object from the temporary ArrayList
					Tree tempTree = new Tree(treeID,treeDiam,treeStatus,treeHealth,speciesName,zipcode,boroughName,xCoord,yCoord);
					
					//Add the temporary Tree object to a comprehensive list containing all of the trees in the file
					fullTreeList.add(tempTree);		
				}
			}
			fileReader.close();
			//Enter an infinite loop; break the loop when the user inputs "quit"
			while (true){
				//Get the user input
				Scanner userInput = new Scanner(System.in);
				System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");
				String treeName = userInput.nextLine();
				
				if (treeName.equals("quit")){
					userInput.close();
					break;
				}
			
				else{
					//Display all trees that have the same species name as the user input. If there are no entries, report it
					System.out.println("All matching species: ");
					if (fullTreeList.getCountByTreeSpecies(treeName) == 0){
						System.out.println("There are no records of " + treeName + " on NYC streets\n");
					}
					else{
						ArrayList<String> fullSpeciesNameList = (ArrayList<String>)fullTreeList.getMatchingSpecies(treeName);
						for (int i = 0; i<fullSpeciesNameList.size();i++){
							System.out.println(fullSpeciesNameList.get(i));
						}
						System.out.println();
						cityPopularity(fullTreeList,treeName);
						System.out.println();
					}
				}
			}
		}
		
		catch (FileNotFoundException error){
			System.err.println("Error: The file \"" + args[0] + "\" cannot be opened");
		}
	}
}
