public class ClassInfo implements Comparable {
    private String className;
    private long lastModified; //date from 01.01.1970 in ms

    public ClassInfo(String name, long date) {
        className = name;
        lastModified = date;
    }

    public String getClassName() {
        return className;
    }

    public long getLastModified() {
        return lastModified;
    }

    /**
     * Comparing by last modified date, if equlas - by className
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof ClassInfo) {
            ClassInfo ci = ((ClassInfo)o);
            int cmp = Long.compare(this.lastModified, ci.lastModified);
            if (cmp == 0) {
                cmp = -this.className.compareTo(ci.className);
            }
            return cmp;
        }
        return 0;
    }
}
