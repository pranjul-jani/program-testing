
import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

    class Node {
        int val;
        int rank;
        Node parent;

        Node(int val, int rank) {
            this.val = val;
            this.rank = rank;
        }
    }

    private Map<Integer, Node> map;

    DisjointSet() {
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        DisjointSet ob = new DisjointSet();

        ob.makeSet(1);
        ob.makeSet(2);
        ob.makeSet(3);
        ob.makeSet(4);
        ob.makeSet(5);
        ob.makeSet(6);
        ob.makeSet(7);

        ob.union(1,2);
        ob.union(2,3);

        System.out.println(ob.findSet(2));
        System.out.println(ob.findSet(3));
        System.out.println(ob.findSet(1));
        System.out.println();

        ob.union(4,5);
        ob.union(6,7);
        ob.union(5,6);

        System.out.println(ob.findSet(4));
        System.out.println(ob.findSet(5));
        System.out.println(ob.findSet(6));
        System.out.println(ob.findSet(7));
        System.out.println();

        System.out.println(ob.union(3,7));

        System.out.println(ob.findSet(2));
        System.out.println(ob.findSet(3));

        System.out.println(ob.union(1,4));



    }

    private void makeSet(int val) {
        Node node = new Node(val, 0);
        node.parent = node;
        map.put(val, node);
    }

    private boolean union(int valA, int valB) {

        if(valA == valB) {
            return false;
        }

        Node nodeA = map.get(valA);
        Node nodeB = map.get(valB);

        Node parentA = findSet(nodeA);
        Node parentB = findSet(nodeB);

        if (parentA == parentB) {
            return false;
        }

        if (parentA.rank >= parentB.rank) {
            parentA.rank = parentA.rank == parentB.rank ? parentA.rank + 1 : parentA.rank;
            parentB.parent = parentA;

        } else {
            parentA.parent = parentB;
        }

        return true;

    }

    private int findSet(int val) {
        return findSet(map.get(val)).val;
    }

    private Node findSet(Node current) {

        Node parent = current.parent;
        if(current == parent) {
            return parent;
        }

        current.parent  = findSet(parent);
        return current.parent;

    }

}