package aoc.day08;

public class Patcher {
    // Patch a code and try again
    public int run(String input) throws Exception {
        BootCodeParser parser = new BootCodeParser();
        BootCode bootCode = parser.parse(input);
        int result = 0;
        for (int loop = 0; loop < bootCode.getInstructions().size(); loop++) {
            bootCode.resetCounts();
            Accumulator accumulator = new Accumulator(bootCode);
            result = accumulator.patchAndAccumulate(loop);
            System.out.printf("loop[%s]: result: %s, %s%n",
                    loop, result, accumulator.spMessage());
            if (accumulator.hasRunFully()) {
                return result;
            }
        }
        return result;
    }
}
