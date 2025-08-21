package GeneticAlgorithm;

import java.io.*;

/**
 * This class is a genetic algorithm used for determining the password used for encryption.
 */
public class GA {
    private String text;
    private static int chromosomeLength;
    private final Crossover CX;
    private final int population; // Population of 10,000

    /**
     *
     * @param initialPopulation
     * @param maxGeneration
     * @param mutationRate
     * @param crossoverRate
     * @param crossoverOperation
     * @throws IOException
     */
    public GA (int initialPopulation, int maxGeneration, double mutationRate, double crossoverRate,
               int crossoverOperation, String filePath) throws IOException {
        // Initialize local variables
        population = initialPopulation;

        File fileApproved = new File(filePath); // dockedApproved.tab file
        FileReader fileReader = new FileReader(fileApproved); // FileReader for BufferedReader
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String keyLength = bufferedReader.readLine();
        chromosomeLength = Integer.parseInt(keyLength); // Parses the string representation to integer value

        CX = new Crossover(crossoverOperation, chromosomeLength, crossoverRate, mutationRate);

        // Code block processes input file containing key length and encrypted text.
        // Encrypted text is stored as private variable 'text'.
        text = "";
        while(true) {
            String str = bufferedReader.readLine();
            if (str == null) {
                break;
            }
            text = text + str;
        }
        bufferedReader.close();

        Genome genome = initializePopulation();
        System.out.println(genome.bestChromosome.getFitness()+"\t"+genome.averageFitness);
        for (int i = 1; i < maxGeneration; i++) { // starting at one generation, the initial generation
            genome = evolution(genome);
            System.out.println(genome.bestChromosome.getFitness()+"\t"+genome.averageFitness);
        }
        //System.out.println(genome.bestChromosome.getFitness());

    } // Constructor

    /**
     *
     * @return
     * @throws IOException
     */
    private Genome initializePopulation() throws IOException {
        Chromosome [] chromosomes = new Chromosome[population];
        // This loop iterates through each member of the population
        for (int i = 0; i < population; i++) {
            char [] geneticSequence;
            geneticSequence = new char[chromosomeLength];
            // Loop constructs a chromosome
            for (int j = 0; j < chromosomeLength; j++) {
                char c = (char) (Math.random()*26 + 97);
                geneticSequence[j] = c;
            };
            String sequence = String.copyValueOf(geneticSequence);
            double fitness = Evaluation.fitness(sequence,text);

            Chromosome chr = new Chromosome(sequence,fitness);
            chromosomes[i] = chr;
        };
        return new Genome(chromosomes);
    };

    /**
     * This method creates the next generation genome
     */
    private Genome evolution(Genome genome) {
        Genome evolved;
        Chromosome[] newGenome = new Chromosome[population];
        // Passes the most elite members of the current generation to the next generation based on 'elite' metric
        for(int i = 0; i < 3; i++) {
            newGenome[i] = genome.geneticSequence[i];
        };

        String [] children;

        for(int j = 2; j < population; j+=2) { // Increment by two

            Chromosome parentOne = tournamentSelect(genome);
            Chromosome parentTwo = tournamentSelect(genome);

            children = CX.crossover(parentOne.getChromosome(), parentTwo.getChromosome());
            Chromosome childOne = new Chromosome(children[0], Evaluation.fitness(children[0],text));
            Chromosome childTwo = new Chromosome(children[1], Evaluation.fitness(children[1],text));

            newGenome[j] = childOne;
            newGenome[j+1] = childTwo;
        }

        evolved = new Genome(newGenome);
        return evolved;
    }

    /**
     *
     * @param genome
     * @return
     */
    private Chromosome tournamentSelect(Genome genome) {
        // int k = (int) (Math.random()*4 + 2); // Generates a number between 2 and 5
        int k = 2;
        Chromosome winner = genome.geneticSequence[(int) (Math.random() * (population))];
        for(int i = 0; i < (k-1); i++) {
            int num = (int) (Math.random() * (population));
            if(genome.geneticSequence[num].getFitness() < winner.getFitness()) {
                winner = genome.geneticSequence[num];
            }
        }
        return winner;
    }

}
