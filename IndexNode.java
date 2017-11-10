package bplustree;

import java.util.List;
import java.util.ArrayList;

public class IndexNode extends TreeNode {
	private int j;
	private ArrayList<TreeNode> a;
	private ArrayList<Double> k;

	// public IndexNode(TreeNode a0, TreeNode a1TreeNode, double key) {
	// 	this.type = "index";
	// 	this.j = 0;
	// 	this.a.add(a0);
	// 	this.a.add(a1);
	// 	this.k.add(key);
	// }
	public IndexNode(IndexNode parent, ArrayList<TreeNode> a, ArrayList<Double> k) {
		this.type = "index";
		this.parent = parent;
		this.j = k.size();
		this.a = a;
		this.k = k;
	}

	/* insert a new key according to right child, even node will become deficient */
	public void insertIndex(IndexNode node) {
		j = j + 1;
		for (int i = 0; i < j - 1; i++) {
			// to handle duplicate insertion, insert the new pair on the left of the duplication
			// ------- change tie breaker here for further specification
			if (node.getKeys().get(0) <= this.k.get(i)) {
				this.k.add(i, node.getKeys().get(0));
				this.a.add(i, node.getAs().get(0));
				return;
			}
		}
		this.k.add(node.getKeys().get(0));
		this.a.add(node.getAs().get(0));
	}

	public IndexNode splitIndex() {
		List<TreeNode> aSub = this.a.subList(j / 2, this.a.size());
		List<Double> kSub = this.k.subList((j + 1) / 2, this.k.size());
		ArrayList<TreeNode> newA = new ArrayList<>(aSub);
		ArrayList<Double> newK = new ArrayList<>(kSub);
		aSub.clear();
		kSub.clear();
		IndexNode newIndex = new IndexNode(this.parent, newA, newK);
		this.j = this.k.size();
		//form a new index node with a0, a1 and k1
        ArrayList<TreeNode> a = new ArrayList<>();
        a.add(this);
        a.add(newIndex);
        ArrayList<Double> k = new ArrayList<>();
        k.add(this.k.get(j - 1));
		return new IndexNode(null, a, k);
	}

	public TreeNode searchIndex(double key) {
		// now linear search ------- implement binary search later
		for (int i = 0; i <= k.size(); i++) {
			// to handle duplicate, insert the same key in the right child
			if (key < k.get(i)) {
				return a.get(i + 1);
			}
		}
		return a.get(k.size());
	}

	public int getNumber() {
		return this.j;
	}

	public ArrayList<Double> getKeys() {
		return this.k;
	}

	public ArrayList<TreeNode> getAs() {
		return this.a;
	}
}