import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;
import javax.security.auth.kerberos.KerberosKey;

import org.omg.CORBA.MARSHAL;

public class BioinformaticMatrix {
	private static final String ARRAY_WORD = "A R N D C Q E G H I L K M F P S T W Y V B Z X *";
	private static final String ARRAY_NUMBER = "4 -1 -2 -2 0 -1 -1 0 -2 -1 -1 -1 -1 -2 -1 1 0 -3 -2 0 -2 -1 0 -4 -1 5 0 -2 -3 1 0 -2 0 -3 -2 2 -1 -3 -2 -1 -1 -3 -2 -3 -1 0 -1 -4 -2 0 6 1 -3 0 0 0 1 -3 -3 0 -2 -3 -2 1 0 -4 -2 -3 3 0 -1 -4 -2 -2 1 6 -3 0 2 -1 -1 -3 -4 -1 -3 -3 -1 0 -1 -4 -3 -3 4 1 -1 -4 0 -3 -3 -3 9 -3 -4 -3 -3 -1 -1 -3 -1 -2 -3 -1 -1 -2 -2 -1 -3 -3 -2 -4 -1 1 0 0 -3 5 2 -2 0 -3 -2 1 0 -3 -1 0 -1 -2 -1 -2 0 3 -1 -4 -1 0 0 2 -4 2 5 -2 0 -3 -3 1 -2 -3 -1 0 -1 -3 -2 -2 1 4 -1 -4 0 -2 0 -1 -3 -2 -2 6 -2 -4 -4 -2 -3 -3 -2 0 -2 -2 -3 -3 -1 -2 -1 -4 -2 0 1 -1 -3 0 0 -2 8 -3 -3 -1 -2 -1 -2 -1 -2 -2 2 -3 0 0 -1 -4 -1 -3 -3 -3 -1 -3 -3 -4 -3 4 2 -3 1 0 -3 -2 -1 -3 -1 3 -3 -3 -1 -4 -1 -2 -3 -4 -1 -2 -3 -4 -3 2 4 -2 2 0 -3 -2 -1 -2 -1 1 -4 -3 -1 -4 -1 2 0 -1 -3 1 1 -2 -1 -3 -2 5 -1 -3 -1 0 -1 -3 -2 -2 0 1 -1 -4 -1 -1 -2 -3 -1 0 -2 -3 -2 1 2 -1 5 0 -2 -1 -1 -1 -1 1 -3 -1 -1 -4 -2 -3 -3 -3 -2 -3 -3 -3 -1 0 0 -3 0 6 -4 -2 -2 1 3 -1 -3 -3 -1 -4 -1 -2 -2 -1 -3 -1 -1 -2 -2 -3 -3 -1 -2 -4 7 -1 -1 -4 -3 -2 -2 -1 -2 -4 1 -1 1 0 -1 0 0 0 -1 -2 -2 0 -1 -2 -1 4 1 -3 -2 -2 0 0 0 -4 0 -1 0 -1 -1 -1 -1 -2 -2 -1 -1 -1 -1 -2 -1 1 5 -2 -2 0 -1 -1 0 -4 -3 -3 -4 -4 -2 -2 -3 -2 -2 -3 -2 -3 -1 1 -4 -3 -2 11 2 -3 -4 -3 -2 -4 -2 -2 -2 -3 -2 -1 -2 -3 2 -1 -1 -2 -1 3 -3 -2 -2 2 7 -1 -3 -2 -1 -4 0 -3 -3 -3 -1 -2 -2 -3 -3 3 1 -2 1 -1 -2 -2 0 -3 -1 4 -3 -2 -1 -4 -2 -1 3 4 -3 0 1 -1 0 -3 -4 0 -3 -3 -2 0 -1 -4 -3 -3 4 1 -1 -4 -1 0 0 1 -3 3 4 -2 0 -3 -3 1 -1 -3 -1 0 -1 -3 -2 -2 1 4 -1 -4 0 -1 -1 -1 -2 -1 -1 -1 -1 -1 -1 -1 -1 -1 -2 0 0 -2 -1 -1 -1 -1 -1 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 1 ";
	public static Map<String, Integer> dictionary;
	private static int row;
	private static int column;
	private static String[] sequence1;
	private static String[] sequence2;
	public static int[][] blosumMatrix;
	public static String[][] smithWatersman;
	private static int MATCH = 1;
	private static int MISMATCH = -1;
	private static  int GAP = -1;
	private static int o = -1;
	private static int l;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create the foundational array and map
		dictionary = createMap(ARRAY_WORD);
		blosumMatrix = createArray(ARRAY_NUMBER);
		Scanner scan = new Scanner(System.in);
		
		// Testing out three test cases
		
		System.out.println("Test Case with ACACACTA and AGCACACA: Match = 2, MisMatch = -1 Gap Penalty set to -1 constant");
		MATCH = 2;
		createSmithWater("ACACACTA", "AGCACACA");
		printSmithWaterman();
		determineBestScore();
		
		System.out.println();
		System.out.println();
		
		System.out.println("Test Case with ATCAGAGTC and GTCAGTCA: Match = 2, MisMatch = -1 Gap Penalty set to -2 constant");
		GAP = -2;
		createSmithWater("ATCAGAGTC", "GTCAGTCA");
		printSmithWaterman();
		determineBestScore();

		System.out.println();
		System.out.println();
		System.out.println("Test Case with AACAGTCT and AGT: Match = 1, MisMatch = -1 Gap Penalty set to -2 constant");
		MATCH = 1;
		createSmithWater("AACAGTCT", "AGT");
		printSmithWaterman();
		determineBestScore();
		
		System.out.println();
		System.out.println();
		String genome = "a";
		//accepts all user input until they would liek to leave.
		while(genome.charAt(0) != 'q'){
			//ask for the genome sequences and referenecs strand
			System.out.println("Enter the Genome you would like have (or q to quit):" );
			genome = scan.nextLine();
			if(genome.charAt(0) != 'q'){
				System.out.println("Enter the reference strand you would like to use: ");
				String ref = scan.nextLine();
				//ask for personal scores
				System.out.println("Enter the match you would like:");
				MATCH = scan.nextInt();
				System.out.println("Enter the misMatch you would like: ");
				MISMATCH = scan.nextInt();
				System.out.println("Enter the constant gap penalty you would like to use: ");
				GAP = scan.nextInt();
				
				System.out.println("Testing with "+genome+" and "+ref+": Match = "
						+ MATCH+", MisMatch = "+MISMATCH+" Gap Penalty set to "+GAP+" constant");
				
				//create the smith watersman
				createSmithWater(genome, ref);
				printSmithWaterman();
				determineBestScore();
			}
		}
		System.out.println("Thank you");
		
		/*
		//obtain the user's string sequence
		
		System.out.print("Enter the String as the base: ");
		String input = scan.nextLine();	
		//obtain the gene sequence(with an assumption of 4 character)
		System.out.print("Enter the gene you would like to compare: ");
		String gene = scan.nextLine();
		String[] geneW = gene.split("");
		
		System.out.println(calculateBestScore(input,gene,geneW[0],0));
		System.out.println(calculateBestScore(input,gene,geneW[1],1));
		System.out.println(calculateBestScore(input,gene,geneW[2],2));
		System.out.println(calculateBestScore(input,gene,geneW[3],3));
		*/
	}
	
	/**
	 * Method calculateBestScore
	 * 
	 * Determines the best score within the matrix with the given gene and sequence
	 * 
	 * @param original
	 * @param gene
	 * @param geneW
	 * @param pos
	 * @return
	 */
	private static int calculateBestScore(String original, String gene, String geneW, int pos)
	{
		//intialize local variable
		int total = 0;
		int loc = 0;
		
		//set each word to their designated row
		String[] genex = gene.split("");
		int row = dictionary.get(genex[0]);
		int row1 = dictionary.get(genex[1]);
		int row2 = dictionary.get(genex[2]);
		int row3 = dictionary.get(genex[3]);

		String[] word = original.split("");
		//loop through the whole word
		for(int i = 0; i < word.length - 3; i++)
		{
			int temp = 0;
			//find when the letter is equal to itself in the string to determine its
			//biggest sum
			if(word[i].equals(geneW) && (i - pos) < 0)
			{
				//determine the sum of the word
				temp += blosumMatrix[row][dictionary.get(word[i - pos])];
				temp += blosumMatrix[row1][dictionary.get(word[i - pos + 1])];
				temp += blosumMatrix[row2][dictionary.get(word[i - pos + 2])];
				temp += blosumMatrix[row3][dictionary.get(word[i - pos+ 3])];
				//find the maximum number
				if(temp > total)
				{
					System.out.println("Letter is: "+ word[i - pos ] + word[i -pos +1] + word[i -pos + 2] + word[i -pos +3]);
					total = temp;
					
					loc = i;
				}
				
			}
		}
		System.out.println("Current Location: " + loc);
		return total;
	}
	
	/**
	 * Method createMAp
	 * 
	 * Indexes all the character in ARRAY_WORD by creating a map
	 * @param lst
	 * @return dict
	 */
	private static Map<String, Integer> createMap(String lst)
	{
		Map<String, Integer> dict = new HashMap<String, Integer>();
		String[] array = lst.split(" ");
		for(int i = 0; i < array.length;i++)
		{
			dict.put(array[i], i);
		}
		return dict;
	}
	
	/**
	 * Method createArray
	 * 
	 * Creates a 2d array based on the string of integer given 
	 * 
	 * @param lst
	 * @return a 2d array of int
	 */
	private static int[][] createArray(String lst)
	{
		int count = 0;
		int[][] res = new int[24][24];
		String[] array = lst.split(" ");
		
		//create 2d array
		for(int i = 0; i < 24; i++)
		{
			for(int j = 0; j < 24; j++)
			{
				res[i][j] =Integer.parseInt(array[count]);
				//System.out.println(array[count]);
				count++;
						
			}
		}
		
		
		return res;
	}
	
	/**
	 * Method Create Smith Watersman Array
	 * 
	 * This method will split the two strings and by using the gap penalty
	 * and the match/mismatch score, it will determine the best possible
	 * SmithWatersman matrix for the calculation of the best local alignment
	 * 
	 * @param seq1
	 * @param seq2
	 * @return
	 */
	private static int[][] createSmithWater(String seq1, String seq2){

		
		//adding dash for the initial table
		seq1 = "-" + seq1.toUpperCase();
		seq2 = "-" + seq2.toUpperCase();

		//updating length to accommodate the empty top left corner
		row = seq2.length() + 1;
		column = seq1.length() + 1;
		
		//deliminate/split the sequence into an array to easily traverse
		sequence1 = seq1.split("");
		sequence2 = seq2.split("");
	
		//setting up the array
		smithWatersman = new String[row][column];
		
		//initialize the first row and column
		smithWatersman[0][0] = " ";
		
		//creating the basic structure of the array
		for(int i = 1;i < row;i++)
		{
			smithWatersman[i][0] = sequence2[i - 1];
		}
		for(int j = 1; j < column; j++)
		{
			smithWatersman[0][j] = sequence1[j - 1];
		}
		for(int i = 1; i < row; i++)
		{
			for(int j = 1; j < column; j++)
			{
				if(i == 1 || j == 1)
				{
					smithWatersman[i][j]= "" + 0;
				}
				else
					smithWatersman[i][j] = "" + checkScore(i, j);
				
			}
		}

		return null;
	}
	
	/**
	 * Method Check Score
	 * 
	 * This method checks for the which number will be inserted for the row and column
	 * of the given location. It will determine this by taking the maximum of the top,
	 * diagonal, and left number of the given location
	 * 
	 * @param rowLoc
	 * @param columnLoc
	 * @return the max number 
	 */
	private static int checkScore(int rowLoc, int columnLoc){
		int max = 0;
		int change; // determine the match or mismatch number

		if(match(rowLoc, columnLoc)){
			l = 0;
			change = MATCH;
		}
		else {
			l++;
			change = MISMATCH;
		}
		
		//determine the max number
		max = Math.max(max, Math.max(Integer.parseInt(smithWatersman[rowLoc - 1][columnLoc]) + GAP,
						Math.max(Integer.parseInt(smithWatersman[rowLoc-1][columnLoc-1])+ change, 
								Integer.parseInt(smithWatersman[rowLoc][columnLoc-1]) + GAP)));
		
		return max;
	}
	
	/**
	 * Method Check Match
	 * 
	 * This method returns a boolean if the two characters are matching or not
	 * @param rowLoc
	 * @param columnLoc
	 * @return a boolean 
	 */
	private static boolean match(int rowLoc, int columnLoc)
	{
		
		return smithWatersman[rowLoc][0].equals(smithWatersman[0][columnLoc]);
	}
	
	/**
	 * Method Print Array
	 */
	private static void printSmithWaterman(){
		for(int i = 0; i < row;i++)
		{
			for(int j = 0; j < column; j++)
			{
				if(smithWatersman[i][j].length() == 1)
					System.out.print(smithWatersman[i][j]+ "  ");
				else
					System.out.print(smithWatersman[i][j]+ " ");
			}
			
			System.out.println();
		}
	}

	/**
	 * Method determining the best score
	 * 
	 * This method prints out and determines the best local alignment for the two
	 * given strands
	 */
	private static void determineBestScore(){
		//creates the two variable for the final result
		ArrayList<String> sequenceRes = new ArrayList<String>();
		ArrayList<String> sequenceRes2 = new ArrayList<String>();
		
		//this variable will be implemeneted later to determine the path of all possible
		//local alignment
		ArrayList<Pair> path = new ArrayList<Pair>();
		
		//this helps the iteration and traveling frm the end of the matrix to the beginning
		int i = row - 1;
		int j = column - 1;
		
		//iterating through the whole matrix
		while(i != 1 || j != 1){
			//checks the base case if the indexes are at the end and can either
			//only traverse left or up
			if((i - 1) == 0 && j > 0){
				sequenceRes.add(smithWatersman[0][j]);
				sequenceRes2.add("-");
				j--;
			}
			else if ((j - 1 ) == 0 && i > 0)
			{
				sequenceRes.add("-");
				sequenceRes2.add(smithWatersman[i][0]);
				i--;
			}
			
			//checks for the numbers to traverse left, diag, or up
			else{
				
				//store all the 3 integers that are being checked
				int diag = Integer.parseInt(smithWatersman[i-1][j-1]);
				int left = Integer.parseInt(smithWatersman[i][j-1]);
				int up = Integer.parseInt(smithWatersman[i-1][j]);

				//if the diagonal is greater then the left number
				if(diag > left){
					//checks if the diag is the greatest number
					if(diag > up){
						sequenceRes.add(smithWatersman[0][j]);
						sequenceRes2.add(smithWatersman[i][0]);
						i--;
						j--;
					}
					//if the diag is not greater then the number above then it will traverse up
					else{
						sequenceRes.add("-");
						sequenceRes2.add(smithWatersman[i][0]);
						i--;
					}
				}
				//checks if the left is greater than the diag number
				else if(left > diag){
					//if the left number is greater than the diag number then check
					//if it is greater then the number above
					if(left > up){
						sequenceRes.add(smithWatersman[0][j]);
						sequenceRes2.add("-");
						j--;
					}
					else{
						sequenceRes.add("-");
						sequenceRes2.add(smithWatersman[i][0]);
						i--;
					}
				}
				//checks if the number above is greater then then number at the diagonal
				else if(up > diag){
					//checks if the number above is greater then the number to the left
					if(up > left)
					{
						sequenceRes.add("-");
						sequenceRes2.add(smithWatersman[i][0]);
						i--;
						
					}
					else{
						sequenceRes.add(smithWatersman[0][j]);
						sequenceRes2.add("-");
						j--;
					}
					
				}
				
				//deals the case that if the number to the left or above is not greater than
				//but equals to the number diagonal then you are required to move
				//diagonal.
				else if (diag == left || diag == up)
				{
					sequenceRes.add(smithWatersman[0][j]);
					sequenceRes2.add(smithWatersman[i][0]);
					i--;
					j--;
				}

			}

		}
		
		//prints out the solution
		for(int first = sequenceRes.size()-1; first >= 0;first--)
		{
			System.out.print(sequenceRes.get(first));
		}
		System.out.println();
		for(int second = sequenceRes2.size() -1 ; second >= 0;second--)
		{
			System.out.print(sequenceRes2.get(second));
		}
	}
	
	/**
	 * Method bestScoreHelper
	 * 
	 * TO BE IMPLEMENTED
	 * 
	 * a method that will help recursively determine all the possible alignment
	 * 
	 * @param i
	 * @param j
	 * @param path
	 * @param total
	 * @return
	 */
	private static ArrayList<Pair> bestScoreHelper(int i, int j,ArrayList<Pair> path, int total)
	{
		if(i == 0 || j == 0)
		{
			return path;
		}
		return bestScoreHelper(i,j,path, total);
	}
	
	
}
