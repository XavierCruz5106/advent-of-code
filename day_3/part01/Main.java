package day_3.part01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner input = new Scanner(new File("input.txt"));
    String line = input.nextLine();
    Pattern mulPattern = Pattern.compile("mul\\((\\d*),(\\d*)\\)");
    Matcher matcher = mulPattern.matcher(line);

    int totalSum = 0;

    while (matcher.find()) {
      int num1 = Integer.parseInt(matcher.group(1));
      int num2 = Integer.parseInt(matcher.group(2));
      totalSum += num1 * num2;
    }

    System.out.println(totalSum);
  }
}