import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Driver {

    static Scanner input = new Scanner(System.in);
    static List<PCB> Q1=new ArrayList<>(); // RR
    static List<PCB> Q2=new ArrayList<>(); //SJF
    static List<PCB> gnattChart=new ArrayList<>(); //proccess might be duplicated
    static List<PCB>  finalList=new ArrayList<>(); //the final proccess
    static List<PCB>  Q1Copy = new ArrayList<>();//this is what we will save the orderd list to then  copy them to Q1 in shaa allah
    static int processCounter;
    static final int TIME_QUANTUM=3;
    static int currentTime=0;
    


    public static void main(String[] args) {
        int choice = 0;
        boolean flag = true;
        System.out.println("──*───*───*───*───*──Welcome to Process Schedualing Program──*───*───*───*───*──");
        while (flag){
            System.out.println("~~Options~~\n1-Enter process information\n2-Display schedualing of processes\n3-Exit program");
            System.out.println("Please enter your choice:");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    clearLists();
                    if (putProccesInQueue()){
                        if(!Q1.isEmpty())
                            RR();
                        if(!Q2.isEmpty())
                            SJF();
                        scheduleProcesses();
                        break;
                    }
                    break;
                case 2:
                    if(finalList.isEmpty()){
                        System.out.println("XX !There are no proccess to be schedualed, please enter proccess info first! XX");
                        break;
                    }
                    else{
                        displaySchedualingOrder();
                        writeOnFile();
                        break;
                    }
             
                case 3:
                    System.out.println("Bye Bye✧");
                    System.out.println("           __");
                    System.out.println("          / _)");
                    System.out.println("    .-^^^-/ /");
                    System.out.println(" __/       /");
                    System.out.println("<.||-||");
        
                    flag = false;
                    break;
                default:
                    System.out.println("XX !enter a valid choice! XX");
                    continue;
            }
        }


    }//main

    private static boolean putProccesInQueue(){
        System.out.println("Enter number of processes:");
        int num = input.nextInt(); 
        if(num > 0){
            for (int i = 0 ; i < num ; i++){ //add process to an array
                System.out.println("[Process " + (i+1) + " info]");
                System.out.println("● Priority (1 or 2): ");
                int priority = input.nextInt();
                System.out.println("● Arrival time: ");
                int arrivalTime = input.nextInt();
                System.out.println("● CPU burst: ");
                int cpuBurst = input.nextInt();
                switch (priority) {
                    case 1:
                        Q1.add(new PCB(("P"+(i+1)),priority, arrivalTime, cpuBurst));
                        break;
                
                    case 2:
                        Q2.add(new PCB(("P"+(i+1)),priority, arrivalTime, cpuBurst));
                        break;
                    default:
                        System.out.println("Please enter a valid priority number!⋋_⋌");
                        return false;
                }
            }
            return true;
        }
        else{
            System.out.println("Enter a valid number of processes!⋋_⋌");
            return false;
        }
    }

    private static void displaySchedualingOrder(){
        System.out.println("────────Schedualing Order────────");
        for(int i = 0 ; i<gnattChart.size() ; i++){//print the gnatt chart 
            System.out.print("|"+gnattChart.get(i).getID()+"|");
        }
        System.out.println("");
        System.out.println("────────Detailed Info About Proccess────────");
        for(int i = 0 ; i < finalList.size() ; i++){
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("● Proccess ID: "+finalList.get(i).getID());
            System.out.println("● Priority: "+ finalList.get(i).getPriority());
            System.out.println("● Arrival time: "+finalList.get(i).getArrivalTime());
            System.out.println("● CPU burst: "+finalList.get(i).getCpuBurst());
            System.out.println("● Start time: "+finalList.get(i).getStartTime());
            System.out.println("● Termination time: "+finalList.get(i).getTerminationTime());
            System.out.println("● Turnaround time: "+finalList.get(i).getTurnaroundTime());
            System.out.println("● Waiting time: "+finalList.get(i).getWaitingTime());
            System.out.println("● Response time: "+finalList.get(i).getResponseTime());
        }
        System.out.println("────────Averages────────");
        System.out.println("✦ Average turnaround time: "+ calculateAverageTurnaroundTime());
        System.out.println("✦ Average waiting time: "+ calculateAverageWaitingTime());
        System.out.println("✦ Average response time: "+ calculateAverageResponseTime());

    }

    private static void writeOnFile(){
        try{
            PrintWriter writer= new PrintWriter("Report.txt");

            writer.println("────────Schedualing Order────────");
            for(int i = 0 ; i<gnattChart.size() ; i++){//write the gnatt chart 
                writer.print("|"+gnattChart.get(i).getID()+"|");
            }
            writer.println("");
            writer.println("────────Detailed Info About Proccess────────");
            for(int i = 0 ; i < finalList.size() ; i++){
                writer.println("--------------------------------------------------------------------------------");
                writer.println("● Proccess ID: "+finalList.get(i).getID());
                writer.println("● Priority: "+ finalList.get(i).getPriority());
                writer.println("● Arrival time: "+finalList.get(i).getArrivalTime());
                writer.println("● CPU burst: "+finalList.get(i).getCpuBurst());
                writer.println("● Start time: "+finalList.get(i).getStartTime());
                writer.println("● Termination time: "+finalList.get(i).getTerminationTime());
                writer.println("● Turnaround time: "+finalList.get(i).getTurnaroundTime());
                writer.println("● Waiting time: "+finalList.get(i).getWaitingTime());
                writer.println("● Response time: "+finalList.get(i).getResponseTime());
            }

            writer.println("────────Averages────────");
            writer.println("✦ Average turnaround time: "+ calculateAverageTurnaroundTime());
            writer.println("✦ Average waiting time: "+ calculateAverageWaitingTime());
            writer.println("✦ Average response time: "+ calculateAverageResponseTime());
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    private static double calculateAverageTurnaroundTime(){
        double totalTurnAround = 0;
        for(int i=0 ; i<finalList.size() ; i++){
            totalTurnAround+= finalList.get(i).getTurnaroundTime();
        }
        double averageTurnAround = totalTurnAround/((double)finalList.size());
        return averageTurnAround;
    }

    private static double calculateAverageWaitingTime(){
        double totalWaiting = 0;
        for(int i=0 ; i<finalList.size() ; i++){
            totalWaiting+= finalList.get(i).getWaitingTime();
        }
        double averageWaiting = totalWaiting/((double)finalList.size());
        return averageWaiting;
    }

    private static double calculateAverageResponseTime(){
        double totalResponse = 0;
        for(int i=0 ; i<finalList.size() ; i++){
            totalResponse+= finalList.get(i).getResponseTime();
        }
        double averageResponse = totalResponse/((double)finalList.size());
        return averageResponse;
        
    }

    public static void clearLists() {
        finalList.clear();
        gnattChart.clear();
      }


    public static void  SJF() { 
        // Sort the processes based on arrival time 

       List <PCB> orderedProcesses = new ArrayList<>();

        int currentSJFTime = 0;
        while (!Q2.isEmpty()) {
            PCB shortestJob = null;
            for (PCB process : Q2) {
                if (process.getArrivalTime() <= currentSJFTime) {
                    if (shortestJob == null || process.getCpuBurst() < shortestJob.getCpuBurst()) {
                        shortestJob = process;
                    }
                }
            }
            if (shortestJob != null) {
                orderedProcesses.add(shortestJob);
                Q2.remove(shortestJob);
                currentSJFTime += shortestJob.getCpuBurst();
            } else {
                currentSJFTime++; // Move to the next time unit if no process is available
            }
        }
    
        Q2 = orderedProcesses;
    
    
    }
    
///////////////////////////////////////////////////////////////////////////////////
    private static void RR()
    {
        Q1Copy.addAll(Q1);

        List<PCB> orderedProcesses = new ArrayList<>();//this is what we will save the orderd list to then  copy them to Q1 in shaa allah
        List<PCB> readyQueue = new ArrayList<>();
        int currentRRTime = Q1.get(0).getArrivalTime();

        Q1Copy.sort(Comparator.comparingInt(p -> p.getArrivalTime())); // Order processes based on arrival time

        checkNewArrivals(Q1Copy, readyQueue, currentRRTime);

        while (Q1Copy.size()>0 || !readyQueue.isEmpty()  )
        {
            if (!readyQueue.isEmpty()) {
                for (PCB readyProcess : new ArrayList<>(readyQueue)) { // Use a copy to avoid ConcurrentModificationException

                    if (readyQueue.get(0).getCpuBurst() <= TIME_QUANTUM) {//-> no need to split, here we add proccess to orderdProcess list

                        readyProcess.setStartTime(currentRRTime) ; // Set start time when process enters execution
                        orderedProcesses.add(readyQueue.get(0));//executed process
                        readyQueue.get(0).setTerminationTime(currentRRTime+ readyQueue.get(0).getCpuBurst()); // Update finish time

                        currentRRTime =readyQueue.get(0).getTerminationTime();

                        readyQueue.remove(readyQueue.get(0));//donr with proccess remove from ready queue


                    } else {//readyProcess.cpuBurst > TIME_QUANTUM -> need to split process
                        PCB part2 = new PCB(readyProcess.getID(),1, readyProcess.getArrivalTime(), readyProcess.getCpuBurst() - TIME_QUANTUM);

                        if (!Q1Copy.isEmpty()) 
                        checkNewArrivals(Q1Copy, readyQueue, currentRRTime+TIME_QUANTUM);

                        readyQueue.add(part2); // Add the rest to the ready queue
                        readyProcess.setCpuBurst(TIME_QUANTUM); // Reduce CPU burst by time quantum
                    }
                }
            }
            if ( (!Q1Copy.isEmpty())&& readyQueue.isEmpty() && (!checkNewArrivals(Q1Copy, readyQueue, currentRRTime)) )//there are still processe we haven't added to the ready queue but they haven't arived yet
            currentRRTime++; 
        }
        Q1.clear();
        Q1.addAll(orderedProcesses);
    }

    private static boolean checkNewArrivals(List<PCB> processes, List<PCB> readyQueue, int currentRRTime)
    {
        List<PCB> processesToRemove = new ArrayList<>();
        boolean newArival=false;
        
        for (PCB currentProcess : processes) {
            if (currentProcess.getArrivalTime() <= currentRRTime) {
                readyQueue.add(currentProcess);
                processesToRemove.add(currentProcess);
                newArival=true;
            }else break;//because it's orderd so no need to check for rest
        }
        processes.removeAll(processesToRemove);
        return newArival;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void scheduleProcesses() {
        while (!Q1.isEmpty() || !Q2.isEmpty()) {
            PCB process = null;
            if (!Q1.isEmpty() && Q1.get(0).getArrivalTime() <= currentTime) { // prioritize Q1
                process = Q1.remove(0);
            } else if (!Q2.isEmpty()) { // Q2
                PCB shortest = Q2.get(0); // Initialize shortest to the first process in Q2
                int shortestIndex = 0;
                for (int i = 0; i < Q2.size(); i++) {
                    if (Q2.get(i).getArrivalTime() <= currentTime && Q2.get(i).getCpuBurst() < shortest.getCpuBurst()) {
                        shortest = Q2.get(i);
                        shortestIndex = i;
                    }
                }
                process = Q2.remove(shortestIndex);
            } else {
                currentTime++;
                continue;
            }
            process.setStartTime(currentTime);
            process.setResponseTime(currentTime - process.getArrivalTime());
            int burst = process.getCpuBurst();
            currentTime += burst;
            process.setTerminationTime(currentTime);
            process.setTurnaroundTime(process.getTerminationTime() - process.getArrivalTime());
            process.setWaitingTime(process.getStartTime() - process.getArrivalTime());
            gnattChart.add(process);
        }
        UpdateProcesses();
    }
    

    
    private static void UpdateProcesses() 
    {
        List<PCB> copyGnattChart=new ArrayList<>();
        copyGnattChart.addAll(gnattChart);
        // outer loop: search through list
        for (int i = 0; i < copyGnattChart.size(); i++) {
    
            PCB currentProcess = copyGnattChart.get(i);
            if (currentProcess.getPriority() == 1) 
            {
            
            String currentID = currentProcess.getID();
            PCB lastDuplicate = null;
            int totalBurst= currentProcess.getCpuBurst();
    
            // calculate burst and save the last duplicate process


            for (int j = i + 1; j < copyGnattChart.size(); j++) {
                if (copyGnattChart.get(j).getID().equals(currentID)) {
                    int burst=copyGnattChart.get(j).getCpuBurst();
                    totalBurst = totalBurst +burst;
                    lastDuplicate = copyGnattChart.get(j);
                    copyGnattChart.remove(j);
                    j--;
                }
            }
    
            if(lastDuplicate!=null)//there aru duplicates
            {
                currentProcess.setTerminationTime(lastDuplicate.getTerminationTime());
                currentProcess.setResponseTime(currentProcess.getStartTime() - currentProcess.getArrivalTime());
                currentProcess.setCpuBurst(totalBurst);
                currentProcess.setTurnaroundTime(lastDuplicate.getTerminationTime()-currentProcess.getStartTime());
                currentProcess.setWaitingTime(lastDuplicate.getTurnaroundTime()-totalBurst);
            }

            else
            {
                    currentProcess.setResponseTime(currentProcess.getStartTime() - currentProcess.getArrivalTime());
                    currentProcess.setTurnaroundTime(currentProcess.getTerminationTime()-currentProcess.getArrivalTime());
                    currentProcess.setWaitingTime(currentProcess.getResponseTime());
            }
            
            }//end if
                    //add process to new list
                    finalList.add(currentProcess);
                    
                }//end outer loop
    }//update process
}
