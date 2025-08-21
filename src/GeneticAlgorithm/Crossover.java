package GeneticAlgorithm;

public class Crossover {

    private double crossoverProbability;
    private double mutationProbability;
    private static int length;
    private int operation;

    /**
     *
     * @param chromosomeLength
     * @param crossoverRate
     * @param mutationRate
     */
    public Crossover(int type, int chromosomeLength, double crossoverRate, double mutationRate) {
        length = chromosomeLength;
        crossoverProbability = crossoverRate * 100;
        mutationProbability = mutationRate * 100;
        operation = type;
    } // Constructor

    /**
     *
     * @param chromosomeOne
     * @param chromosomeTwo
     * @return
     */
    public String[] crossover(String chromosomeOne, String chromosomeTwo) {
        String [] children;

        if(operation == 0) {
            children = crossoverUX(chromosomeOne, chromosomeTwo);
        } else { // operation == 1
            children = twoPointCrossover(chromosomeOne, chromosomeTwo);
        }
        return children;
    }

    /**
     * This method performs a Uniform Crossover on the current generation genome within this GA class.
     *
     */
    private String[] crossoverUX(String chromosomeOne, String chromosomeTwo) {

        char [] parentOne = chromosomeOne.toCharArray();
        char [] parentTwo = chromosomeTwo.toCharArray();


        char [] childOne = new char[length];
        char [] childTwo = new char[length];
        String [] children = new String[2];

        for(int i = 0; i < length; i++) {
            int randomint = (int) (Math.random()*2); // flip a coin
            if(randomint == 0) {
                childOne[i] = parentOne[i];
                childTwo[i] = parentTwo[i];
            } else {
                childOne[i] = parentTwo[i];
                childTwo[i] = parentOne[i];
            }
        }

        children[0] = String.copyValueOf(mutate(childOne));
        children[1] = String.copyValueOf(mutate(childTwo));

        return children;
    }

    /**
     *
     * @param chromosomeOne
     * @param chromosomeTwo
     * @return
     */
    private String[] twoPointCrossover(String chromosomeOne, String chromosomeTwo) {
        String [] children = new String[2];
        char [] parentOne = chromosomeOne.toCharArray();
        char [] parentTwo = chromosomeTwo.toCharArray();
        char [] childOne = new char[length];
        char [] childTwo = new char[length];

        // Generate two points for crossover:
        int p1 = (int) (Math.random()*length);
        int p2 = (int) (Math.random()*length);
        int min;
        int max;

        if(p1 < p2) {
            min = p1;
            max = p2;
        } else {
            min = p2;
            max = p1;
        }

        for (int i = 0; i < length; i++) {
            if (min < i && i < max) {
                childOne[i] = parentTwo[i];
                childTwo[i] = parentOne[i];
            } else {
                childOne[i] = parentOne[i];
                childTwo[i] = parentTwo[i];
            }
        }

        children[0] = String.copyValueOf(mutate(childOne));
        children[1] = String.copyValueOf(mutate(childTwo));
        return children;
    }

    /**
     *
     * @param sequence
     * @return
     */
    private char[] mutate(char[] sequence) {

        if ((int) (Math.random() * 100 + 1) <= mutationProbability) {
            int mutationSelect = (int) (Math.random() * 2);
            if (mutationSelect == 0) { // Reciprocal Exchange:
                // Generate two points for cut of original chromosome
                char[] mutation = new char[length];
                for(int i = 0; i < length; i++) {
                    mutation[i] = sequence[i];
                }
                int point1 = (int) (Math.random()*length);
                int point2 = (int) (Math.random()*length);

                char temp = mutation[point1];
                mutation[point1] = mutation[point2];
                mutation[point2] = temp;
                return mutation;
            } else {
                // Custom Mutation Method: Select random allele in chromosome and change to a letter
                sequence[(int) (Math.random() * (sequence.length))] = (char) (Math.random() * 26 + 97);
                return sequence;
            }
        }
        return sequence;
    }

}
