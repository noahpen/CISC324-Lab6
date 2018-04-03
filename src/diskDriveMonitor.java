public class diskDriveMonitor {

    private int readCount;

    public diskDriveMonitor() {
        readCount = 0;
    }

    public synchronized void startRead() {
        while(readCount > 0) {
            try {
                wait();
            } catch (Exception e) {} // wait for notify()
        }
        readCount++;
    }

    public synchronized void endRead() {
        readCount--;
        notifyAll();
    }
}
