package Scheduling;

public class sProcess implements Comparable{
  public int cputime;
  public int ioblocking;
  public int cpudone;
  public int ionext;
  public int numblocked;
  public int processIndex;
  public static int index;
  static {
    index = 0;
  }
  public sProcess (int cputime, int ioblocking, int cpudone, int ionext, int numblocked) {
    this.cputime = cputime;
    this.ioblocking = ioblocking;
    this.cpudone = cpudone;
    this.ionext = ionext;
    this.numblocked = numblocked;
    this.processIndex = index++;
  }

  @Override
  public int compareTo(java.lang.Object o){
    sProcess otherProcess = (sProcess)o;
    return (otherProcess.cputime/otherProcess.ioblocking) - (cputime/ioblocking);
  }
}
