import java.util.Arrays;

/**
 * TODO Kommentare schreiben
 * 
 * @author Steffen Dworsky, Ramón Schultz
 *
 */
public class LicenseKeyCracker {

	public static void main(String[] args) {

		char[] charset = "0123456789".toCharArray();
		Arrays.sort(charset);
		LicenseKeyCracker bf = new LicenseKeyCracker(charset, 8);

		while (true) {

			String attempt = bf.toString();
			System.out.println(attempt);
			// attempt = getSecurePassword(attempt, salt);
			// System.out.println("" + attempt);

			bf.increment();
		}
	}

	private char[] cs; // Character Set
	private char[] cg; // Current Guess

	public LicenseKeyCracker(char[] characterSet, int guessLength) {
		cs = characterSet;
		cg = new char[8];
		Arrays.fill(cg, cs[0]);
	}

	public void increment() {
		int index = cg.length - 1;
		while (index >= 0 && index <= 7) {
			if (cg[index] == cs[cs.length - 1]) {
				if (index == 0) {
					cg = new char[cg.length + 1];
					Arrays.fill(cg, cs[0]);
					break;
				} else {
					cg[index] = cs[0];
					index--;
				}
			} else {
				cg[index] = cs[Arrays.binarySearch(cs, cg[index]) + 1];
				break;
			}
		}

	}

	@Override
	public String toString() {
		return String.valueOf(cg);
	}

//converts a char[] to byte[] without creating a string.
//    private static byte[] toBytes(char[] chars) {
//        CharBuffer charBuffer = CharBuffer.wrap(chars);
//        ByteBuffer byteBuffer = Charset.defaultCharset().encode(charBuffer);
//        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
//                byteBuffer.position(), byteBuffer.limit());
//        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
//        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
//        return bytes;
//    }   
}