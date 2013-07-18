import java.util.List;
import java.util.ArrayList;

/**
 * Coding sample for a job interview
 * @author Kern Ormond
 */
public class Sample {

	/**
	 * Take a nested list of unknown depth and print each item (to System.out) on a separate line.
	 * The value of any given line should be the line prefix followed by the depth and position of the item in the list.
	 *
	 * Assumes that the list contains only Strings and other lists.
	 * 
	 * For example, if we have the following list (represented here using array notation):
	 * list = ["Red",["Yellow","Green","Blue"],"Black",["White"]]
	 * 
	 * Calling printList("Test", list) would produce the following:
	 *
	 * Test.0: Red
	 * Test.1.0: Yellow
	 * Test.1.1: Green
	 * Test.1.2: Blue
	 * Test.2: Black
	 * Test.3.0: White
	 * 
	 * @param linePrefix	A string to be used at the start of each printed line
	 * @param list			A list of objects.  Each object in this list will either be a String or a List
	 */
	@SuppressWarnings("unchecked")
	public static void printList(String linePrefix, List<Object> list) 
	{
		for (int i = 0; i < list.size(); i++) 
		{
			Object item = list.get(i);
			if (item instanceof List)
				printList(linePrefix + "." + i, (List<Object>)item);
			else
				System.out.println (linePrefix + "." + i + ": " + item);
		}
	}

	/**
	 * Test the printList method
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// list = ["Red",["Yellow","Green","Blue"],"Black",["White"]]
		ArrayList<Object> list1 = new ArrayList<Object>();
		ArrayList<Object> list2 = new ArrayList<Object>();
		ArrayList<Object> list3 = new ArrayList<Object>();
		list2.add("Yellow");
		list2.add("Green");
		list2.add("Blue");
		
		list3.add("White");

		list1.add("Red");
		list1.add(list2);
		list1.add("Black");
		list1.add(list3);
		
		printList("Test", list1);
	}

}
