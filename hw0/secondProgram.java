public class secondProgram {

	public static boolean ifZero(int[] array) {
		for (int k = 0; k < array.length; k++) {
			for (int s = 0; s < array.length; s++) {
				if (array[k] == array[s]) {
					continue;
				}

				for (int l = 0; l < array.length; l++) {
					if ((array[k] == array[l]) || (array[s] == array[l])) {
						continue;
					}
					if (array[l] + array[s] + array[k] == 0) {
						
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] ar = {5, 1, 0, 3, 6};
		System.out.println(ifZero(ar));
	}


}