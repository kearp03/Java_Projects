package algorithms;

public class Algorithm {
	public static int linearSearch(int[] arr, int key){
		for(int i = 0; i < arr.length; i++) if(arr[i] == key) return i;
		return -1;
	}

	public static int binarySearch(int[] arr, int l, int h, int key){
		if(l <= h) {
			int mid = l + (h - l) / 2;

			if(arr[mid] == key) return mid;
			else if(arr[mid] > key) return binarySearch(arr, l, mid-1, key);
			else if(arr[mid] < key) return binarySearch(arr, mid+1, h, key);
		}
		return -1;
	}

	public static int ternarySearch(int[] arr, int l, int h, int key) {
		if(l <= h) {
			int mid1 = l + (h - l) / 3, mid2 = h - (h - l) / 3;

			if(arr[mid1] == key) return mid1;
			else if(arr[mid2] == key) return mid2;
			else if(arr[mid1] > key) return ternarySearch(arr, l, mid1-1, key);
			else if(arr[mid2] < key) return ternarySearch(arr, mid2+1, h, key);
			else return ternarySearch(arr, mid1+1, mid2-1, key);
		}
		return -1;
	}

	public static void bubbleSort(int[] arr){
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}
	
	public static void printArr(int[] arr){
		System.out.print("{");
		for(int i = 0; i < arr.length-1; i++) System.out.print(arr[i] + " ");
		System.out.println(arr[arr.length-1] + "}");
	}

	public static void main(String[] args){
		
	}
}