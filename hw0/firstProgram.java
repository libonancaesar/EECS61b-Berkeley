public class firstProgram{
	public static boolean threeSum(int[] anArray) {
		int arrLen = anArray.length;
		for (int i = 0; i < arrLen; i++) {
			for (int j = 0; j < arrLen; j++) {
				for (int k = 0; k < arrLen; k++) {
					if (anArray[i] + anArray[j] + anArray[k] == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}


	public static void main (String[] args) {
		int[] arra = {8,2,-1,15};
		System.out.println(threeSum(arra));
	}
}