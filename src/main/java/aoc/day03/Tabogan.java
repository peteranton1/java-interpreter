package aoc.day03;

public class Tabogan {
    private SkiMap skiMap;
    public Tabogan(String inputFile) throws Exception {
        this.skiMap = new SkiMap(inputFile);
    }

    public int run(int rightStep, int downStep) {
        skiMap.reset();
        int travelledDown = 0;
        int trees = 0;
        while(!skiMap.isEof()) {
            travelledDown += downStep;
            trees += skiMap.move(rightStep, downStep);
        }
        System.out.printf("Trees: %d, down steps: %d%n",
                trees, travelledDown);
        return trees;
    }
}
