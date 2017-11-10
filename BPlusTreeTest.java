package bplustree;
public class BPlusTreeTest{
	public static void main(String[] args){
		BPlusTree tree = new BPlusTree(4);
		tree.Insert(3,"value1");
		tree.Insert(4,"value2");
		tree.Insert(5,"value2");
		//System.out.println(((LeafNode)tree.root).pairs.get(0).key);
	}

}