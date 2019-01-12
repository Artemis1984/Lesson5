public class Lesson5 {


    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public static void main(String[] args) {
        Lesson5 lesson5 = new Lesson5();
        lesson5.method1();
        lesson5.method2();

    }

    public void method1(){
        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println("метод 1 выполнил работу за: " + (System.currentTimeMillis() - a)/1000.0 + " секунд");
    }

    public void method2(){
        long a = System.currentTimeMillis();
        new Thread(() -> {
            float[] a1 = new float[h];
            System.arraycopy(arr,0,a1,0,h);

            for (int i = 0; i < h; i++) {
                a1[i] = 1;
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(a1, 0, arr, 0, h);
        }).start();

        new Thread(() -> {
            float[] a2 = new float[h];
            System.arraycopy(arr,h,a2,0,h);

            for (int i = 0; i < h; i++) {
                a2[i] = 1;
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(a2, 0, arr, h, h);

        System.out.println("метод 2 выполнил работу за: " + (System.currentTimeMillis() - a)/1000.0 + " секунд");
        }).start();

    }

}
