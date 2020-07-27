public class DrawTriangle {
    public static void DrawTriangle(int n) {
        int j = 0;
        while (j < n) {
            for (int i = 0; i < j; i++) {
                System.out.println("*");
            }
            j++;
        }
    }

    public static void main(String[] args) {
        DrawTriangle(10);
    }

}