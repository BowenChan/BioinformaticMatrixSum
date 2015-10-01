import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BioinformaticMatrix {
	private static final String ARRAY_WORD = "A R N D C Q E G H I L K M F P S T W Y V B Z X *";
	private static final String ARRAY_NUMBER = "4 -1 -2 -2 0 -1 -1 0 -2 -1 -1 -1 -1 -2 -1 1 0 -3 -2 0 -2 -1 0 -4 -1 5 0 -2 -3 1 0 -2 0 -3 -2 2 -1 -3 -2 -1 -1 -3 -2 -3 -1 0 -1 -4 -2 0 6 1 -3 0 0 0 1 -3 -3 0 -2 -3 -2 1 0 -4 -2 -3 3 0 -1 -4 -2 -2 1 6 -3 0 2 -1 -1 -3 -4 -1 -3 -3 -1 0 -1 -4 -3 -3 4 1 -1 -4 0 -3 -3 -3 9 -3 -4 -3 -3 -1 -1 -3 -1 -2 -3 -1 -1 -2 -2 -1 -3 -3 -2 -4 -1 1 0 0 -3 5 2 -2 0 -3 -2 1 0 -3 -1 0 -1 -2 -1 -2 0 3 -1 -4 -1 0 0 2 -4 2 5 -2 0 -3 -3 1 -2 -3 -1 0 -1 -3 -2 -2 1 4 -1 -4 0 -2 0 -1 -3 -2 -2 6 -2 -4 -4 -2 -3 -3 -2 0 -2 -2 -3 -3 -1 -2 -1 -4 -2 0 1 -1 -3 0 0 -2 8 -3 -3 -1 -2 -1 -2 -1 -2 -2 2 -3 0 0 -1 -4 -1 -3 -3 -3 -1 -3 -3 -4 -3 4 2 -3 1 0 -3 -2 -1 -3 -1 3 -3 -3 -1 -4 -1 -2 -3 -4 -1 -2 -3 -4 -3 2 4 -2 2 0 -3 -2 -1 -2 -1 1 -4 -3 -1 -4 -1 2 0 -1 -3 1 1 -2 -1 -3 -2 5 -1 -3 -1 0 -1 -3 -2 -2 0 1 -1 -4 -1 -1 -2 -3 -1 0 -2 -3 -2 1 2 -1 5 0 -2 -1 -1 -1 -1 1 -3 -1 -1 -4 -2 -3 -3 -3 -2 -3 -3 -3 -1 0 0 -3 0 6 -4 -2 -2 1 3 -1 -3 -3 -1 -4 -1 -2 -2 -1 -3 -1 -1 -2 -2 -3 -3 -1 -2 -4 7 -1 -1 -4 -3 -2 -2 -1 -2 -4 1 -1 1 0 -1 0 0 0 -1 -2 -2 0 -1 -2 -1 4 1 -3 -2 -2 0 0 0 -4 0 -1 0 -1 -1 -1 -1 -2 -2 -1 -1 -1 -1 -2 -1 1 5 -2 -2 0 -1 -1 0 -4 -3 -3 -4 -4 -2 -2 -3 -2 -2 -3 -2 -3 -1 1 -4 -3 -2 11 2 -3 -4 -3 -2 -4 -2 -2 -2 -3 -2 -1 -2 -3 2 -1 -1 -2 -1 3 -3 -2 -2 2 7 -1 -3 -2 -1 -4 0 -3 -3 -3 -1 -2 -2 -3 -3 3 1 -2 1 -1 -2 -2 0 -3 -1 4 -3 -2 -1 -4 -2 -1 3 4 -3 0 1 -1 0 -3 -4 0 -3 -3 -2 0 -1 -4 -3 -3 4 1 -1 -4 -1 0 0 1 -3 3 4 -2 0 -3 -3 1 -1 -3 -1 0 -1 -3 -2 -2 1 4 -1 -4 0 -1 -1 -1 -2 -1 -1 -1 -1 -1 -1 -1 -1 -1 -2 0 0 -2 -1 -1 -1 -1 -1 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 -4 1 ";
	public static Map<String, Integer> dictionary;
	public static int[][] matrix;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create the foundational array and map
		dictionary = createMap(ARRAY_WORD);
		matrix = createArray(ARRAY_NUMBER);
			
		//obtain the user's string sequence
		Scanner scan = new Scanner(System.in);
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
			if(word[i].equals(geneW))
			{
				//determine the sum of the word
				temp += matrix[row][dictionary.get(word[i - pos])];
				temp += matrix[row1][dictionary.get(word[i - pos + 1])];
				temp += matrix[row2][dictionary.get(word[i - pos + 2])];
				temp += matrix[row3][dictionary.get(word[i - pos+ 3])];
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
}
