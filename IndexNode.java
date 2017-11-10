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
		this.j = this.j + 1;
		for (int i = 0; i < this.j - 1; i++) {
			// to handle duplicate insertion, MUST insert the new INDEX on the most RIGHT of the duplications
			if (node.getKeys().get(0) < this.k.get(i)) {
				this.k.add(i, node.getKeys().get(0));
				this.a.set(i, node.getAs().get(0));
				this.a.add(i + 1, node.getAs().get(1));
				return;
			}
		}
		// reach the end of k
		this.k.add(node.getKeys().get(0));
		this.a.set(this.j - 1, node.getAs().get(0));
		this.a.add(node.getAs().get(1));
	}

	public IndexNode splitIndex() {
		List<TreeNode> aSub = this.a.subList((j + 1) / 2, this.a.size());
		List<Double> kSub = this.k.subList(j / 2 + 1, this.k.size());
		ArrayList<TreeNode> newA = new ArrayList<>(aSub);
		ArrayList<Double> newK = new ArrayList<>(kSub);
		aSub.clear();
		kSub.clear();
		IndexNode newIndex = new IndexNode(this.parent, newA, newK);
		// change the parent of each child in the new index node
		for (TreeNode a : newA) {
			a.parent = newIndex;
		}
		//form a new index node with a0, a1 and k0, remove the middle key from the left child index node
		this.j = this.k.size() - 1;
		ArrayList<Double> k = new ArrayList<>();
		k.add(this.k.get(this.j));
		this.getKeys().remove(this.j);
		ArrayList<TreeNode> a = new ArrayList<>();
		a.add(this);
		a.add(newIndex);
		return new IndexNode(null, a, k);
	}

	public TreeNode searchIndex(double key) {
		// now linear search ------- implement binary search later
		for (int i = 0; i < k.size(); i++) {
			// to handle duplicate, insert the same key in the right child
			if (key < k.get(i)) {
				return a.get(i);
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