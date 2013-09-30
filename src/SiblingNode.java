public class SiblingNode {

    private int val;
    private SiblingNode llink;
    private SiblingNode rlink;
    private SiblingNode slink;

    public SiblingNode(int val) {
        this.val = val;
        this.llink = null;
        this.rlink = null;
        this.slink = null;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public SiblingNode getLlink() {
        return llink;
    }

    public void setLlink(SiblingNode llink) {
        this.llink = llink;
    }

    public SiblingNode getRlink() {
        return rlink;
    }

    public void setRlink(SiblingNode rlink) {
        this.rlink = rlink;
    }

    public SiblingNode getSlink() {
        return slink;
    }

    public void setSlink(SiblingNode slink) {
        this.slink = slink;
    }

}
