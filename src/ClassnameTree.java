import java.util.TreeSet;

/**
 * Data structure for getting
 */
public class ClassnameTree {
    private Node root;
    private int k;

    public ClassnameTree(int k) {
        this.k = k;
        root = new Node();
    }

    public class Node {
        TreeSet<ClassInfo> classesArray;
        Node[] child = new Node[128];

        public Node() {
            classesArray = new TreeSet<>();
        }

        public void add(ClassInfo toAdd, int depth) {
            if (classesArray.size() < k)
                classesArray.add(toAdd);
            else if (classesArray.first().compareTo(toAdd) < 0) {
                classesArray.pollFirst();
                classesArray.add(toAdd);
            }
            if (depth < toAdd.getClassName().length()) {
                int childIndex = toAdd.getClassName().charAt(depth);
                if (child[childIndex] == null)
                    child[childIndex] = new Node();
                child[childIndex].add(toAdd, depth + 1);
            }
        }

        public Node getChild(char c) {
            return child[c];
        }

        public TreeSet<ClassInfo> getClasses() {
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
        return nodeByPrefix == null ? new TreeSet<>() : nodeByPrefix.getClasses();
    }
}
