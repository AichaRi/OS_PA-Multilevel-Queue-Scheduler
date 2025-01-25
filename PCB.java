public class PCB {

    private String processID;
    private int priority;
    private int arrivalTime;
    private int cpuBurst;
    private int startTime;
    private int terminationTime;
    private int turnaroundTime;
    private int waitingTime;
    private int responseTime;

    public PCB(String processID, int priority, int arrivalTime, int cpuBurst) {
        this.processID = processID;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
        this.startTime = -1;
        this.terminationTime = -1;
        this.turnaroundTime = -1;
        this.waitingTime = -1 ;
        this.responseTime = -1 ;
    }

    //setters

    public void setPriority(int priority){
        this.priority = priority;
    }

    public void setProcessID(String processID){
        this.processID = processID;
    }

    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public void setCpuBurst(int cpuBurst){
        this.cpuBurst=cpuBurst;
    }

    public void setStartTime(int startTime){
        this.startTime = startTime;
    }

    public void setTerminationTime(int terminationTime){
        this.terminationTime = terminationTime;
    }

    public void setTurnaroundTime(int turnaroundTime){
        this.turnaroundTime = turnaroundTime;
    }

    public void setWaitingTime(int waitingTime){
        this.waitingTime = waitingTime;
    }

    public void setResponseTime(int responseTime){
        this.responseTime = responseTime;
    }



    //getters

    public int getPriority(){
        return priority;
    }

    public String getID(){
        return processID;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }

    public int getCpuBurst(){
        return cpuBurst;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getTerminationTime(){
        return terminationTime;
    }

    public int getTurnaroundTime(){
        return turnaroundTime;
    }

    public int getWaitingTime(){
        return waitingTime;
    }

    public int getResponseTime(){
        return responseTime;
    }





}
