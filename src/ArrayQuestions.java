public class ArrayQuestions {
    public static void main(String[] args) {

        int a[] = { 10, 20, -30, -1 };
        int n = a.length;
        int k = 3;
        int res = kConcetenation(a, 3);
        System.out.println(res);
    }

    // https://leetcode.com/problems/k-concatenation-maximum-sum/
    public static int kConcetenation(int[] arr, int k) {
        int currentMax = 0;
        int overallmax = 0;
        int n = arr.length;

        for (int i = 0; i < n * k; i++) {
            int val = arr[i % n];
            if (currentMax + val > currentMax) {
                currentMax = currentMax + val;
            } else {
                currentMax = val;
            }
            if (currentMax > overallmax) {
                overallmax = currentMax;
            }
        }
        return overallmax;

    }
}
