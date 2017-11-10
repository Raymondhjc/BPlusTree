package bplustree;
public class TreeNode{
    //public int order;
    protected IndexNode parent;
    protected String type;
    public TreeNode(){

    }
    public String getType(){
        return this.type;
    }
    public IndexNode getParent(){
        return this.parent;
    }
}