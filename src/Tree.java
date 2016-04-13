import java.util.*;

/**
 * Created by swati on 1/8/16.
 */
public class Tree {

    private Scanner in;
    private Node root;
    private Node root2;
    private int size;
    private  int maxLevel;

    public Tree() {
        this.in = new Scanner(System.in);
        this.size = 0;
    }

    private void insert(int val, Node node) {

        if (val <= node.getVal()) {
            if (node.getLlink() == null) {
                node.setLlink(new Node(val));
            } else {
                insert(val, node.getLlink());
            }
        } else {
            if (node.getRlink() == null) {
                node.setRlink(new Node(val));
            } else {
                insert(val, node.getRlink());
            }
        }
    }

    public void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.getLlink());
        System.out.print(node.getVal() + " -> ");
        inOrder(node.getRlink());
    }

    public void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.getLlink());
        postOrder(node.getRlink());
        System.out.print(node.getVal() + " -> ");

    }

    public void preOrder(Node node) {
        if (node == null)
            return;

        System.out.print(node.getVal() + " -> ");
        preOrder(node.getLlink());
        preOrder(node.getRlink());

    }

    public void create() {

        System.out.println("Enter the root: ");
        this.root = new Node(this.in.nextInt());
        System.out.println("Enter the number of nodes: ");
        int n = in.nextInt();
        System.out.println("Enter the other " + (n - 1) + " nodes: ");
        for (int i = 0; i < n - 1; i++) {
            insert(in.nextInt(), this.root);
        }

    }

    public void createTree2() {

        System.out.println("Enter the root: ");
        this.root2 = new Node(this.in.nextInt());
        System.out.println("Enter the number of nodes: ");
        int n = in.nextInt();
        System.out.println("Enter the other " + (n - 1) + " nodes: ");
        for (int i = 0; i < n - 1; i++) {
            insert(in.nextInt(), this.root2);
        }

    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return max(height(node.getLlink()) + 1, height(node.getRlink()) + 1);
    }

    private void levelOrderTraversal(Node node, int level) {
        if (level == 0)
            return;

        if (level == 1) {
            System.out.print(node.getVal() + " -> ");
        }

        levelOrderTraversal (node.getLlink(), level - 1);
        levelOrderTraversal (node.getRlink(), level - 1);
    }

    private void levelOrder(Node root) {
        int height = height(root);
        for (int i = 1; i <= height; i++) {
            levelOrderTraversal(root, i);
        }
    }

    public void levelOrderQueue(Queue<Node> queue) {
        if (queue.isEmpty())
            return;

        Node node = queue.remove();
        System.out.print(node.getVal() + " -> ");
        if (node.getLlink() != null)
            queue.add(node.getLlink());
        if (node.getRlink() != null)
            queue.add(node.getRlink());

        levelOrderQueue(queue);
    }

    private boolean isBst(Node node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.getVal() > max || node.getVal() < min) {
            return false;
        }

        return (isBst(node.getLlink(), min, node.getVal()) && isBst(node.getRlink(), node.getVal(), max));
    }

    private void printArray(Node[] arr, int level) {
        for (int i = 0; i <=     level; i++){
            System.out.print(arr[i].getVal() + " -> ");
        }
        System.out.println();
    }

    private void printPaths(Node node, Node[] paths, int level) {

        paths[level] = node;

        if (node.getLlink() == null && node.getRlink() == null) {
            printArray(paths, level);
            return;
        }

        if (node.getLlink() != null)
            printPaths(node.getLlink(), paths, level + 1);
        if (node.getRlink() != null)
            printPaths(node.getRlink(), paths, level + 1);
    }

    private int minEle(Node node) {
        while (node.getLlink() != null) {
            node = node.getLlink();
        }

        return node.getVal();
    }

    private void mirror(Node node) {
        if (node == null) {
            return;
        }

        Node temp = node.getLlink();
        node.setLlink(node.getRlink());
        node.setRlink(temp);

        if (node.getLlink() != null)
            mirror(node.getLlink());
        if (node.getLlink() != null)
            mirror(node.getRlink());
    }

    private void size(Node node) {
        if (node == null)
            return;

        this.size += 1;

        size(node.getLlink());
        size(node.getRlink());

    }

    private void clone(Node node, Node clone) {
        if (node == null)
            return;

        clone.setLlink(node.getLlink());
        clone.setRlink(node.getRlink());

        clone(node.getLlink(), clone.getLlink());
        clone(node.getRlink(), clone.getRlink());

    }

    private int countLeaves(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.getLlink() == null && node.getRlink() == null) {
            return 1;
        }

        return (countLeaves(node.getLlink()) + countLeaves(node.getRlink()));
    }

    private void spiral(Node node, int level, boolean dir) {
        if (level == 0)
            return;

        if (level == 1) {
            System.out.print(node.getVal() + " -> ");
        }

        if (dir) {
            spiral(node.getLlink(), level - 1, dir);
            spiral(node.getRlink(), level - 1, dir);
        } else {
            spiral(node.getRlink(), level - 1, dir);
            spiral(node.getLlink(), level - 1, dir);
        }
    }

    private void spiralLevelOrder(Node root) {
        int height = height(root);
        boolean dir = true;
        for (int i = 1; i <= height; i++) {
            spiral(root, i, dir);
            dir = !dir;
            System.out.println();
        }
    }

    private void leftView(Node node, int level) {
        if (node == null)
            return;

        if (level > this.maxLevel) {
            this.maxLevel = level;
            System.out.print(node.getVal() + " -> ");
        }

        leftView(node.getLlink(), level + 1);
        leftView(node.getRlink(), level + 1);
    }

    private void rightView(Node node, int level) {
        if (node == null) {
            return;
        }

        if (level > this.maxLevel) {
            this.maxLevel = level;
            System.out.print(node.getVal() + " -> ");
        }

        rightView(node.getRlink(), level + 1);
        rightView(node.getLlink(), level + 1);
    }

    private void lca(Node node, int n1, int n2) {
        if (node == null) {
            System.out.println("No LCA");
            return;
        }

        if (node.getVal() >= n1 && node.getVal() < n2) {
            System.out.println("LCA is: " + node.getVal());
        } else if(node.getVal() > n1) {
            lca(node.getLlink(), n1, n2);
        } else {
            lca(node.getRlink(), n1, n2);
        }

    }

    private boolean childSumProp(Node node) {
        if (node == null || (node.getLlink() == null && node.getRlink() == null))
            return true;

        int lval = 0, rval = 0;
        if (node.getLlink() != null) {
            lval = node.getLlink().getVal();
        }
        if (node.getRlink() != null) {
            rval = node.getRlink().getVal();
        }
        return (lval + rval == node.getVal() && childSumProp(node.getLlink()) && childSumProp(node.getRlink()));
    }

    private void convertChildSumProp(Node node) {
        if (node == null || (node.getLlink() == null && node.getRlink() == null))
            return;
        convertChildSumProp(node.getLlink());
        convertChildSumProp(node.getRlink());

        int lval = 0, rval = 0;
        if (node.getLlink() != null) {
            lval = node.getLlink().getVal();
        }

        if (node.getRlink() != null) {
            rval = node.getRlink().getVal();
        }

//        diff = lval + rval - node.getVal();
        node.setVal(lval + rval);


    }

    private boolean rootToLeafSum(Node node, int sum) {
        if (node == null) {
            return sum == 0;
        }
        int tempSum = sum - node.getVal();
        if (tempSum == 0 && node.getLlink() == null && node.getRlink() == null) {
            return true;
        }
        return rootToLeafSum(node.getLlink(), tempSum) || rootToLeafSum(node.getRlink(), tempSum);

    }

    private void printLeftBoundary(Node node) {
        if (node == null) {
            return;
        }

        if (node.getLlink() != null) {
            System.out.println(node.getVal());
            printLeftBoundary(node.getLlink());
        } else if (node.getRlink() != null) {
            System.out.println(node.getVal());
            printLeftBoundary(node.getRlink());
        }
    }

    private void printLeaves(Node node) {
        if (node == null) {
            return;
        }

        if (node.getLlink() == null && node.getRlink() == null) {
            System.out.println(node.getVal());
        }

        printLeaves(node.getLlink());
        printLeaves(node.getRlink());

    }

    private void printRightBoundary(Node node) {
        if (node == null) {
            return;
        }
        if (node.getRlink() != null) {
            printRightBoundary(node.getRlink());
            System.out.println(node.getVal());
        } else if (node.getLlink() != null) {
            printRightBoundary(node.getLlink());
            System.out.println(node.getVal());
        }
    }

    private void printBoundary(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getVal());
        printLeftBoundary(node.getLlink());
        printLeaves(node.getLlink());
        printLeaves(node.getRlink());
        printRightBoundary(node.getRlink());
    }

    private Node superImpose(Node root1, Node root2, boolean LeftDir) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root2.setLlink(superImpose(root1.getLlink(), root2.getLlink(), true));
        root2.setRlink(superImpose(root1.getRlink(), root2.getRlink(), true));
        return root2;

    }

    public void printMenu() {
        while (true) {
            System.out.println("Tree Problems");
            System.out.println("1. Inorder");
            System.out.println("2. Postorder");
            System.out.println("3. Preorder");
            System.out.println("4. Height");
            System.out.println("5. Level Order");
            System.out.println("6. Level Order using Queue");
            System.out.println("7. Is BST");
            System.out.println("8. Print Paths");
            System.out.println("9. Minimum Element");
            System.out.println("10. Mirror");
            System.out.println("11. Size");
            System.out.println("12. Clone");
            System.out.println("13. Count Leaves");
            System.out.println("14. Spiral level order");
            System.out.println("15. Left View");
            System.out.println("16. Right View");
            System.out.println("17. LCA");
            System.out.println("18. Children sum property");
            System.out.println("19. Convert children sum property");
            System.out.println("20. Root to Leaf equal to given sum");
            System.out.println("21. Print Boundary");
            System.out.println("22. Superimpose two tress");
            System.out.println("Q. Exit");
            System.out.println("Enter your choice: ");

            String ch = in.next();

            if (ch.equals("1")) {
                inOrder(root);
            } else if (ch.equals("2")) {
                postOrder(root);
            } else if (ch.equals("3")) {
                preOrder(root);
            } else if (ch.equals("4")) {
                System.out.println(height(root));
            } else if (ch.equals("5")) {
                levelOrder(root);
            } else if (ch.equals("6")) {
                Queue<Node> queue = new LinkedList<Node>();
                queue.add(root);
                levelOrderQueue(queue);
            } else if (ch.equals("7")) {
                boolean isBst = isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (isBst) {
                    System.out.println("Yes, it is a BST");
                } else {
                    System.out.println("No, it is not a BST");
                }
            } else if (ch.equals("8")) {
                int height = height(root);
                Node[] paths = new Node[height];
                printPaths(root, paths, 0);
            } else if (ch.equals("9")) {
                System.out.println(minEle(root));
            } else if (ch.equals("10")) {
                mirror(root);
                inOrder(root);
            } else if (ch.equals("11")) {
                size(root);
                System.out.println(this.size);
            } else if (ch.equals("12")) {
                Node clone = new Node(root.getVal());
                clone(root, clone);
                System.out.println("Before cloning: ");
                inOrder(root);
                System.out.println("After cloning: ");
                inOrder(clone);
            } else if (ch.equals("13")) {
                System.out.println(countLeaves(root));
            } else if (ch.equals("14")) {
                spiralLevelOrder(root);
            } else if (ch.equals("15")) {
                this.maxLevel = 0;
                leftView(root, 1);
                System.out.println();
            } else if (ch.equals("16")) {
                this.maxLevel = 0;
                rightView(root, 1);
                System.out.println();
            } else if (ch.equals("17")) {
                System.out.println("Enter n1: ");
                int n1 = in.nextInt();
                System.out.println("Enter n2: ");
                int n2 = in.nextInt();
                lca(root, n1, n2);
            } else if (ch.equals("18")) {
                Node root1 = new Node(24);

                Node lroot1 = new Node(8);
                lroot1.setLlink(new Node(3));
                lroot1.setRlink(new Node(5));

                Node rroot1 = new Node(16);
                rroot1.setLlink(new Node(7));
                rroot1.setRlink(new Node(9));

                root1.setLlink(lroot1);
                root1.setRlink(rroot1);
                System.out.println("Child sum property: " + childSumProp(root));
            } else if (ch.equals("19")) {
                convertChildSumProp(root);
                inOrder(root);
            } else if (ch.equals("20")) {
                System.out.println("Enter the sum: ");
                System.out.println("Root to Leaf with the given sum exists: " + rootToLeafSum(root, in.nextInt()));
            } else if (ch.equals("21")) {
                printBoundary(root);
            } else if (ch.equals("22")) {
                System.out.println("Enter the 2nd tree: ");
                createTree2();
                System.out.println("Inorder of tree2: ");
                inOrder(root2);
                System.out.println();
                root2 = superImpose(root, root2, true);
                System.out.println("Inorder of tree1 after superimposition: ");
                inOrder(root2);
            } else if (ch.equals("Q")) {
                System.exit(0);
            }
        }
    }
    public static void main(String args[]) {

        Tree t = new Tree();
        t.create();
        t.printMenu();
    }
}
