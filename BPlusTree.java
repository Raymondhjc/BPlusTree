package bplustree;

import java.util.ArrayList;
import javafx.util.Pair;

public class BPlusTree {
	private TreeNode root;
	private int order;

	public BPlusTree(int m) {
		Initialize(m);
	}

	public void Initialize(int m) {
		this.order = m;
	}

	public void Insert(double key, String value) {
		TreeNode node = findLeaf(key, "right");
		if (node == null) {
			ArrayList<Pair<Double, String>> p = new ArrayList<>();
			p.add(new Pair<>(key, value));
			this.root = new LeafNode(null, p);
			return;
		}
		((LeafNode) node).insertPair(key, value);
		// decide if the node become deficient
		if (((LeafNode) node).getPairs().size() == this.order) {
			this.splitNode((LeafNode) node);
		}
	}

	public ArrayList<Pair<Double, String>> Search(double... keys) {
		TreeNode node = findLeaf(keys[0], "left");
		if (node == null) {
			return null;
		}
		ArrayList<Pair<Double, String>> res = new ArrayList<>();
		while (((LeafNode) node).searchPairs(res, keys)) {
			if (((LeafNode) node).getRSib() == null) {
				break;
			}
			node = ((LeafNode) node).getRSib();
		}
		return res;
	}

	/* find leaf node from root */
	public TreeNode findLeaf(double key, String match) {
		// empty tree
		if (this.root == null) {
			return null;
		}
		TreeNode node = this.root;
		// if the root is a leaf, return it
		while (node.getType() != "leaf") {
			node = ((IndexNode) node).searchIndex(key, match);
		}
		return node;
	}

	public void splitNode(LeafNode leaf) {
		IndexNode newIndex = leaf.splitLeaf();
		IndexNode parentIndex = leaf.getParent();
		while (parentIndex != null) {
			parentIndex.insertIndex(newIndex);
			if (parentIndex.getNumber() < this.order) {
				return;
			}
			// if deficient
			newIndex = parentIndex.splitIndex();
			parentIndex = parentIndex.getParent();
		}
		// if reaches root
		newIndex.getAs().get(0).setParent(newIndex);
		newIndex.getAs().get(1).setParent(newIndex);
		this.root = newIndex;
	}

	public TreeNode getRoot() {
		return this.root;
	}
}