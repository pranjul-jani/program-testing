import java.util.Base64;

public class Base {

    public static void main(String[] args) {
       String token = baseEncoded("\"b699e9e6-7329-4b87-b9a3-7e310702b1b0\"", "\"e896b015-a4fc-4006-be7e-03529cb9a9b9\"");
       System.out.print(token);
    }


    private static String baseEncoded(String accessToken, String orgToken) {

        String token =  "{\"access_token\": "
                + accessToken
                + ",\"orgId\":"
                + orgToken
                + "}";

        String base64EncodedToken = Base64.getEncoder().encodeToString(token.getBytes());

        return base64EncodedToken;
    }
}
