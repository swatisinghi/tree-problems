import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SiblingTree {

    private class QueueNode {

        private SiblingNode node;
        private int level;

        public QueueNode(SiblingNode node, int level) {
            this.node = node;
            this.level = level;
        }

        public SiblingNode getNode() {
            return node;
        }

        public void setNode(SiblingNode node) {
            this.node = node;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    private Scanner in;
    private int[] elements;
    private SiblingNode root;
    private int size;


    public SiblingTree(int size) {
        in = new Scanner(System.in);
        this.size = size;
        this.elements = new int[size];
    }


    private void insert(SiblingNode root, int val) {
        if (val <= root.getVal()) {
            if (root.getLlink() != null) {
                insert(root.getLlink(), val);
            } else {
                root.setLlink(new SiblingNode(val));
            }
        } else {
            if (root.getRlink() != null) {
                insert(root.getRlink(), val);
            } else {
                root.setRlink(new SiblingNode(val));
            }
        }
    }

    private void inorder(SiblingNode root) {

        if (root == null)
            return;

        inorder(root.getLlink());
        System.out.print(root.getVal() + "->");
        inorder(root.getRlink());
    }

    private void addSiblingPointers(Queue<SiblingNode> queue) {

        if (queue.isEmpty())
            return;

        Queue<SiblingNode> newQueue = new LinkedList<SiblingNode>();

        SiblingNode queueNode = queue.remove();

        if (queue.isEmpty())
            queueNode.setSlink(null);
        if (queueNode.getLlink() != null)
            newQueue.add(queueNode.getLlink());
        if (queueNode.getRlink() != null)
            newQueue.add(queueNode.getRlink());

        while (!queue.isEmpty()) {
            SiblingNode nextNode = queue.element();
            if (nextNode != null) {
                if (nextNode.getLlink() != null)
                    newQueue.add(nextNode.getLlink());
                if (nextNode.getRlink() != null)
                    newQueue.add(nextNode.getRlink());
                queueNode.setSlink(nextNode);
            } else {
                queueNode.setSlink(null);
            }

            queueNode = queue.remove();
        }

        addSiblingPointers(newQueue);

    }

    private void create() {
        System.out.println("Enter the size");
        for (int i = 0; i < size; i++) {
            elements[i] = in.nextInt();
        }

        root = new SiblingNode(elements[0]);
        System.out.println("Creating the tree");
        for (int i = 1; i < size; i++) {
            insert(root, elements[i]);
        }
        inorder(root);

        Queue<SiblingNode> queue = new LinkedList<SiblingNode>();
        queue.add(root);
        addSiblingPointers(queue);

        System.out.println();
        System.out.println(root.getLlink().getRlink().getSlink());
        System.out.println(root.getSlink());
        System.out.println(root.getRlink().getSlink());
        System.out.println(root.getLlink().getSlink().getVal());
        System.out.println(root.getLlink().getLlink().getSlink().getVal());
        System.out.println(root.getLlink().getRlink().getLlink().getSlink().getVal());

    }

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size");
        int size = in.nextInt();
        SiblingTree siblingTree = new SiblingTree(size);

        siblingTree.create();

    }

}
