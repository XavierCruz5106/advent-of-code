package day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner input = new Scanner(new File("input.txt"));

    int safeReports = 0;
    while (input.hasNextLine()) {
      String line = input.nextLine();

      String[] stringArray = line.split(" ");
      int[] intArray = Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();

      if (isSafe(intArray)) {
        safeReports++;
        continue;
      }

      boolean isSafeWithOneRemoval = false;
      for (int i = 0; i < intArray.length; i++) {
        int[] modifiedArray = new int[intArray.length - 1];
        // creating a new array without i
        System.arraycopy(intArray, 0, modifiedArray, 0, i);
        System.arraycopy(intArray, i + 1, modifiedArray, i, intArray.length - i - 1);

        if (isSafe(modifiedArray)) {
          isSafeWithOneRemoval = true;
          break;
        }
      }

      if (isSafeWithOneRemoval) {
        safeReports++;
      }
    }

    System.out.println(safeReports);

    input.close();
  }

  public static boolean isSafe(int[] intArray){
    boolean isIncreasing = true;
    boolean isDecreasing = true;

    for (int i = 0; i < intArray.length - 1; i++) {
      int diff = Math.abs(intArray[i] - intArray[i + 1]);

      if (diff < 1 || diff > 3) {
        return false;
      }

      if (intArray[i] < intArray[i + 1]) {
        isDecreasing = false;
      } else if (intArray[i] > intArray[i + 1]) {
        isIncreasing = false;
      }
    }

    return isIncreasing || isDecreasing;
  }
}
