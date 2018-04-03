// CPU Monitor

public class cpuMonitor { // simulates one CPU

    //private boolean inUse;
    private int N; // number of CPUs
    private int numInUse; // number of CPUs in use

    public cpuMonitor(int numCPUs) {
        N = numCPUs;
        numInUse = 0; // 0 CPUs in use initially
    }

    public synchronized void startCPUuse() {
        while (numInUse >= N) {
            try {  wait(); } catch (Exception e) {};  // wait for "notify()"
            System.out.println("All CPUs in use, waiting for a CPU to finish..."); // for debugging
        }
        numInUse++;
    }

    public synchronized void endCPUuse() {
        numInUse--;
        notifyAll();
    }

}
