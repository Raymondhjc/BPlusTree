package bplustree;

import java.awt.event.KeyListener;
import java.util.ArrayList;

public class LeafNode extends TreeNode {
    public class Pair {
        double key;
        String value;

        public Pair(double key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public ArrayList<Pair> pairs;
    public LeafNode lSib;
    public LeafNode rSib;

    public LeafNode(IndexNode parent, double key, String value) {
        this.type = "leaf";
        this.pairs = new ArrayList<Pair>(key, value);
        this.parent = parent;
    }

    /* returns a pair value if key is found, null if fell off */
    public String searchPair(double key) {
        for (int i = 0; i < pairs.size(); i++) {
            if (key == pairs.get(i).key) {
                return pairs.get(i).value;
            }
        }
        return null;
    }

    /* returns a pair value if key is found, null if fell off */
    public void insertPair(double key, String value) {
        for (int i = 0; i < pairs.size(); i++) {
            // to handle duplicate insertion, insert the new pair on the left of the duplication
            // ------- change tie breaker here for further specification
            if (key <= pairs.get(i).key) {
                pairs.add(i, new Pair(key, value));
            }
        }
    }

}