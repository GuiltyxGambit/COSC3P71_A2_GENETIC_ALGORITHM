package GeneticAlgorithm;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is the main function for the GeneticAlgorithm package.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        /*
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input an integer value population size: ");
        int populationSize = Integer.parseInt(scanner.nextLine());
        System.out.println();
        System.out.print("Please input an integer value for number of generations: ");
        int maxGen = Integer.parseInt(scanner.nextLine());
        System.out.println();
        System.out.print("Please input a decimal value less than one for mutation probability: ");
        double mutationRate = Double.parseDouble(scanner.nextLine());
        System.out.println();
        System.out.print("Please input a decimal value less than one for crossover probability: ");
        double crossoverRate = Double.parseDouble(scanner.nextLine());
        System.out.println();
        System.out.print("Please input either 0 for Uniform Crossover or 1 for Two-Point Crossover: ");
        int crossoverOperator = Integer.parseInt(scanner.nextLine());
        System.out.println();
        System.out.print("Please input either 0 for Data1.txt or 1 for Data2.txt for : ");
        int fileChoice = Integer.parseInt(scanner.nextLine());
        String filePath;
        if (fileChoice == 0) {
            filePath = "src/GeneticAlgorithm/Data1.txt";
        } else {
            filePath = "src/GeneticAlgorithm/Data2.txt";
        }

        GA geneticAlgorithm1 = new GA(populationSize, maxGen, mutationRate, crossoverRate, crossoverOperator, filePath);

        */

        // Case 1:
        GA geneticAlgorithm1 = new GA(1000, 50, 0.2, 1.0, 1,
                    "src/GeneticAlgorithm/Data1.txt");

    }
}