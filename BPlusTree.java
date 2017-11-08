package bplustree;

public class BPlusTree {
	public TreeNode root;
	public int order;

	public BPlusTree(int m) {
		Initialize(m);
	}

	public void Initialize(int m) {
		this.order = m;
	}

	public void Insert(double key, String value) {
		LeafNode node = findLeaf(this.root);
		// ------- little redundant here for now
		if (node == null) {
			this.root = new LeafNode(null, key, value);
			return;
		}
		// ---- decide m here
		node.insertPair(key, value);
	}

	public TreeNode Search(double... keys) {
		return null;
	}

	public LeafNode findLeaf(TreeNode node) {
		// empty tree
		if (node == null) {
			return null;
		}
		// if the root is a leaf, return it
		while (node.type != "leaf") {
			node = ((IndexNode) node).searchIndex(key);
		}
		return node;
	}
}