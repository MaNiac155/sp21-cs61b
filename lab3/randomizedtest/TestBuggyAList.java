package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> alist = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        alist.addLast(4);
        buggyAList.addLast(4);
        alist.addLast(5);
        buggyAList.addLast(5);
        alist.addLast(6);
        buggyAList.addLast(6);

        int m = alist.removeLast();
        int n = buggyAList.removeLast();
        assertEquals(m, n);

        m = alist.removeLast();
        n = buggyAList.removeLast();
        assertEquals(m, n);

        m = alist.removeLast();
        n = buggyAList.removeLast();
        assertEquals(m, n);
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> bL=new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bsize=bL.size();
                assertEquals(size,bsize);
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if (L.size() == 0) continue;
                //getLast
                int last = L.getLast();
                int blast=bL.getLast();
                assertEquals(last,blast);
                System.out.println("getLast(" +last + ")");
            } else if (operationNumber == 3) {
                if (L.size() == 0) continue;
                //removeLast
                int last=L.removeLast();
                int blast=bL.removeLast();
                assertEquals(last,blast);
                System.out.println("removeLast(" + last + ")");
            }

        }
    }


    @Test
    public void test(){
        BuggyAList<Integer> bL=new BuggyAList<>();
        for(int i=1;i<20;i++){
            bL.addLast(i);
        }
        for(int i=1;i<20;i++){
            bL.removeLast();
        }
    }

}

