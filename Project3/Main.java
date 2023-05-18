import java.util.Random;
public class Main{
   public static void main(String[] args) {
      int num = args.length == 1 ? Integer.parseInt(args[0]) : 10;
      long start, stop;
      Integer[] array = new Integer[num];
      Random rand;
      
      rand = new Random(1);
      for (int i = 0; i < array.length; ++i) {
         array[i] = rand.nextInt(num);
      }
      
      start = System.currentTimeMillis();
      Sorts.heapSort(array); //sorting algo currently used
      stop = System.currentTimeMillis();
      for (int i = 0; i < array.length - 1; ++i) {
         if (array[i] > array[i+1]) {
            System.out.println("heap fail");
            System.exit(1);
         }
      }
      System.out.println("time = " + (stop - start));
   }
}
