/**
 * Some variations on the ROT13 cipher, https://en.wikipedia.org/wiki/ROT13
 * @author Kern Ormond
 */
public class Rot { 

	/**
	 * Basic ROT13 cipher, replaces characters by shifting 13.
	 * Characters from A-M are shifted up 13. Characters N-Z are shifted down 13.
	 * @param s the string to encode
	 * @return the encoded string
	 */
	public static String cipher13 (String s)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if       (c >= 'a' && c <= 'm') c += 13;
			else if  (c >= 'n' && c <= 'z') c -= 13;
			else if  (c >= 'A' && c <= 'M') c += 13;
			else if  (c >= 'A' && c <= 'Z') c -= 13;
			sb.append(c);
		}

		return sb.toString();
	}

	private static final char[] L26 = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
										'n','o','p','q','r','s','t','u','v','w','x','y','z'};

	private static final char[] U26 = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
										'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	/**
	 * Another implementation of ROT13. A bit more streamlined than the original version.
	 * @param s the string to encode
	 * @return the encoded string
	 */
	public static String cipher13a (String s)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z')
			{
				// (int)(c - 'a') is the index of the current character in the array
				sb.append(L26[(c - 'a' + 13) % 26]);
			}
			else if (c >= 'A' && c <= 'Z')
			{
				// (int)(c - 'A') is the index of the current character in the array
				sb.append(U26[(c - 'A' + 13) % 26]);
			}
			else
			{
				sb.append(c);
			}
		}

		return sb.toString();
	}

	private static final char[] C62 = {'0','1','2','3','4','5','6','7','8','9',
										'A','B','C','D','E','F','G','H','I','J','K','L','M',
										'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
										'a','b','c','d','e','f','g','h','i','j','k','l','m',
										'n','o','p','q','r','s','t','u','v','w','x','y','z'};

	/**
	 * A variation on ROT13, going to ROT31. Implements the shift based on the character set
	 * of all upper and lowercase letters plus the digits 0-9 (62 total characters).
	 * @param s the string to encode
	 * @return the encoded string
	 */
	public static String cipher31 (String s)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9')
			{
				c = C62[(c - '0' + 31) % 62]; //c - '0' = index of original character 
			}
			else if (c >= 'A' && c <= 'Z')
			{
				c = C62[(c - 'A' + 41) % 62]; //c - 'A' + 10 = index of original character
			}
			else if (c >= 'a' && c <= 'z')
			{
				c = C62[(c - 'a' + 67) % 62]; //c - 'a' + 36 = index of original character
			}
			sb.append(c);
		}

		return sb.toString();    	
	}

	/**
	 * A variation on ROT31 that adds a case change to every other character before 
	 * and after finding the replacement character to mix things up a bit more. 
	 * The cipher is still it's own inverse.
	 * @param s the string to encode
	 * @return the encoded string
	 */
	public static String cipher31s (String s)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			c = ((i%2==0)?c:switchCase(c));
			if (c >= '0' && c <= '9')
			{
				c = C62[(c - '0' + 31) % 62]; //c - '0' = index of original character 
			}
			else if (c >= 'A' && c <= 'Z')
			{
				c = C62[(c - 'A' + 41) % 62]; //c - 'A' + 10 = index of original character
			}
			else if (c >= 'a' && c <= 'z')
			{
				c = C62[(c - 'a' + 67) % 62]; //c - 'a' + 36 = index of original character
			}
			c = ((i%2==0)?c:switchCase(c));
			sb.append(c);
		}

		return sb.toString();    	
	}

	/**
	 * Another ROT cipher using most of the visible ASCII characters.
	 * This variation includes the space character ' ' in the cipher and excludes '~'. 
	 * @param s the string to encode
	 * @return the encoded string
	 */
	public static String cipher47 (String s)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c > 31) && (c < 126))
			{
				c = (char)(((c + 'm') % 94) + ' '); // c + 'm' = c - ' ' + 47 + 94
			}
// if want to include '~' and exclude ' '
//			if ((c > 32) && (c < 127)) 
//			{
//				c = (char)(((c + 'l') % 94) + '!');
//			}
			sb.append(c);
		}

		return sb.toString();    	
	}

	/**
	 * Switch the case of a character from upper to lowercase and vice versa.
	 * @param c the character to switch
	 * @return the switched case character
	 */
	private static char switchCase(char c)
	{
		if (Character.isLowerCase(c))
			return Character.toUpperCase(c);
		else if (Character.isUpperCase(c))
			return Character.toLowerCase(c);
		else return c;
	}

	// Visible ASCII characters
	private final static String chars95 = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

	/**
	 * main
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		String s = (args.length > 0) ? args[0] : chars95;
		System.out.println("rot13  : "+cipher13(s));
		System.out.println("rot13  : "+cipher13(cipher13(s)));
		System.out.println("rot13a : "+cipher13a(s));
		System.out.println("rot13a : "+cipher13a(cipher13a(s)));
		System.out.println("rot31  : "+cipher31(s));
		System.out.println("rot31  : "+cipher31(cipher31(s)));
		System.out.println("rot31s : "+cipher31s(s));
		System.out.println("rot31s : "+cipher31s(cipher31s(s)));
		System.out.println("rot47  : "+cipher47(s));
		System.out.println("rot47  : "+cipher47(cipher47(s)));
	}
}