package aocutils;

public class Benchmarker {
    private long beforeUsedMem;
    private long startTime;

    public Benchmarker() {
        this.beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        this.startTime = System.currentTimeMillis();
    }

    public void details() {
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long endTime = System.currentTimeMillis();
        long memUsedMb = (afterUsedMem - this.beforeUsedMem) / (1000 * 1000);
        long timeTakenMs = endTime - this.startTime;
        System.out.println("Mem used: " + memUsedMb + "mb , Time taken: " + timeTakenMs + "ms");
    }
}
