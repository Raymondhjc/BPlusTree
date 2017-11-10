package bplustree;

import java.util.ArrayList;

public class IndexNode extends TreeNode {
	private int j;
	private ArrayList<TreeNode> a;
	private ArrayList<Double> k;

	public IndexNode(IndexNode parent) {
		this.parent = parent;
		this.type = "index";
	}

	public boolean insertIndex(double key) {
		j++;
		return true;
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

	public int getNumber(){
		return this.j;
	}
}