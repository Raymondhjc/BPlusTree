package bplustree;

import java.util.ArrayList;
import javafx.util.Pair;

public class BPlusTreeTest {
	public static void main(String[] args) {
		BPlusTree tree = new BPlusTree(3);
		// tree.Insert(1, "value1");
		// tree.Insert(4, "value2");
		// tree.Insert(5, "value2");
		// tree.Insert(2, "value1");
		// tree.Insert(5, "value1");
		// tree.Insert(6, "value2");
		// tree.Insert(5, "value3");
		// tree.Insert(5, "value4");
		// tree.Insert(9, "value5");
		// tree.Insert(10, "value1");
		tree.Insert(2, "second");
		tree.Insert(1, "first");
		tree.Insert(3, "third1");
		tree.Insert(3, "third2");
		tree.Insert(3, "third2");
		tree.Insert(3, "third3");
		tree.Insert(6, "sixth");
		tree.Insert(-3, "minus three");
		tree.Insert(-2, "minus two");
		tree.Insert(-1, "minus one");
		printResult(tree.Search(-3));

		visualize(tree.getRoot());

	}

	public static void printResult(ArrayList<Pair<Double, String>> res) {
		for (Pair<Double, String> p : res) {
			System.out.println("("+p.getKey()+","+p.getValue()+")");
		}
	}

	public static void visualize(TreeNode node) {
		if (node.getType() == "leaf") {
			for (int i = 0; i < ((LeafNode) node).getPairs().size(); i++) {
				System.out.print("(" + ((LeafNode) node).getPairs().get(i).getKey() + ")");
			}
			System.out.print("|");
			return;
		} else {
			for (int i = 0; i <= ((IndexNode) node).getNumber(); i++) {
				if (i < ((IndexNode) node).getNumber()) {
					System.out.print("<" + ((IndexNode) node).getKeys().get(i) + ">");
				}
			}
			System.out.println("$");
			for (int i = 0; i <= ((IndexNode) node).getNumber(); i++) {
				visualize(((IndexNode) node).getAs().get(i));
			}
		}
	}
}