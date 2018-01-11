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
     * Comparing by last modified date.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof ClassInfo) {
            return Long.compare(this.lastModified, ((ClassInfo)o).lastModified);
        }
        return 0;
    }
}
