/**
 * Data structure for getting
 */
public class ClassnameTree {
    private Node root = new Node();

    public class Node {
        KLargestArray classesArray;
        Node[] child = new Node[128];

        public void add(ClassInfo toAdd, int depth) {
            classesArray.add(toAdd);
            if (depth < toAdd.getClassName().length()) {
                int childIndex = toAdd.getClassName().charAt(depth);
                if (child[childIndex] == null)
                    child[childIndex] = new Node();
                child[childIndex].add(toAdd, depth + 1);
            }
        }

    }

    public void add(ClassInfo toAdd) {
        root.add(toAdd, 0);
    }
}
