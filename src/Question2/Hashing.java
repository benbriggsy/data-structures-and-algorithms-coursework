package Question2;

import java.util.Random;

public class Hashing {

    public static void main(String[] args) {
//        ArrayHashTable hash = new ArrayHashTable();
//        for(int i = 0; i<50; i++){
//            hash.add(i);
//        }
//        
//        System.out.println(hash.printHash());
//        hash.remove(20);
//        System.out.println("\n"+hash.printHash());
        double[][] results = new double[18][4];
        int j = 0, reps = 15;   
        double mvs[];
        for(int i = 1000; i <= 10000; i+=1000){              
            mvs = timeHashMap(reps, i);
            results[j][0] = i;
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }
        for(int i = 15000; i <= 50000; i+=5000){
            mvs = timeHashMap(reps, i);
            results[j][0] = i;
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }        
        //Write array to a csv file.
        CSVwriter.writeCsvFile("hashResults.csv", results);        
    }
    
    static double[] timeHashMap(int reps, int n){
        // Record mean and std deviation of performing an operation
        // reps times                
        double mvs[] = new double[3];
        double sum=0,s=0;
        double sumSquared=0;
        for(int i=0;i<reps;i++){
            int[] randInts=new int[n];
            Random r=new Random();
            for(int j=0;j<n;j++){
                randInts[j]=Math.abs(r.nextInt());
            }
            ArrayHashTable hash = new ArrayHashTable();
            long t1=System.nanoTime();
            addAndRemoveNints(n, hash, randInts);
            long t2=System.nanoTime()-t1;
            //Recording it in milli seconds to make it more interpretable
            sum+=(double)t2/1000000.0;
            sumSquared+=(t2/1000000.0)*(t2/1000000.0);
        }
        mvs[0]=sum/reps;
        mvs[1]=sumSquared/reps-(mvs[0]*mvs[0]);
        mvs[2]=Math.sqrt(mvs[1]);        
        System.out.println(n+"\t" + mvs[0]);
        return mvs;
    }
    
    static void addAndRemoveNints(int n, ArrayHashTable hash, int[] randInts){
        for(int j=0;j<n;j++){
            hash.add(randInts[j]);
        }  
        for(int j=0;j<n;j++){
            hash.remove(randInts[j]);
        }
    }
    
}
