public class Node {

    private int val;
    private Node llink;
    private Node rlink;

    public Node(int val) {
        this.val = val;
        this.llink = null;
        this.rlink = null;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLlink() {
        return llink;
    }

    public void setLlink(Node llink) {
        this.llink = llink;
    }

    public Node getRlink() {
        return rlink;
    }

    public void setRlink(Node rlink) {
        this.rlink = rlink;
    }


}
