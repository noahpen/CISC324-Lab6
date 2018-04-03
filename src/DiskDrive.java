public class DiskDrive {

    private int diskReadHead;
    private int accessTime;
    private diskDriveMonitor drive;

    public DiskDrive() {
        diskReadHead = 0;
        drive = new diskDriveMonitor();
    }

    public void useTheDisk(int track) {
        drive.startRead();
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
        drive.endRead();
    }

}
