/*
 * Noah Peneycad
 * CISC324 Lab #6
 * April 3, 2018
 * UserJob.java: Creates a UserJob that is either CPU or IO bound. Each UserJob performs 10 iterations
 * of using the CPU and the disk drive.
 */

public class UserJob extends Thread {

    private String name; // UserJob number
    private int bound; // 0 = CPU bound, 1 = IO bound
    private int CPUtime; // amount of time using the CPU
    private DiskDrive diskDrive; // instance of the disk drive
    private CPUmonitor cpu; // instance of the CPU monitor containing N CPUs
    private final int DISK_TRACKS = 1024; // the disk drive has 1024 tracks

    // Constructor
    public UserJob(String job_name, int job_bound, DiskDrive drive, CPUmonitor cpuOne) {
        name = "UserJob " + job_name;
        bound = job_bound;
        diskDrive = drive;
        cpu = cpuOne;
    }

    public void run() {
        System.out.println(name + " is starting");
        // perform 10 iterations per UserJob
        for (int i = 0; i < 10; i++) {
            // gets the last used CPU from CPUmonitor
            int lastUsedCPU = cpu.getLastUsedCPU();
            // choose a random track on the disk
            int track = 1 + (int)(Math.random() * ((DISK_TRACKS - 1) + 1));

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

            // start using the CPU
            cpu.startCPUuse(name, CPUtime);
            try {
                Thread.sleep(CPUtime); // simulate time using the CPU
            }
            catch (Exception e) {}

            System.out.println(name + " requesting to read disk track " + track);
            diskDrive.useTheDisk(track);
            System.out.println(name + " finished reading disk track " + track);
            cpu.endCPUuse(name, lastUsedCPU);
        }
    }
}