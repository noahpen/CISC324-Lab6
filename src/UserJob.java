public class UserJob extends Thread {

    private String name;
    private int bound;
    private int CPUtime;
    private int track;
    private DiskDrive diskDrive;
    private final int DISK_TRACKS = 1024;

    // Constructor
    public UserJob(String job_name, int job_bound, DiskDrive drive) {
        name = "UserJob " + job_name;
        bound = job_bound;
        diskDrive = drive;
    }

    public void run() {
        System.out.println(name + " is starting");
        for (int i = 0; i < 5; i++) {

            CPUtime = 100 + (int)(Math.random() * ((1000 - 100) + 1));
            track = 1 + (int)(Math.random() * ((DISK_TRACKS - 1) + 1));

            // CPU Bound
            if (bound == 0) {
                // CPU Time between 100ms and 1000ms
                System.out.println(name + " starting CPU burst of " + CPUtime + "ms");
                try {
                    Thread.sleep(CPUtime);
                }
                catch (Exception e) {}
            }

            // IO Bound
            else {
                System.out.println(name + " requesting to read disk track " + track);
                diskDrive.useTheDisk(track);
                System.out.println(name + " finished reading disk track " + track);
            }
        }
    }
}