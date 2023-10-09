import java.util.*;

public class MapTest {

    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<>();

        arr.add(1);
        arr.add(0, 0);

        System.out.println(arr.contains(2));
        System.out.println(arr.contains(0));

        System.out.println(arr.get(1));

        System.out.println(arr.size());

        System.out.println(arr.remove(1));
        System.out.println(arr.remove(0));

        arr.add(0, 2);
        arr.add(0, 1);
        arr.add(2, 0);
        System.out.println(arr);

        Object[] arr1 = arr.toArray();

        for (int i=0;i<arr1.length;i++) {
            System.out.println(1 + (Integer)arr1[i]);
        }

        int[] arr4 = {1, 2, 3};

        List<int[]> li = Arrays.asList(arr4);

        System.out.println(li);


        SortedMap<Integer, Integer> map = new TreeMap<>();

        map.put(1000, 56);
        map.put(11, 51);
        map.put(13, 5);
        map.put(3, 100000);
        map.put(1, 560);

        System.out.println(map);

        Stack<Integer> st = new Stack<>();

        st.push(1);



    }


}
