/*
 * Noah Peneycad
 * CISC324 Lab #6
 * April 3, 2018
 * MainMethod.java: This method initializes the simulation, including the user jobs, disk drive, and CPU(s).
 * It creates N CPUs and K user jobs.
 */

public class MainMethod {

    public static void main (String argv[]) {

        System.out.println("The simulation of the computer system is starting");

        int N = 3; // number of CPUs to be used in the simulation
        int K = 10; // number of UserJobs to be used in the simulation

        UserJob[] userJobs = new UserJob[11];
        DiskDrive diskDrive = new DiskDrive();
        CPUmonitor cpu = new CPUmonitor(N);
        int jobBound; // 0 is CPU, 1 is IO

        for (int i = 1; i <= K; i++) {
            jobBound = (int)(Math.random() * ((1) + 1)); // randomly choose 0 or 1
            userJobs[i] = new UserJob(Integer.toString(i), jobBound, diskDrive, cpu);
            userJobs[i].start();
        }

    }  // end of "main"
}  // end of "MainMethod"