/**
 * Tree Class: Creates a Tree object that stores information about a specific tree in New York City. 
 * @author Rebecca Hom
 * @since April 18, 2017
 * 
 */

public class Tree implements Comparable<Tree>{
	private int tree_id;
	private int tree_dbh;
	private String status;
	private String health;
	private String spc_common;
	private int zipcode;
	private String boroname;
	private double x_sp;
	private double y_sp;

	/**
	 * Creates a Tree object with the data fields id, diam, status, health, spc, boro, x, and y
	 * If the parameters do not meet the requirements in parentheses, throw an Illegal Argument Exception.
	 * @param id The tree's unique ID number (must be a positive integer)
	 * @param diam Diameter at breast height of the tree (must be a positive integer)
	 * @param status Tree status ("Alive", "Dead", "Stump", "", or null) 
	 * @param health Tree health ("Good", "Fair", "Poor", "", or null)
	 * @param spc Common name of the tree species (must not be a null string)
	 * @param zip Zipcode of the tree (must not be more than 5 digits)
	 * @param boro Borough in which the tree is located ("Manhattan","Bronx","Brooklyn","Queens", or "Staten Island")
	 * @param x X coordinate of the tree
	 * @param y Y coordinate of the tree
	 */
	public Tree(int id, int diam, String status, String health, String spc, int zip, String boro, 
			double x, double y) throws IllegalArgumentException{
		setTreeID(id);
		
		setTreeDbh(diam);

		setStatus(status);
		
		setHealth(health);
		
		setSpc(spc);
		
		setZipcode(zip);
		
		setBoroname(boro);
		
		x_sp = x;
		y_sp = y;
	}

	public int getTreeID(){
		return tree_id;
	}
	
	public void setTreeID(int ID) throws IllegalArgumentException{
		try{
			if (ID >= 0){
				tree_id = ID;
			}
		}
		catch(IllegalArgumentException e){
			//Continue running the program
		}
	}
	
	public int getTreeDbh(){
		return tree_dbh;
	}
	
	public void setTreeDbh(int diameter) throws IllegalArgumentException{
		try{
			if (diameter >= 0){
				tree_dbh = diameter;
			}
		}
		catch(IllegalArgumentException e){
			//Continue running the program
		}
		
	}
	
	public String getStatus(){
		return status;
	}
	
	private void setStatus(String status) throws IllegalArgumentException{
		try{
			if (status == null){
				this.status = null;
			}
			else if (status.equalsIgnoreCase("alive")||status.equalsIgnoreCase("dead")||status.equalsIgnoreCase("stump")||
			status.equalsIgnoreCase("")){
				this.status = status;
			}
		}
		catch(IllegalArgumentException e){
			//Continue running the program
		}
		
	}
	
	public String getHealth(){
		return health;
	}
	
	private void setHealth(String health) throws IllegalArgumentException{
		try{
			if (health == null){
				this.health = null;
			}
			else if (health.equalsIgnoreCase("good")||health.equalsIgnoreCase("fair")||health.equalsIgnoreCase("poor")||
			health.equalsIgnoreCase("")){
				this.health = health;
			}
		}
		catch(IllegalArgumentException e){
			//Continue running the program
		}
	}
	
	public String getSpc(){
		return spc_common;
	}
	
	private void setSpc(String spc) throws IllegalArgumentException{
		try{
			if (spc != null){
				this.spc_common = spc;
			}
		}
		catch (IllegalArgumentException e){
			//Continue running the program
		}
	}
	
	public int getZipcode(){
		return zipcode;
	}
	
	public void setZipcode(int zip)throws IllegalArgumentException{
		try{
			if (zip <= 99999 && zip >= 0){
				zipcode = zip;
			}
		}
		catch(IllegalArgumentException e){
			//Continue running the program
		}
	}
	
	public String getBoroname(){
		return boroname;
	}
	
	private void setBoroname(String boro)throws IllegalArgumentException{
		try{
			if (boro.equalsIgnoreCase("manhattan")||boro.equalsIgnoreCase("queens")||boro.equalsIgnoreCase("bronx")||
			boro.equalsIgnoreCase("brooklyn")||boro.equalsIgnoreCase("staten island"))	{
				this.boroname = boro;
			}
		}
		catch(IllegalArgumentException e){
			//Continue running the program
		}
	}
	
	public double getX_SP(){
		return x_sp;
	}

	public double getY_SP(){
		return y_sp;
	}

	/**
	 * Compares two trees and checks if they are the same tree based on their tree ID number and species name
	 * @param a The first tree to be compared
	 * @param b The second tree to be compared
	 * @return True if the two trees are the same, or false if they are not
	 * @throws IllegalArgumentException If the two trees have the same ID, but not the same species name
	 */

	public boolean equals(Tree a) throws IllegalArgumentException{
		if (this.getTreeID() == a.getTreeID() && this.getSpc().equalsIgnoreCase(a.getSpc())){
			return true;
		}
		else if (this.getTreeID() == a.getTreeID() && (this.getSpc().equals(a.getSpc()) == false)){
			throw new IllegalArgumentException("Invalid tree");
		}
		else{
			return false;
		}			
	}
	/**
	 * Determines if two Tree objects have the same name
	 * @param t A secondary tree object that is compared with a primary Tree object
	 * @return True if the two Tree objects have the same name; false otherwise 
	 */
	public boolean sameName(Tree t){
		if (this.getSpc().equalsIgnoreCase(t.getSpc())){
			return true;
		}
		return false;
	}
	
	/**
	 * Compares two trees and checks if the trees are alphabetically sorted
	 * @param tree A secondary tree object that is compared with a primary tree object
	 * @return An integer less than 0 if the secondary tree is not before the primary tree in the alphabet, 0 
	 * if the two trees are the same, or an integer greater than 0 if the secondary tree is after the 
	 * primary tree in the alphabet 
	 */
	public int compareTo(Tree tree){
		if (this.spc_common.compareToIgnoreCase(tree.spc_common) > 1 ){
			return this.spc_common.compareToIgnoreCase(tree.spc_common);
		}
		
		else if (this.spc_common.compareToIgnoreCase(tree.spc_common) == 0){
			Integer a = new Integer(this.tree_id);
			Integer b = new Integer(tree.tree_id);
			return a.compareTo(b);
		}
		
		return this.spc_common.compareToIgnoreCase(tree.spc_common);		
	}
	
	/**
	 * Compares two Tree objects; reports if they have the same species name
	 * @param t A secondary Tree object that is compared with a primary Tree object
	 * @return 0 if the two Tree objects have the same name, An integer less than 0 if the 
	 * primary tree species name is smaller than the secondary tree name (case sensitive), 
	 * or an integer greater than 0 if the primary species name is bigger than the secondary tree name (case sensitive)
	 */
	public int compareName(Tree t){
		if (this.spc_common.compareToIgnoreCase(t.getSpc()) == 0){
			return 0;
		}
		else if (this.spc_common.compareToIgnoreCase(t.getSpc()) > 0){
			return this.spc_common.compareToIgnoreCase(t.getSpc());
		}
		return this.spc_common.compareToIgnoreCase(t.getSpc());
	}
	
	/**
	 * Turns all of the attributes of the Tree object into one string
	 */
	public String toString(){
		String treeString = "Tree ID: " + tree_id + "\nTree Diameter: " + tree_dbh + "\nTree Status: " + status
				+"\nTree Health: " + health + "\nSpecies Name: " + spc_common + "\nZipcode: " + zipcode + "\nBorough: " 
				+ boroname;
		return treeString;
	}		
}