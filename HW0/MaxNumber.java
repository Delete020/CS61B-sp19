package HW0;

public class MaxNumber {
    public static int max(int[] m){
        int max=0;
        for (int i = 0; i<m.length;i++){
            if(m[i]>max){
                max=m[i];
            }
        }
        System.out.println(max);
        return max;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        //max(numbers);
        MaxNumber m = new MaxNumber();
        m.max(numbers);
    }

}
