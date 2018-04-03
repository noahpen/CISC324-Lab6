/*
 * Noah Peneycad
 * CISC324 Lab #6
 * April 3, 2018
 * DiskDrive.java: Disk drive monitor simulates a disk drive that is accessed and read on a
 * specific track.
 */

public class DiskDrive {

    private int diskReadHead; // where the head is currently at
    private int accessTime; // time it takes to access the disk

    public DiskDrive() {
        diskReadHead = 0;
    } // head initially starts at track 0

    public synchronized void useTheDisk(int track) {
        if (track > diskReadHead)
            accessTime = 1 + (track - diskReadHead);
        else if (track < diskReadHead)
            accessTime = 1 + (diskReadHead - track);
        else
            accessTime = 1;
        System.out.println("Disk head is moving from track " + diskReadHead + " to track " + track + "; this disk access takes " + accessTime + "ms");
        try {
            Thread.sleep(accessTime); // simulate disk access time
        }
        catch (Exception e) {}
        diskReadHead = track; // head moves to new track
    }

}
