import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    @Test
    public void hardTest() {
        int n = 100000;
        long[] dates = new long[n];
        String[] classNames = new String[n];
        Random rand = new Random();
        for (int i = 0; i < n; ++i) {
            dates[i] = i+1;
            classNames[i] = new String(new char[25]).replace("\0", "A");
            for (int j = 0; j < 7; ++j)
                classNames[i] += (char)('A'+rand.nextInt(26));

        }
        ISearcher searcher = new ISearcherImpl();
        long start = System.currentTimeMillis();
        searcher.refresh(classNames, dates);
        long refreshTime = System.currentTimeMillis() - start;
        System.out.println("r="+refreshTime);
        start = System.currentTimeMillis();
        String toFind = new String(new char[25]).replace("\0", "A");
        String[] res1 = searcher.guess(toFind);
        String[] res2 = searcher.guess(toFind+"G");
        String[] res3 = searcher.guess(toFind+"N");
        long searchTime = (System.currentTimeMillis() - start ) / 3;
        System.out.println(searchTime);
        System.out.println(Arrays.toString(res1));
        System.out.println(Arrays.toString(res2));
        System.out.println(Arrays.toString(res3));
    }

}
