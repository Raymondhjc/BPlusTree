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
		TreeNode node = findLeaf(key);
		// ------- little redundant here for now
		if (node == null) {
			ArrayList<Pair<Double, String>> p = new ArrayList<>();
			p.add(new Pair<>(key, value));
			this.root = new LeafNode(null, p);
			return;
		}
		// ---- decide m here
		((LeafNode) node).insertPair(key, value);
		if (((LeafNode) node).getPairs().size() == this.order) {
			this.splitNode((LeafNode) node);
		}
	}

	public TreeNode Search(double... keys) {
		return null;
	}

	/* find leaf node from root */
	public TreeNode findLeaf(double key) {
		// empty tree
		if (this.root == null) {
			return null;
		}
		TreeNode node = this.root;
		// if the root is a leaf, return it
		while (node.getType() != "leaf") {
			node = ((IndexNode) node).searchIndex(key);
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