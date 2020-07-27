public class MaxNumber {
    public static int max(int[] m) {
        int max = 0;
        for (int i : m) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);
        return max;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        max(numbers);
    }

}
