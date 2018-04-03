/*
 * Noah Peneycad
 * CISC324 Lab #6
 * April 3, 2018
 * CPUmonitor.java: Monitor that simulates N CPUs that are used in parallel by K user jobs, with mutual
 * exclusion for each CPU.
 */

public class CPUmonitor { // simulates one CPU

    private int N; // number of CPUs
    private int lastUsedCPU; // CPU last used by a UserJob
    private int numInUse; // number of CPUs in use
    private boolean[] inUse; // array of if each CPU is in use (true) or not (false)

    public CPUmonitor(int numCPUs) {
        N = numCPUs;
        numInUse = 0; // 0 CPUs in use initially
        inUse = setInUse(N);
    }

    // sets all elements of inUse array to false
    public boolean[] setInUse(int N) {
        boolean[] array = new boolean[N];
        for (int i = 0; i < N; i++)
            array[i] = false; // all CPUs are initially not in use
        return array;
    }

    // gets last used CPU
    public int getLastUsedCPU() {
        return lastUsedCPU;
    }

    public synchronized void startCPUuse(String name, int CPUtime) {
        // all CPUs are in use
        while (numInUse >= N) {
            try {  wait(); } catch (Exception e) {};  // wait for "notify()"
        }

        // check if any CPUs are free
        for (int i = 0; i < N; i++) {
            if (inUse[i] == false) { // CPU i is free
                inUse[i] = true; // set CPU i to in-use
                lastUsedCPU = i; // CPU i is the last used CPU
                numInUse++; // another CPU is in use now
                break;
            }
        }

        System.out.println(name + " starting to use CPU " + lastUsedCPU + " on a burst of " + CPUtime + "ms");
    }

    public synchronized void endCPUuse(String name, int lastUsedCPU) {
        inUse[lastUsedCPU] = false; // this CPU is now free
        numInUse--;
        System.out.println(name + " is finished using CPU " + lastUsedCPU);
        notify();
    }

}
