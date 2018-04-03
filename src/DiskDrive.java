// Disk Drive Monitor

public class DiskDrive {

    private int diskReadHead;
    private int accessTime;

    public DiskDrive() {
        diskReadHead = 0;
    }

    public synchronized void useTheDisk(int track) {
        if (track > diskReadHead)
            accessTime = 1 + (track - diskReadHead);
        else if (track < diskReadHead)
            accessTime = 1 + (diskReadHead - track);
        else
            accessTime = 1;
        System.out.println("Disk head is moving from track " + diskReadHead + " to track " + track + "; this disk access takes " + accessTime + "ms");
        try {
            Thread.sleep(accessTime);
        }
        catch (Exception e) {}
        diskReadHead = track;
    }

}
