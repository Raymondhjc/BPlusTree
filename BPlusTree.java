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
		TreeNode node = findLeaf(key);
		// ------- little redundant here for now
		if (node == null) {
			this.root = new LeafNode(null, key, value);
			return;
		}
		// ---- decide m here
		((LeafNode)node).insertPair(key, value);
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
		while (node.type != "leaf") {
			node = ((IndexNode) node).searchIndex(key);
		}
		return node;
	}
}