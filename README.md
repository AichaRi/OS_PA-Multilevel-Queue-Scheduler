# Multilevel Queue Scheduling Algorithm

This project implements a **Multilevel Queue (MLQ) Scheduling Algorithm** with distinct queue priorities and scheduling mechanisms.

## Features

### Queue Structure
- **Two Queues**:
  - `Q1` (higher priority): Processes are scheduled using **Round-Robin (RR)** with a time quantum of **3ms**.
  - `Q2` (lower priority): Processes are scheduled using **Non-Preemptive Shortest Job First (SJF)**.
- Processes are assigned to queues based on their priority, with **Q1** having higher priority than **Q2**.

### Scheduling Features
- **Preemptive Priority Scheduling**: Higher-priority processes in `Q1` can preempt processes in `Q2`.
- Supports dynamic process arrivals, where new processes are added to the ready queue if they arrive when another process finishes execution.
- Handles scenarios where processes arrive at the same time as others release the CPU.

### Detailed Process Information
The program calculates and reports the following for each process:
- **Process Details**: Process ID, Priority, Arrival Time, CPU Burst Time, Start Time, Termination Time.
- **Performance Metrics**:
  - Turnaround Time.
  - Waiting Time.
  - Response Time.
- **Overall System Metrics**:
  - Average Turnaround Time.
  - Average Waiting Time.
  - Average Response Time.

### User Menu
The program includes a simple user menu:
1. **Input Process Information**: Add processes to the system, including priority, arrival time, and CPU burst time.
2. **Generate Report**: Displays detailed information about all processes, scheduling order, and performance metrics on the console and writes them to a `Report.txt` file.
3. **Exit**: Exit the program.



## Contributions
This was a project assignment for an **Operating Systems** course. My main contribution was implementing the **Round-Robin (RR) Scheduling Algorithm** for `Q1`. The project was completed collaboratively, with other members working on the SJF algorithm and reporting functionalities.

## Sample Run

```
──*───*───*───*───*──Welcome to Process Schedualing Program──*───*───*───*───*──
~~Options~~
1-Enter process information
2-Display schedualing of processes
3-Exit program
Please enter your choice:
1
Enter number of processes:
4
[Process 1 info]
● Priority (1 or 2): 
1
● Arrival time: 
0
● CPU burst: 
5
[Process 2 info]
● Priority (1 or 2): 
1
● Arrival time: 
0
● CPU burst: 
3
[Process 3 info]
● Priority (1 or 2): 
1
● Arrival time: 
8
● CPU burst: 
2
[Process 4 info]
● Priority (1 or 2): 
1
● Arrival time: 
9
● CPU burst: 
3
~~Options~~
1-Enter process information
2-Display schedualing of processes
3-Exit program
Please enter your choice:
2
────────Schedualing Order────────
|P1||P2||P1||P3||P4|
────────Detailed Info About Proccess────────
--------------------------------------------------------------------------------
● Proccess ID: P1
● Priority: 1
● Arrival time: 0
● CPU burst: 5
● Start time: 0
● Termination time: 8
● Turnaround time: 8
● Waiting time: 3
● Response time: 0
--------------------------------------------------------------------------------
● Proccess ID: P2
● Priority: 1
● Arrival time: 0
● CPU burst: 3
● Start time: 3
● Termination time: 6
● Turnaround time: 6
● Waiting time: 3
● Response time: 3
--------------------------------------------------------------------------------
● Proccess ID: P3
● Priority: 1
● Arrival time: 8
● CPU burst: 2
● Start time: 8
● Termination time: 10
● Turnaround time: 2
● Waiting time: 0
● Response time: 0
--------------------------------------------------------------------------------
● Proccess ID: P4
● Priority: 1
● Arrival time: 9
● CPU burst: 3
● Start time: 10
● Termination time: 13
● Turnaround time: 4
● Waiting time: 1
● Response time: 1
────────Averages────────
✦ Average turnaround time: 5.0
✦ Average waiting time: 1.75
✦ Average response time: 1.0
~~Options~~
1-Enter process information
2-Display schedualing of processes
3-Exit program
Please enter your choice:
3
Bye Bye✧
           __
          / _)
    .-^^^-/ /
 __/       /
<.||-||
```
