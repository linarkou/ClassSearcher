import java.util.Iterator;
import java.util.NavigableSet;

public class ISearcherImpl implements ISearcher {
    private static int k = 12;
    private ClassnameTree allClasses = new ClassnameTree(k);
    /**
     * Обновляет внутренние структуры данных для последующего быстрого поиска
     *
     * @param classNames        названия классов в проекте
     * @param modificationDates дата модификации класса в формате мс, прошедших с 1 января 1970 года
     */
    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        for (int i = 0; i < classNames.length; ++i) {
            ClassInfo toAdd = new ClassInfo(classNames[i], modificationDates[i]);
            allClasses.add(toAdd);
        }
    }

    /**
     * Ищет подходящие имена классов
     * Название должно начинаться с start
     *
     * @param start начало имени класса
     * @return массив длины от 0 до 12, имена классов, упорядоченный по
     * дате модификации и лексиграфически.
     */
    @Override
    public String[] guess(String start) {
        NavigableSet<ClassInfo> foundClasses = allClasses.getLastModifiedClassesByPrefix(start);
        String[] resultedArray = new String[foundClasses.size()];
        int i = 0;
        Iterator<ClassInfo> classInfoIterator = foundClasses.descendingIterator();
        while (classInfoIterator.hasNext()) {
            resultedArray[i++] = classInfoIterator.next().getClassName();
        }
        return resultedArray;
    }
}
