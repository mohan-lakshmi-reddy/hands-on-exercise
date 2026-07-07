package Algorithms_Data_Structures.FinancialForecasting;

import java.util.Scanner;

public class FinancialForecasting {

    public static double predictFutureValue(double currentValue, double growthRate, int years) {

        if (years == 0) {
            return currentValue;
        }

        return predictFutureValue(
                currentValue * (1 + growthRate),
                growthRate,
                years - 1
        );
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Current Value: ");
        double currentValue = sc.nextDouble();

        System.out.print("Enter Growth Rate (%): ");
        double growthRate = sc.nextDouble() / 100;

        System.out.print("Enter Number of Years: ");
        int years = sc.nextInt();


        double futureValue =
                predictFutureValue(currentValue, growthRate, years);


        System.out.println("Current Value: " + currentValue);

        System.out.println("Growth Rate: " + (growthRate * 100) + "%");

        System.out.println("Years: " + years);

        System.out.printf("Predicted Future Value: %.2f%n", futureValue);


        System.out.println("\nAnalysis:");

        System.out.println("Time Complexity : O(n)");

        System.out.println("Space Complexity: O(n)");
  
        System.out.println("Optimization:");
        System.out.println("The recursive algorithm has O(n) time complexity and O(n) space complexity due to recursive function calls. It can be optimized by using an iterative approach, which eliminates recursion overhead and reduces space complexity to O(1)");
    }
}
