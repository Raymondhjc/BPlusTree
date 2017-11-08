package bplustree;

import java.util.ArrayList;

public class IndexNode extends TreeNode {
	public int j;
	public ArrayList<TreeNode> a;
	public ArrayList<Double> k;

	public IndexNode(TreeNode parent) {
		this.parent = parent;
		this.type = "index";
	}

	public boolean insertIndex(double key) {
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
}