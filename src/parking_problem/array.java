package parking_problem;
import java.lang.reflect.Array;

//import games.allotment;

public class array {
  
  public static int[][][] parking=new int[2][4][7];

  public static int basement=2,row=4,column=7;
  public static void main(String[] args) {
      // TODO Auto-generated method stub
      int i,j,k;
     
      for(i=0;i<2;i++)
      {
          for(j=0;j<3;j++)
          {
              for(k=0;k<7;k++)
              {
                  System.out.print(parking[i][j][k]+" ");
              }
              System.out.println();
          }
          System.out.println();
      }
      
  }

}