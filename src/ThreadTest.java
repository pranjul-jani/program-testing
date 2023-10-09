public class ThreadTest {

    public static void main(String[] args) {

        for (int i=0;i<10000000;i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }

        System.out.println("********* " + Thread.currentThread().getName());

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("ThreadName: " + Thread.currentThread().getName());
        }
    }

}




