// CPU Monitor

public class cpuMonitor { // simulates one CPU

    private boolean inUse;

    public cpuMonitor() {
        inUse = false; // CPU initialized as not in use
    }

    public synchronized void startCPUuse() {
        while (inUse == true) {
            try {  wait(); } catch (Exception e) {};  // wait for "notify()"
        }
        inUse = true;
    }

    public synchronized void endCPUuse() {
        inUse = false;
        notifyAll();
    }

}
