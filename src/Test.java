import java.io.IOException;

public class Test {


    public static void main(String[] args) throws IOException, InterruptedException {

        long prevTime = System.currentTimeMillis();
        System.out.println(prevTime);

        Thread.sleep(1000);

        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime);

        long diff = (currentTime - prevTime) / 1000;
        System.out.println(diff);

        double amount = 100.00;
        if (amount != 100) {
            System.out.println(false);
        } else {
            System.out.println(true);
        }


    }

}
