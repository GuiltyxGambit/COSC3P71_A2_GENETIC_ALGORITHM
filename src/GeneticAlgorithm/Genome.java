package GeneticAlgorithm;

public class Genome {

    Chromosome [] geneticSequence;
    Chromosome bestChromosome;

    double averageFitness;

    Genome(Chromosome [] chromosomes) {
        geneticSequence = chromosomes;
        quickSort(0, geneticSequence.length-1);
        bestChromosome = geneticSequence[0];

        double sumFitness = 0;
        for(int i = 0; i < geneticSequence.length; i++) {
            sumFitness = sumFitness + geneticSequence[i].getFitness();
        }
        averageFitness = sumFitness/geneticSequence.length;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    private void quickSort(int low, int high) {
        if(low < high) {
            int pi = partition(low, high);
            quickSort(low, pi-1);
            quickSort(pi+1,high);
        }
    }

    private int partition(int low, int high) {
        double pivot = geneticSequence[high].getFitness();
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (geneticSequence[j].getFitness() <= pivot) {
                i++;
                // Swap
                Chromosome temp = geneticSequence[i];
                geneticSequence[i] = geneticSequence[j];
                geneticSequence[j] = temp;
            }
        }
        Chromosome temp = geneticSequence[i+1];
        geneticSequence[i+1] = geneticSequence[high];
        geneticSequence[high] = temp;
        return i+1;
    }
}
