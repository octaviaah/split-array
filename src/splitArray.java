import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class splitArray {
    static ArrayList<Integer> findElemsForAvg(int[] array,
                               int size, int sum, int shouldHave)
    {
        if (sum == 0 && shouldHave == 0)
            return new ArrayList<>();
        if (size == 0)
            return null;

        if (array[size-1] > sum)
            return findElemsForAvg(array, size - 1, sum, shouldHave);


        ArrayList<Integer> exclude = findElemsForAvg(array, size - 1, sum, shouldHave);
        if (exclude == null){
            ArrayList<Integer> include = findElemsForAvg(array, size-1, sum-array[size-1], shouldHave - 1);
            if (include == null)
                return null;
            else {
                include.add(array[size-1]);
                return include;
            }
        }
        else
            return exclude;
    }

    static int sumArray(int[] array, int size){
        int sum = 0;
        for (int i = 0; i < size; i++)
            sum += array[i];
        return sum;
    }

    static int sumList(ArrayList<Integer> list, int size) {
        int sum = 0;
        for (Integer element : list)
            sum += element;
        return sum;
    }

    public static void main(String[] args){
        int[] aArray = new int[30];
        int sizeA = 0;
        boolean ok = true;
        System.out.println("Enter the elements of the array A:");
        while (ok){
            Scanner sc = new Scanner(System.in);
            Integer element = sc.nextInt();
            if (element != 0){
                aArray[sizeA] = element;
                sizeA++;
            }
            else ok = false;
        }
        int sumA = sumArray(aArray, sizeA);

        ArrayList<Integer> bList = new ArrayList<>();
        ArrayList<Integer> cList = new ArrayList<>();
        ok = false;
        for (int i = 1; i <= sizeA; i++) {
            cList.clear();
            int tempSum = (i * sumA) / sizeA;
            bList = findElemsForAvg(aArray, sizeA, tempSum, i);
            if (bList != null) {
                for (int j = 0; j < sizeA; j++) {
                    if (!bList.contains(aArray[j]))
                        cList.add(aArray[j]);
                }
                int sumB = sumList(bList, bList.size());
                int sumC = sumList(cList, cList.size());
                float avgB = (float) sumB / i;
                float avgC = (float) sumC / (sizeA - i);
                try {
                    if (avgB == avgC) {
                        ok = true;
                        break;
                    }
                }
                catch (Exception e){}
            }
        }
        System.out.println(ok);
    }
}
