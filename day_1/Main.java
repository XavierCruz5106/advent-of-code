import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    try {
      File inputFile = new File("input.txt");
      Scanner scanner = new Scanner(inputFile);

      ArrayList<Integer> leftList = new ArrayList<>();
      ArrayList<Integer> rightList = new ArrayList<>();


      while (scanner.hasNextLine()) {
          String line = scanner.nextLine().trim();

          if (line.isEmpty()) continue;
          String[] numbers = line.split("\\s+");

          if (numbers.length == 2) {
              leftList.add(Integer.parseInt(numbers[0]));
              rightList.add(Integer.parseInt(numbers[1]));
          }
      }

      int[] leftArray = leftList.stream().mapToInt(Integer::intValue).toArray();
      int[] rightArray = rightList.stream().mapToInt(Integer::intValue).toArray();
      distances(leftArray, rightArray);
      similarityScore(leftArray, rightArray);




      scanner.close();

  } catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
  }

  }

  public static void distances(int[] leftArray, int[] rightArray){
    int[] sortedLeftList = Arrays.copyOf(leftArray, leftArray.length);
    int[] sortedRightList = Arrays.copyOf(rightArray, rightArray.length);
    Arrays.sort(sortedLeftList);
    Arrays.sort(sortedRightList);

    int totalDistance = 0;
    for (int i = 0; i < sortedLeftList.length; i++) {
        totalDistance += Math.abs(sortedLeftList[i] - sortedRightList[i]);
    }

    System.out.println("Total Distance: " + totalDistance);
  }

  public static void similarityScore(int[] leftArray, int[] rightArray){
    Map<Integer, Integer> numberFromIndex = new HashMap<>();


    int occurances = 0;
    for (int i = 0; i < rightArray.length; i++){

      if (numberFromIndex.get(rightArray[i]) == null){
        occurances++;
      } else {
        occurances = numberFromIndex.get(rightArray[i]) + 1;
      }
      numberFromIndex.put(rightArray[i], occurances);
      occurances = 0;
    }

    int similarityScore = 0;
    for (int i = 0; i < leftArray.length; i++){
      if (numberFromIndex.get(leftArray[i]) != null) {
        similarityScore += leftArray[i] * numberFromIndex.get(leftArray[i]);
      }
    }

    System.out.println(similarityScore);
  }
}
