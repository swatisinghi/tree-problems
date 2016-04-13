import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TreeProblems {

    private Scanner in;
    private int[] elements;
    private int size;
    private Node root;
    private int maxLevel;

    public TreeProblems(int size) {
        this.in = new Scanner(System.in);
        this.size = size;
        elements = new int[size];
    }

    private void insert(Node node, int element) {

        if(element <= node.getVal()) {
            if(node.getLlink() != null)
                insert(node.getLlink(), element);
            else
                node.setLlink(new Node(element));
        } else {
            if(node.getRlink() != null)
                insert(node.getRlink(), element);
            else
                node.setRlink(new Node(element));
        }
    }

    private void inorder(Node root) {

        if(root == null)
            return;

        inorder(root.getLlink());
        System.out.println(root.getVal());
        inorder(root.getRlink());
    }

    private int height(Node root) {

        if(root == null)
            return 0;
        if(root.getLlink() == null && root.getRlink() == null)
            return 1;
        return Math.max(height(root.getLlink()), height(root.getRlink())) + 1;
    }

    private void levelOrderTraversal(Node root, int level) {
        if(root == null)
            return;

        if(level == 0)
            System.out.print(root.getVal());
        else {
            levelOrderTraversal(root.getLlink(), level - 1);
            levelOrderTraversal(root.getRlink(), level - 1);
        }
    }

    private void levelOrder(Node root) {
        int height = height(root);
        for(int i = 0; i < height; i++) {
            levelOrderTraversal(root, i);
        }

    }

    private void create() {

        System.out.println("Enter the numbers");
        for(int i = 0; i < size; i++) {
            elements[i] = in.nextInt();
        }

        root = new Node(elements[0]);
        System.out.println("Creating the tree");
        for(int i = 1; i < size; i++) {
            insert(root, elements[i]);
        }

    }

    private void mimElement(Node root) {
        while(root.getLlink() != null) {
            root = root.getLlink();
        }
        System.out.println(root.getVal());
    }

    private void printPaths(Node root, int pathLen, int[] paths) {
        if(root == null) {
            return;
        }

        paths[pathLen] = root.getVal();
        if(root.getLlink() == null && root.getRlink() == null)
            printPath(paths, pathLen);
        printPaths(root.getLlink(), pathLen + 1, paths);
        printPaths(root.getRlink(), pathLen + 1, paths);

    }

    private void printPath(int[] path, int pathLen) {
        for(int i = 0; i <= pathLen; i++)
            System.out.print(path[i] + "->");
        System.out.println();
    }

    private int size(Node root) {
        if(root == null)
            return 0;

        return (size(root.getLlink()) + 1 + size(root.getRlink()));
    }

    private void mirror(Node root) {
        if(root == null)
            return;

        mirror(root.getLlink());
        mirror(root.getRlink());
        Node tempNode = root.getLlink();
        root.setLlink(root.getRlink());
        root.setRlink(tempNode);

    }

    private void spiralLevelOrderTraversal(Node root, int i, boolean dir) {

        if(root == null)
            return;

        if(i == 0) {
            System.out.print(root.getVal());
        } else {
            if(dir) {
                spiralLevelOrderTraversal(root.getLlink(), i - 1, dir);
                spiralLevelOrderTraversal(root.getRlink(), i - 1, dir);
            } else {
                spiralLevelOrderTraversal(root.getRlink(), i - 1, dir);
                spiralLevelOrderTraversal(root.getLlink(), i - 1, dir);
            }
        }
    }

    private void spiralLevelOrder(Node root) {
        int height = height(root);
        boolean dir = true;
        for(int i = 0; i < height; i++) {
            spiralLevelOrderTraversal(root, i, dir);
            dir = !dir;
        }
    }

    private int lca(Node root, int n1, int n2) {
        if(root == null)
            return -1;

        if(root.getVal() > n1 && root.getVal() > n2)
            return lca(root.getLlink(), n1, n2);

        if(root.getVal() < n1 && root.getVal() < n2)
            return lca(root.getRlink(), n1, n2);

        return root.getVal();
    }

    private int countLeaves(Node root) {

        if(root == null)
            return 0;
        if(root.getLlink() == null && root.getRlink() == null)
            return 1;
        return countLeaves(root.getLlink()) + countLeaves(root.getRlink());
    }

    private boolean childSumProperty(Node root) {
        int leftData = 0, rightData = 0;
        if(root == null || (root.getLlink() == null && root.getRlink() == null))
            return true;

        if(root.getLlink() != null)
            leftData = root.getLlink().getVal();
        if(root.getRlink() != null)
            rightData = root.getRlink().getVal();

        return root.getVal() == leftData + rightData && childSumProperty(root.getLlink()) && childSumProperty(root.getRlink());
    }

    private void convertTreeToChildSum(Node root) {

        int leftData = 0, rightData = 0;

        if(root == null || (root.getLlink() == null && root.getRlink() == null))
            return;

        convertTreeToChildSum(root.getLlink());
        convertTreeToChildSum(root.getRlink());

        if(root.getLlink() != null)
            leftData = root.getLlink().getVal();

        if(root.getRlink() != null)
            rightData = root.getRlink().getVal();

        int diff = root.getVal() - (leftData + rightData);

        if(diff < 0)
            root.setVal(root.getVal() - diff);
        else
            incrementValues(root, diff);
    }

    private void incrementValues(Node root, int diff) {
        if(root.getLlink() != null) {
            root.getLlink().setVal(root.getLlink().getVal() + diff);
            incrementValues(root.getLlink(), diff);
        } else if(root.getRlink() != null) {
            root.getRlink().setVal(root.getRlink().getVal() + diff);
            incrementValues(root.getRlink(), diff);
        }
    }

    private void levelOrderUsingQueue(Queue<Node> queue) {

        if(queue.size() == 0)
            return;

        Node root = queue.remove();

        if(root.getLlink() != null) {
            System.out.print(root.getLlink().getVal() + "->");
            queue.add(root.getLlink());
        }
        if(root.getRlink() != null) {
            System.out.print(root.getRlink().getVal() + "->");
            queue.add(root.getRlink());
        }

        levelOrderUsingQueue(queue);
        levelOrderUsingQueue(queue);

    }

    private void leftView(Node root, int level) {

        if(root == null) {
            return;
        }

        if(level > this.maxLevel) {
            System.out.println(root.getVal());
            this.maxLevel = level;
        }

        leftView(root.getLlink(), level + 1);
        leftView(root.getRlink(), level + 1);

    }

    private void rightView(Node root, int level) {
        if(root == null)
            return;

        if(this.maxLevel < level) {
            System.out.println(root.getVal());
            this.maxLevel = level;
        }

        rightView(root.getRlink(), level + 1);
        rightView(root.getLlink(), level + 1);
    }

    private void clone(Node node, Node cloneNode) {
        if (node.getLlink() != null) {
            cloneNode.setLlink(node.getLlink());
            clone(node.getLlink(), cloneNode.getLlink());
        }

        if (node.getRlink() != null) {
            cloneNode.setRlink(node.getRlink());
            clone(node.getRlink(), cloneNode.getRlink());
        }
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



    public void menu() {

        System.out.println("Menu");
        System.out.println("1. Inorder");
        System.out.println("2. Height");
        System.out.println("3. Level Order");
        System.out.println("4. Minimum element");
        System.out.println("5. Print paths");
        System.out.println("6. Size");
        System.out.println("7. Mirror");
        System.out.println("8. Least common Ancestor(LCA)");
        System.out.println("9. Count leaves");
        System.out.println("10. Spiral level order");
        System.out.println("11. Children sum property");
        System.out.println("12. Convert tree to children sum property");
        System.out.println("13. Level order traversal using queue");
        System.out.println("14. Left View of the tree");
        System.out.println("15. Right View of the tree");
        System.out.println("16. Clone a tree");
        System.out.println("17. Is BST");
        System.out.println("Q. Quit");

        String input = in.nextLine();

        if(input.equals("1"))
            inorder(root);
        else if(input.equals("2"))
            System.out.println(height(root));
        else if(input.equals("3"))
            levelOrder(root);
        else if(input.equals("4"))
            mimElement(root);
        else if(input.equals("5")) {
            int height = height(root);
            int[] paths = new int[height];
            printPaths(root, 0, paths);
        } else if(input.equals("6"))
            System.out.println(size(root));
        else if(input.equals("7")) {
            mirror(root);
            inorder(root);
        } else if(input.equals("8")) {
            System.out.println("Enter the numbers: ");
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            System.out.println(lca(root, n1, n2));
        } else if(input.equals("9"))
            System.out.println(countLeaves(root));
        else if(input.equals("10"))
            spiralLevelOrder(root);
        else if(input.equals("11"))
            System.out.println(childSumProperty(root));
        else if(input.equals("12"))
            convertTreeToChildSum(root);
        else if(input.equals("13")) {
            System.out.print(root.getVal() + "->");
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            levelOrderUsingQueue(queue);
        } else if(input.equals("14")) {
            this.maxLevel = 0;
            leftView(root, 1);
        } else if(input.equals("15")) {
            this.maxLevel = 0;
            rightView(root, 1);
        } else if(input.equals("16")) {
            Node cloneRoot = new Node(root.getVal());
            clone(root, cloneRoot);
            System.out.println("In order traversal of original tree");
            inorder(root);
            System.out.println("In order traversal of cloned tree");
            inorder(cloneRoot);
        } else if (input.equals("7")) {
            boolean isBst = isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (isBst) {
                System.out.println("Yes, it is a BST");
            } else {
                System.out.println("No, it is not a BST");
            }
        } else if(input.equals("Q"))
            System.exit(0);

        menu();
    }

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size");
        int size = in.nextInt();

        TreeProblems treeProblems = new TreeProblems(size);
        treeProblems.create();
        treeProblems.menu();

    }
}
