public class MainMethod {

    public static void main (String argv[]) {

        System.out.println("The simulation of the computer system is starting");

        UserJob[] userJobs = new UserJob[10];
        DiskDrive diskDrive = new DiskDrive();
        int jobBound; // 0 is CPU, 1 is IO

        // CPU bound user jobs
        for (int i = 1; i <= 5; i++) {
            jobBound = (int)(Math.random() * ((1) + 1));
            userJobs[i] = new UserJob(Integer.toString(i), jobBound, diskDrive);
            userJobs[i].start();
        }

    }  // end of "main"
}  // end of "MainMethod"