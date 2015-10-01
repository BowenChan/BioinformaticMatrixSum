import java.util.HashMap;
import java.util.Map;

public class BioinformaticMatrix {
	private static final String ARRAY_WORD = "A R N D C Q E G H I L K M F P S T W Y V B Z X *";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> dictionary = createMap(ARRAY_WORD);
		
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
}
