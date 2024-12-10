public class SortingAlgorithms {
    public static void bubbleSort(int[] array, SortingVisualizer visualizer, int speed) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                visualizer.updateArray(array);
            }
        }
    }

    public static void selectionSort(int[] array, SortingVisualizer visualizer, int speed) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;

            visualizer.updateArray(array);
        }
    }
    public static void insertionSort(int[] array, SortingVisualizer visualizer, int speed) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                visualizer.updateArray(array);
            }
            array[j + 1] = key;
            visualizer.updateArray(array);
        }
    }
    public static void mergeSort(int[] array, SortingVisualizer visualizer, int speed) {
        mergeSortHelper(array, 0, array.length - 1, visualizer, speed);
    }

    private static void mergeSortHelper(int[] array, int left, int right, SortingVisualizer visualizer, int speed) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortHelper(array, left, mid, visualizer, speed);
            mergeSortHelper(array, mid + 1, right, visualizer, speed);

            merge(array, left, mid, right, visualizer, speed);
        }
    }

    private static void merge(int[] array, int left, int mid, int right, SortingVisualizer visualizer, int speed) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
            visualizer.updateArray(array);
        }

        while (i < n1) {
            array[k++] = leftArray[i++];
            visualizer.updateArray(array);
        }

        while (j < n2) {
            array[k++] = rightArray[j++];
            visualizer.updateArray(array);
        }
    }
    public static void quickSort(int[] array, SortingVisualizer visualizer, int speed) {
        quickSortHelper(array, 0, array.length - 1, visualizer, speed);
    }

    private static void quickSortHelper(int[] array, int low, int high, SortingVisualizer visualizer, int speed) {
        if (low < high) {
            int pi = partition(array, low, high, visualizer, speed);

            quickSortHelper(array, low, pi - 1, visualizer, speed);
            quickSortHelper(array, pi + 1, high, visualizer, speed);
        }
    }

    private static int partition(int[] array, int low, int high, SortingVisualizer visualizer, int speed) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            visualizer.updateArray(array);
             }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        visualizer.updateArray(array);
        return i + 1;
    }

}
