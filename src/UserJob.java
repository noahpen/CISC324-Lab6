public class UserJob extends Thread {

    private String name;
    private int bound;
    private int CPUtime;
    private int track;
    private DiskDrive diskDrive;
    private cpuMonitor cpu;
    private final int DISK_TRACKS = 1024;

    // Constructor
    public UserJob(String job_name, int job_bound, DiskDrive drive, cpuMonitor cpuOne) {
        name = "UserJob " + job_name;
        bound = job_bound;
        diskDrive = drive;
        cpu = cpuOne;
    }

    public void run() {
        System.out.println(name + " is starting");
        for (int i = 0; i < 5; i++) {

            track = 1 + (int)(Math.random() * ((DISK_TRACKS - 1) + 1));

            // CPU Bound
            if (bound == 0) {
                // CPU Time between 100ms and 1000ms
                CPUtime = 100 + (int) (Math.random() * ((1000 - 100) + 1));
            }
            // IO Bound
            else {
                // CPU Time between 1ms and 200ms
                CPUtime = 1 + (int) (Math.random() * ((200 - 1) + 1));
            }

            cpu.startCPUuse();
            System.out.println(name + " starting CPU burst of " + CPUtime + "ms");
            try {
                Thread.sleep((int)(CPUtime * Math.random() + 1));
            }
            catch (Exception e) {}
            System.out.println(name + " is finished using the CPU");
            cpu.endCPUuse();

            System.out.println(name + " requesting to read disk track " + track);
            diskDrive.useTheDisk(track);
            System.out.println(name + " finished reading disk track " + track);
        }
    }
}