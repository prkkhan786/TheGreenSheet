public class ArrayQuestions {
    public static void main(String[] args) {

        // int a[] = { 10, 20, -30, -1 };
        // int n = a.length;
        // int k = 3;
        // int res = kConcetenation(a, 3);
        // System.out.println(res);
        // int a[] = { 1, -2, 3, -2 };
        // System.out.println(maxCircularSumBetter(a));



        int [] a = {-2, -3, 4, -1, -2, 1, 5, -3}; 
        System.out.println(kadane(a));
        System.out.println(kadane2(a));

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

    // https://www.geeksforgeeks.org/maximum-contiguous-circular-sum/
    public static int maxCircularSum(int arr[]) {

        // there can be two cases
        // 1: element are wrapped [1,2,3,-7,-7] -- use kadane
        // 2: elements are not wrpped {10, -12, 11},

        int max_sum_from_kadane = kadane(arr);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            arr[i] = -arr[i];
        }
        int reverse_array_max_sum = kadane(arr);
        reverse_array_max_sum = reverse_array_max_sum + sum;
        return (reverse_array_max_sum > max_sum_from_kadane) ? reverse_array_max_sum : max_sum_from_kadane;

    }

    public static int maxCircularSumBetter(int arr[]) {

        int curr_min = arr[0];
        int over_min = arr[0];

        int curr_max = arr[0];
        int over_max = arr[0];

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        for (int i = 1; i < arr.length; i++) {

            curr_max = Math.max(curr_max + arr[i], arr[i]);
            over_max = Math.max(curr_max, over_max);

            // Kadane's Algorithm to find Minimum subarray sum.
            curr_min = Math.min(curr_min + arr[i], arr[i]);
            over_min = Math.min(over_min, curr_min);
        }

        if (sum == over_min) {
            return over_max;
        }
        System.out.println(over_min);
        System.out.println(over_max);

        return Math.max(over_max, sum - over_min);

    }

    private static int kadane(int[] arr) {

        int curr_sum = arr[0];
        int overall_sum = 0;

        for (int i = 1; i < arr.length; i++) {

            if (curr_sum < 0) {
                curr_sum = 0;
            } else {
                curr_sum += arr[i];
            }
            if (overall_sum < curr_sum) {
                overall_sum = curr_sum;
            }
        }
        return overall_sum;
    }

    private static int kadane2(int[] arr) {
        int curr_sum = arr[0];
        int over_sum = 0;

        for (int i = 1; i < arr.length; i++) {
            curr_sum = Math.max(curr_sum + arr[i], arr[i]);
            over_sum = Math.max(curr_sum, over_sum);
        }
        return over_sum;
    }
}
