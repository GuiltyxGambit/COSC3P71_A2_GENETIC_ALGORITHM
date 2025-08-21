package GeneticAlgorithm;

public class Chromosome {

    private final String chromosome;
    private final double fitness;
    Chromosome(String seq, double f) {
        //System.out.println(str.toString());
        chromosome = seq;
        fitness = f;
    }

    public String getChromosome() {
        return chromosome;
    }

    public double getFitness() {
        return fitness;
    }

}
