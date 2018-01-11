import java.util.TreeSet;

/**
 * Data structure for getting
 */
public class ClassnameTree {
    private Node root;
    private int k;

    public ClassnameTree(int k) {
        this.k = k;
        root = new Node(k);
    }

    public class Node {
        KLargestArray classesArray;
        Node[] child = new Node[128];

        public Node(int k) {
            classesArray = new KLargestArray(k);
        }

        public void add(ClassInfo toAdd, int depth) {
            classesArray.add(toAdd);
            if (depth < toAdd.getClassName().length()) {
                int childIndex = toAdd.getClassName().charAt(depth);
                if (child[childIndex] == null)
                    child[childIndex] = new Node(k);
                child[childIndex].add(toAdd, depth + 1);
            }
        }

        public Node getChild(char c) {
            return child[c];
        }

        public KLargestArray getClasses() {
            return classesArray;
        }
    }

    public void add(ClassInfo toAdd) {
        root.add(toAdd, 0);
    }

    private Node getNodeByPrefix(String prefix) {
        Node currNode = root;
        for (int i = 0; i < prefix.length(); ++i) {
            currNode = currNode.getChild(prefix.charAt(i));
        }
        return currNode;
    }

    public TreeSet<ClassInfo> getLastModifiedClassesByPrefix(String prefix) {
        Node nodeByPrefix = getNodeByPrefix(prefix);
        if (nodeByPrefix == null)
            return new TreeSet<>();
        return nodeByPrefix.getClasses().getLastModifiedClasses();
    }
}
