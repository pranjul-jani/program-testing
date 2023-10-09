//i/p: I am a Robot!
//o/p: I ma a Tobor!

//split, reverse, substring methods not allowed

/*
i/p: I am a lEuc3ine
o/p: I ma a eNic3uel

i/p: We aRe l3gEndS
o/p: Ew eRa s3dNegL
 */

public class Sample {


    public static void main(String[] args) {

        String input = "I am a Robot!";
        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0;i<input.length();i++) {
            for(int j = i;j<input.length();j++) {
                if(!Character.isLetter(input.charAt(j))) {
                    stringBuffer.append(reverseString(i, j - 1, input));
                    stringBuffer.append(input.charAt(j));
                    i = j + 1;
                }
            }

        }

        System.out.print(stringBuffer.toString());
    }

    public static String reverseString(int beg, int end, String s) {

        char[] curr = new char[end - beg + 1];
        char[] temp = new char[end - beg + 1];

        for(int i=beg;i<=end;i++) {
            curr[i] = s.charAt(i);
        }

        int low = 0;
        int high = temp.length - 1;


        while(low < temp.length && high >= 0) {
            if (curr[low] >= 'a' && curr[low] <= 'z') {
                temp[low] = Character.toLowerCase(curr[high]);
                low += 1;
                high -= 1;
            } else if (curr[low] >= 'A' && curr[low] <= 'Z') {
                temp[low] = Character.toUpperCase(curr[high]);
                low += 1;
                high -= 1;
            } else {
                temp[low] = curr[low];
                low += 1;
            }
        }


        String output = "";

        for(int i=0;i<temp.length;i++) {
            output += temp[i];
        }

        return output;

    }

}
