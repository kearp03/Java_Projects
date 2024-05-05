package music;

public class MajorMinor {
	/* parallel 2-D String array of the major and minor keys
	 * keys[0] is the list of major keys
	 * keys[1] is the list of minor keys
	 */
	private static final String[][] keys = {  
		{"C", "G", "D", "A" , "E" , "B" , "Cb", "F#", "Gb", "C#", "Db", "Ab", "Eb", "Bb", "F"}, 
		{"a", "e", "b", "f#", "c#", "g#", "ab", "d#", "eb", "a#", "bb", "f" , "c" , "g" , "d"}
	};
	
	//static method to find relative minor for a major key
	public static void majorToMinor(String majorKey) {
		//ensures majorKey is within the correct length
		if(majorKey.length() > 0 && majorKey.length() < 3) {
			//loops through the major keys until it finds the index of the entered major key
			for(int i = 0; i < keys[0].length; i++) {
				if(keys[0][i].toUpperCase().equals(majorKey.toUpperCase())) {
					//prints the minor key in the same index of the major key entered
					System.out.println("Relative minor: " + keys[1][i]);
					//terminates method
					return;
				}
			}
		}
		//prints error message if for loops ends without finding a matching key
		System.out.println("ERROR: Key not recognized");
	}
	
	//static method to find relative major for a minor key
	public static void minorToMajor(String minorKey) {
		//ensures minorKey is within the correct length
		if(minorKey.length() > 0 && minorKey.length() < 3) {
			//loops through the minor keys until it finds the index of the entered minor key
			for(int i = 0; i < keys[1].length; i++) {
				if(keys[1][i].toLowerCase().equals(minorKey.toLowerCase())) {
					//prints the major key in the same index of the minor key entered
					System.out.println("Relative major: " + keys[0][i]);
					//terminates method
					return;
				}
			}
		}
		//prints error message if for loops without finding a matching key
		System.out.println("ERROR: Key not recognized");
	}
}