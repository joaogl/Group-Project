package group.project.unknown.utils;

public class Utils {
	
	public static boolean areAllTrue(boolean[] array) {
	    for(boolean b : array) {
	    	if (!b) return false;
	    }
	    return true;
	}
	
	public static boolean areAllFalse(boolean[] array) {
		for (boolean b : array) {
			if (b) return false;
		}
		return true;
	}
	
}
