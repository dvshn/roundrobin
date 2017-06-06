package roundrobin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

public class RoundRobin {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the value for Time Quantum: ");
       int tq = Integer.parseInt(br.readLine());
    System.out.println("Enter the number of Processes: ");
       int n = Integer.parseInt(br.readLine());
       int proc[][] = new int[n + 1][4];
       for(int i = 1; i <= n; i++)
       {
    System.out.println("Please enter the Burst Time for Process " + i + ": ");
      proc[i][1] = Integer.parseInt(br.readLine());
     }
       System.out.println();
     
       
     int total_time = 0;
     for(int i = 1; i <= n; i++)
     {
      total_time += proc[i][1];
     }
     int time_chart[] = new int[total_time];
     
     int sel_proc = 1;
     int current_q = 0;
     for(int i = 0; i < total_time; i++)
     {
      time_chart[i] = sel_proc;
      proc[sel_proc][1]--;
      
      for(int j = 1; j <= n; j++)
      {
       if(proc[j][1] != 0)
       {
        proc[j][3]++;
        if(j != sel_proc)
         proc[j][2]++;
       }
       else if(j == sel_proc)
        proc[j][3]++;
      }
      
      if(i != 0)
      {
       if(sel_proc != time_chart[i - 1])
       {
        System.out.print("--" + i + "--P" + sel_proc);
       }
      }
      else
       System.out.print(i + "--P" + sel_proc);
      if(i == total_time - 1)
       System.out.print("--" + (i + 1));
      
      current_q++;
      if(current_q == tq || proc[sel_proc][1] == 0)
      {
       current_q = 0;
       for(int j = 1; j <= n; j++)
       {
        sel_proc++;
        if(sel_proc == (n + 1))
            sel_proc = 1;
        if(proc[sel_proc][1] != 0)
         break;
       }
      }
     }
     System.out.println();
     System.out.println();
     
     System.out.println("P\t WT  \t TT  ");
     for(int i = 1; i <= n; i++)
     {
      System.out.printf("%d\t%3dms\t%3dms",i,proc[i][2],proc[i][3]);
      System.out.println();
     }
     
     System.out.println();
     
     float WT = 0,TT = 0;
     for(int i = 1; i <= n; i++)
     {
      WT += proc[i][2];
      TT += proc[i][3];
     }
     WT /= n;
     TT /= n;
     System.out.println("The Average WT is: " + WT + "ms");
     System.out.println("The Average TT is: " + TT + "ms");
 }
}