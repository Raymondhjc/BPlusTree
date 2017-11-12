package bplustree;

import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

public class LeafNode extends TreeNode {
    // public class Pair {
    //     double K;
    //     String V;

    //     public Pair(double key, String value) {
    //         this.K = key;
    //         this.V = value;
    //     }
    // }

    private ArrayList<Pair<Double, String>> pairs;
    private LeafNode lSib;
    private LeafNode rSib;

    public LeafNode(IndexNode parent, ArrayList<Pair<Double, String>> p) {
        this.type = "leaf";
        this.pairs = p;
        this.parent = parent;
    }

    /* returns a pair value if key is found, null if fell off */
    public boolean searchPairs(ArrayList<Pair<Double, String>> res, double ...keys) {
        if(keys.length == 1){
            for (int i = 0; i < this.pairs.size(); i++) {
                if (keys[0] == this.pairs.get(i).getKey()) {
                    res.add(this.pairs.get(i));
                }else if(keys[0] < this.pairs.get(i).getKey()){
                    return false;
                }
            }
            return true;
        }else{
            for (int i = 0; i < this.pairs.size(); i++) {
                if(keys[0] > this.pairs.get(i).getKey()){
                    continue;
                }else if (keys[1] >= this.pairs.get(i).getKey()) {
                    res.add(this.pairs.get(i));
                }else{
                    return false;
                }
            }
            return true;
        }
    }

    /* insert a new pair, even node will become deficient */
    public void insertPair(double key, String value) {
        for (int i = 0; i < this.pairs.size(); i++) {
            // to handle duplicate insertion, insert the new pair on the left of the duplication
            // ------- change tie breaker here for further specification
            if (key <= this.pairs.get(i).getKey()) {
                this.pairs.add(i, new Pair<>(key, value));
                return;
            }
        }
        this.pairs.add(new Pair<>(key, value));
    }

    /* splits the element node into two portions whose parent reference is still the old parent, returns a new index node. */
    public IndexNode splitLeaf() {
        List<Pair<Double, String>> sub = this.pairs.subList(this.pairs.size() / 2, this.pairs.size());
        ArrayList<Pair<Double, String>> newPair = new ArrayList<>(sub);
        sub.clear();
        LeafNode newLeaf = new LeafNode(this.parent, newPair);
        newLeaf.lSib = this;
        newLeaf.rSib = this.rSib;
        this.rSib = newLeaf;
        //form a new index node with a0, a1 and k1
        ArrayList<TreeNode> a = new ArrayList<>();
        a.add(this);
        a.add(newLeaf);
        ArrayList<Double> k = new ArrayList<>();
        k.add(newLeaf.pairs.get(0).getKey());
        return new IndexNode(null, a, k);
    }

    public ArrayList<Pair<Double, String>> getPairs() {
        return this.pairs;
    }

    // public LeafNode getLSib() {
    //     return this.lSib;
    // }

    public LeafNode getRSib() {
        return this.rSib;
    }

    // public void setLSib(LeafNode n) {
    //     this.lSib = n;
    // }

    // public void setRSib(LeafNode n) {
    //     this.rSib = n;
    // }

}