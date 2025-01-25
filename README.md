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

## How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/multilevel-queue-scheduler.git
   ```
2. Compile and run the program:
   ```bash
   javac Main.java
   java Main
   ```

## Contributions
This was a project assignment for an **Operating Systems** course. My main contribution was implementing the **Round-Robin (RR) Scheduling Algorithm** for `Q1`. The project was completed collaboratively, with other members working on the SJF algorithm and reporting functionalities.

## Sample Output
- Example scheduling order:
  ```
  [P1 | P2 | P1 | P3]
  ```
- Process Report:
  ```
  Process ID: P1
  Priority: 1
  Arrival Time: 0ms
  CPU Burst: 5ms
  Start Time: 0ms
  Termination Time: 8ms
  Turnaround Time: 8ms
  Waiting Time: 3ms
  Response Time: 0ms
  ```
- Overall Metrics:
  ```
  Average Turnaround Time: 10ms
  Average Waiting Time: 5ms
  Average Response Time: 2ms
  ```
