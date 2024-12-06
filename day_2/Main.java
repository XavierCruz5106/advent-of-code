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

      boolean isIncreasing = true;
      boolean isDecreasing = true;
      boolean isSafe = true;

      for (int i = 0; i < intArray.length - 1; i++) {
        int diff = Math.abs(intArray[i] - intArray[i + 1]);

        if (diff < 1 || diff > 3) {
          isSafe = false;
          break;
        }

        if (intArray[i] < intArray[i + 1]) {
          isDecreasing = false;
        } else if (intArray[i] > intArray[i + 1]) {
          isIncreasing = false;
        }
      }

      if (isSafe && (isIncreasing || isDecreasing)) {
        safeReports++;
      }
    }

    System.out.println(safeReports);

    input.close();
  }
}
