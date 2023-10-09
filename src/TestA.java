import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class TestA {

    public static void main(String[] args) throws IOException {

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String currentTimeMillis = Long.toHexString(Instant.now().toEpochMilli());
        String randomInteger = Integer.toHexString(random.nextInt(65536));
        System.out.println(randomInteger);
        System.out.println(currentTimeMillis.concat("-").concat(randomInteger));

    }



}
