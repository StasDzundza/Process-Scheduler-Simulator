package Scheduling;

import java.util.*;

public class MultipleQueuesScheduler{
    Vector queues;

    public MultipleQueuesScheduler(Vector processes){
        queues = new Vector();
        splitProcessesToQueues(processes);
    }

    public void splitProcessesToQueues(Vector processVector){
        Collections.sort(processVector, new Comparator<sProcess>() {
            @Override
            public int compare(sProcess p1, sProcess p2) {
                return p1.compareTo(p2);
            }
        }
        );
        int numberOfProcesses = processVector.size();
        int i = 0;
        while (i < numberOfProcesses){
            Queue<sProcess> queue = new LinkedList<>();
            sProcess process = (sProcess)processVector.get(i);
            int priority = process.cputime / process.ioblocking;
            queue.offer((sProcess)processVector.get(i));
            while(true){
                i++;
                if(i < numberOfProcesses){
                    sProcess currentProcess = (sProcess)processVector.get(i);
                    int currentPriority = currentProcess.cputime / currentProcess.ioblocking;
                    if(currentPriority == priority){
                        queue.offer(currentProcess);
                    }else{
                        break;
                    }
                }
                else {
                    break;
                }
            }
            queues.add(queue);
        }
    }

    public sProcess getNextProcess(){
        int numberOfQueues = queues.size();
        for(int i = 0; i < numberOfQueues;i++){
            Queue currentProcessGroup = (Queue)queues.get(i);
            while(currentProcessGroup.peek()!=null){//if Queue isn`t empty
                sProcess currentProcess = (sProcess)currentProcessGroup.peek();
                if(currentProcess.cpudone != currentProcess.cputime){
                    currentProcessGroup.remove();
                    currentProcessGroup.offer(currentProcess);//set currentProcess to the end of queue
                    return currentProcess;
                }else{
                    currentProcessGroup.remove();//removing finished process from queue
                }
            }
        }
        return null;
    }
}