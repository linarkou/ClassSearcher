import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Data structure for easy getting K largest elements
 */
public class KLargestArray {
     private int k;
     private TreeSet<ClassInfo> lastModifiedClasses = new TreeSet<>(); //by last modified date
     private ArrayList<ClassInfo> oldClasses = new ArrayList<>();

     public KLargestArray(int k) {
         if (k > 0) {
             this.k = k;
         } else
             throw new IllegalArgumentException("Argument k must be positive");
     }

     public void add(ClassInfo classToAdd) {
         if (lastModifiedClasses.size() < k) {
             lastModifiedClasses.add(classToAdd);
         } else {
             if (classToAdd.compareTo(lastModifiedClasses.first()) > 0) {
                 oldClasses.add(lastModifiedClasses.pollFirst());
                 lastModifiedClasses.add(classToAdd);
             } else {
                 oldClasses.add(classToAdd);
             }
         }
     }

     public TreeSet<ClassInfo> getLastModifiedClasses() {
         return lastModifiedClasses;
     }


}
