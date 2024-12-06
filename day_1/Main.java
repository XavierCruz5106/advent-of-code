import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner lineCounter = new Scanner(file);

            // Count lines manually
            int lineCount = 0;
            while (lineCounter.hasNextLine()) {
                lineCounter.nextLine();
                lineCount++;
            }
            lineCounter.close();

            // Create arrays with exact size
            int[] leftArray = new int[lineCount];
            int[] rightArray = new int[lineCount];

            // Read file again
            Scanner scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] numbers = line.split("\\s+");
                    if (numbers.length == 2) {
                        leftArray[index] = Integer.parseInt(numbers[0]);
                        rightArray[index] = Integer.parseInt(numbers[1]);
                        index++;
                    }
                }
            }
            scanner.close();

            distances(leftArray, rightArray);
            similarityScore(leftArray, rightArray);

        } catch (FileNotFoundException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void distances(int[] leftArray, int[] rightArray) {
        // Sort arrays in-place to avoid creating copies
        Arrays.sort(leftArray);
        Arrays.sort(rightArray);

        int totalDistance = 0;
        for (int i = 0; i < leftArray.length; i++) {
            totalDistance += Math.abs(leftArray[i] - rightArray[i]);
        }

        System.out.println("Total Distance: " + totalDistance);
    }

    public static void similarityScore(int[] leftArray, int[] rightArray) {
        // Use a more efficient frequency counting approach
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count frequencies in right array
        for (int num : rightArray) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Calculate similarity score
        int similarityScore = 0;
        for (int num : leftArray) {
            if (frequencyMap.containsKey(num)) {
                similarityScore += num * frequencyMap.get(num);
            }
        }

        System.out.println("Similarity Score: " + similarityScore);
    }
}