class AVLNode {
    int rollNo, height;
    double cgpa;
    String name;
    AVLNode left, right;

    AVLNode(int rollNo, String name, double cgpa) {
        this.rollNo = rollNo;
        this.name = name;
        this.cgpa = cgpa;
        height = 1;
    }
}

public class EduGraphAVL {
    AVLNode root;

    int height(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(AVLNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, int rollNo, String name, double cgpa) {
        if (node == null)
            return new AVLNode(rollNo, name, cgpa);

        if (rollNo < node.rollNo)
            node.left = insert(node.left, rollNo, name, cgpa);
        else if (rollNo > node.rollNo)
            node.right = insert(node.right, rollNo, name, cgpa);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && rollNo < node.left.rollNo)
            return rightRotate(node);

        // RR
        if (balance < -1 && rollNo > node.right.rollNo)
            return leftRotate(node);

        // LR
        if (balance > 1 && rollNo > node.left.rollNo) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && rollNo < node.right.rollNo) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.rollNo + " " + node.name + " CGPA:" + node.cgpa);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        EduGraphAVL tree = new EduGraphAVL();

        tree.root = tree.insert(tree.root, 1021, "Arjun", 8.4);
        tree.root = tree.insert(tree.root, 1005, "Priya", 9.1);
        tree.root = tree.insert(tree.root, 1034, "Kiran", 7.5);
        tree.root = tree.insert(tree.root, 1012, "Divya", 9.4);
        tree.root = tree.insert(tree.root, 1045, "Rahul", 7.2);

        System.out.println("Inorder Traversal:");
        tree.inorder(tree.root);
    }
}