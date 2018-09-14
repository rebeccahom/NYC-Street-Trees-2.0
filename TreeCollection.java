import java.util.*;
/**
 * 
 * @author Rebecca Hom
 * @since April 22, 2017
 * A class that stores Tree objects in a binary search tree. It inherits from the MyBST<Tree> class.
 *
 */
public class TreeCollection extends MyBST<Tree>{
	public ArrayList<String> speciesList = new ArrayList<String>();
	private MyBST<Tree> bronx = new MyBST<Tree>();
	private MyBST<Tree> brooklyn = new MyBST<Tree>();
	private MyBST<Tree> manhattan = new MyBST<Tree>();
	private MyBST<Tree> queens = new MyBST<Tree>();
	private MyBST<Tree> staten = new MyBST<Tree>();
	
	/**
	 * Default constructor; needs to make a call to the super class's constructor
	 */
	public TreeCollection(){
		super();
	}
	
	/**
	 * Retrieves the total number of trees stored in the list
	 * @return size An integer describing the number of trees stored in the BST
	 */
	public int getTotalNumberOfTrees(){
		return size;
	}
	
	/**
	 * Retrieves the total number of Tree objects in a list whose species matches speciesName specified by the parameter
	 * It is case insensitive; uses an efficient implementation to search the BST
	 * @param speciesName A string that contains a given species name to search for in the BST
	 * @return The number of trees that contain the given speciesName. Returns 0 if the species 
	 * doesn't exist
	 */
	public int getCountByTreeSpecies(String speciesName){
		//Create a temporary array to hold the species names that contain the speciesName
		ArrayList<String> speciesNameList = (ArrayList<String>) getMatchingSpecies(speciesName.toLowerCase());
		int treeSpeciesCount = 0; //create a counter variable
		//Iterate through the temporary array; check each value and look for it in the BST
		for (int i = 0; i<speciesNameList.size();i++){
			treeSpeciesCount += getCountByTreeSpeciesHelper(root,speciesNameList.get(i));
		}
		return treeSpeciesCount;
	}
	
	/**
	 * A private helper method for retrieving the number of trees by tree species
	 * @param node A node in a BST of type Tree
	 * @param speciesName The supplied species name that we are searching for
	 * @return The number of trees that contain the given species name
	 */
	private int getCountByTreeSpeciesHelper(BSTNode<Tree> node, String speciesName){
		if (node == null){
			return 0;
		}	
		
		//If the data matches, then increase the count by 1
		else if (node.getData().getSpc().equalsIgnoreCase(speciesName)){
			return 1 + getCountByTreeSpeciesHelper(node.getLeft(),speciesName) + getCountByTreeSpeciesHelper(node.getRight(),speciesName);
		}
		
		//If the node is alphabetically before the given speciesName, look to the left of the BST
		else if (node.getData().getSpc().compareToIgnoreCase(speciesName) < 0){
			return getCountByTreeSpeciesHelper(node.getRight(),speciesName);
		}	
		
		//If the node is alphabetically after the given speciesName, look to the right of the BST
		else{
			return getCountByTreeSpeciesHelper(node.getLeft(),speciesName);
		}
	}
	
	/**
	 * Retrieves the number of Tree objects in a given borough
	 * @param boroName The borough that we are searching in
	 * @return The number of Tree objects in a given borough
	 */
	public int getCountByBorough(String boroName){
		if (boroName.equalsIgnoreCase("bronx")){
			return bronx.size;
		}
		else if (boroName.equalsIgnoreCase("brooklyn")){
			return brooklyn.size;
		}
		else if (boroName.equalsIgnoreCase("queens")){
			return queens.size;
		}
		else if (boroName.equalsIgnoreCase("manhattan")){
			return manhattan.size;
		}
		else if (boroName.equalsIgnoreCase("staten island")){
			return staten.size;
		}
		return 0;
	}
	
	/**
	 * Retrieves the number of Tree objects containing a given speciesName in a given borough
	 * @param speciesName The speciesName with which we are searching the BST
	 * @param boroName The given borough that we are searching
	 * @return The number of Tree objects containing a given speciesName in a given borough
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName){
		//Create a temporary ArrayList to hold all of the species containing the given speciesName
		ArrayList<String> speciesNameList = (ArrayList<String>) getMatchingSpecies(speciesName.toLowerCase());
		//Create a counter
		int treeSpeciesCount = 0;

		//Check if the tree is in the Bronx
		if (boroName.equalsIgnoreCase("bronx")){
			//Iterate through the temporary ArrayList and the Bronx BST
			for (int i = 0; i<speciesNameList.size();i++){
				treeSpeciesCount += getCountByTreeSpeciesHelper(bronx.root,speciesNameList.get(i));
			}
		}
		
		//Check if the tree is in Brooklyn
		else if (boroName.equalsIgnoreCase("brooklyn")){
			//Iterate through the temporary ArrayList and the Brooklyn BST
			for (int i = 0; i<speciesNameList.size();i++){
				treeSpeciesCount += getCountByTreeSpeciesHelper(brooklyn.root,speciesNameList.get(i));
			}
		}
		else if (boroName.equalsIgnoreCase("queens")){
			//Iterate through the temporary ArrayList and the Queens BST
			for (int i = 0; i<speciesNameList.size();i++){
				treeSpeciesCount += getCountByTreeSpeciesHelper(queens.root,speciesNameList.get(i));
			}
		}
		else if (boroName.equalsIgnoreCase("manhattan")){
			//Iterate through the temporary ArrayList and the Manhattan BST
			for (int i = 0; i<speciesNameList.size();i++){
				treeSpeciesCount += getCountByTreeSpeciesHelper(manhattan.root,speciesNameList.get(i));
			}
		}
		else if (boroName.equalsIgnoreCase("staten island")){
			//Iterate through the temporary ArrayList and the Staten Island BST
			for (int i = 0; i<speciesNameList.size();i++){
				treeSpeciesCount += getCountByTreeSpeciesHelper(staten.root,speciesNameList.get(i));
			}
		}		
		return treeSpeciesCount;		
	}	
	
	/**
	 * Searches through the BST for all of the Tree objects that contain a given species name
	 * @param speciesName The species name with which we search the BST
	 * @return A Collection object containing all of the tree species that match the given parameter 
	 */
	public Collection<String> getMatchingSpecies(String speciesName){
		//Create a temporary ArrayList to hold the matching species names
		ArrayList<String> tempList = new ArrayList<String>();
		String newSpeciesName = speciesName.toLowerCase(); //Convert the string to lowercase
		for (int i = 0; i < speciesList.size();i++){ //Iterate through the species names list
			//If the speciesList element contains the lowercase speciesName, add it to the temporary ArrayList
			if (speciesList.get(i).toLowerCase().contains(newSpeciesName)){
				tempList.add(speciesList.get(i).toLowerCase());
			}
		}	
		return tempList;		
	}

	/**
	 * Overridden add method that adds a Tree object to the main BST, its species name to the 
	 * species name BST, and the object to its respective borough BST
	 * @param e The Tree object that is to be added to the BST
	 * @return True is the object is successfully added, false otherwise
	 */
	@Override
	public boolean add(Tree e) throws ClassCastException, NullPointerException{
		//Check for null arguments
		if (e == null){
			throw new NullPointerException();
		}

		super.add(e);
		//Add the species name to the species list
		String tempSpeciesName = e.getSpc().toLowerCase();
		if (!speciesList.contains(tempSpeciesName)){
			speciesList.add(tempSpeciesName);
		}

		//Add the tree to its proper borough
		if (e.getBoroname().equalsIgnoreCase("queens")){
			return queens.add(e);
		}
		else if (e.getBoroname().equalsIgnoreCase("brooklyn")){
			return brooklyn.add(e);
		}
		
		else if (e.getBoroname().equalsIgnoreCase("bronx")){
			return bronx.add(e);
		}
		else if (e.getBoroname().equalsIgnoreCase("staten island")){
			return staten.add(e);
		}
		else if (e.getBoroname().equalsIgnoreCase("manhattan")){
			return manhattan.add(e);
		}
		return false;
	}	
	
	@Override
	public String toString(){
		String tempString = root.toString();
		return tempString;
	}
}
