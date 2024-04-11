public class MergeSort {
    private static int basicOperations = 0;

    private static void sort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            basicOperations += 3; // Account for comparison, start + end, and /2
            sort(array, start, mid);
            basicOperations += 1; // Account for mid + 1
            sort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        basicOperations += 3; // Account for end - start, then the +1, then the mid + 1
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
            basicOperations += 7; // 3 comparisons each iteration, then another comparison for if(), then two ++
        }

        
        while (i <= mid) {
            temp[k++] = array[i++];
            basicOperations++; 
        }

        
        while (j <= end) {
            temp[k++] = array[j++];
            basicOperations++; 
        }

        
        for (i = start, k = 0; i <= end; i++, k++) {
            array[i] = temp[k];
            basicOperations++; 
        }
    }
}

