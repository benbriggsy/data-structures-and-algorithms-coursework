package Question1;

public class MatrixAlgorithms {

    public static void main(String[] args) {
        int[][] A = {   {1, 3, 7, 8, 8, 9, 12},
                        {2, 4, 8, 9, 10, 30, 38},
                        {4, 5, 10, 20, 29, 50, 60},
                        {8, 10, 11, 30, 50, 60, 61},
                        {11, 12, 40, 80, 90, 100, 111},
                        {13, 15, 50, 100, 110, 112, 120},
                        {22, 27, 61, 112, 119, 138, 153} }; 
        
        System.out.println("The given example array to be searched:");
        System.out.println(arrayToString(A));
        //4,12,110,5,6,111
        System.out.println("Find elements using algorithm D:");
        System.out.println("4: \t" + FindElementD(A, 7, 4));
        System.out.println("12: \t" + FindElementD(A, 7, 12));
        System.out.println("110: \t" + FindElementD(A, 7, 110));
        System.out.println("5: \t" + FindElementD(A, 7, 5)); 
        System.out.println("6: \t" + FindElementD(A, 7, 6));
        System.out.println("111: \t" + FindElementD(A, 7, 111));         
        System.out.println();          
        
        System.out.println("Find elements using algorithm D1:");
        System.out.println("4: \t" + FindElementD1(A, 7, 4));
        System.out.println("12: \t" + FindElementD1(A, 7, 12));
        System.out.println("110: \t" + FindElementD1(A, 7, 110));
        System.out.println("5: \t" + FindElementD1(A, 7, 5)); 
        System.out.println("6: \t" + FindElementD1(A, 7, 6));
        System.out.println("111: \t" + FindElementD1(A, 7, 111)); 
        System.out.println(); 
          
        System.out.println("Find elements using algorithm D2:");      
        System.out.println("4: \t" + FindElementD2(A, 7, 4));
        System.out.println("12: \t" + FindElementD2(A, 7, 12));
        System.out.println("110 \t" + FindElementD2(A, 7, 110));
        System.out.println("5: \t" + FindElementD2(A, 7, 5)); 
        System.out.println("6: \t" + FindElementD2(A, 7, 6));
        System.out.println("111: \t" + FindElementD2(A, 7, 111));
        System.out.println();
        
        System.out.println("An example of the array built for the tests"
                + "of algorithm D and D1 this example is of size n=10:\n");
        System.out.println(arrayToString(buildMatrix1(10)));
        System.out.println("An example of the array built for the tests"
                + "of algorithm D2 this example is of size n=10:\n");
        System.out.println(arrayToString(buildMatrix2(10)));
        
        //run algorithm tests and output results to csv
        testAlgorithmD();
        testAlgorithmD1();
        testAlgorithmD2();
    }
      
    public static boolean FindElementD(int[][] A, int n, int p) {
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (A[i][j] == p)
                    return true;
            }        
        }
        return false;
    }
    
    public static boolean FindElementD1(int[][] A, int n, int p) {
        for (int i = 0; i < n; i++){
            if(A[i][0] <= p && p <= A[i][n-1]){
                if (binarySearch(A[i], n, p))
                        return true; 
            }
        }
        return false;
    }
    
    public static boolean binarySearch(int[] A, int n, int p) {
         int first = 0;
         int last = n - 1;
          
         while(last >= first) {
             int centre = (first + last) / 2;
             if(A[centre] == p) {
                 return true;
             }
             if(A[centre] < p) {
                 first = centre + 1;
             }
             if(A[centre] > p) {
                 last = centre - 1;
             }       
        }
        return false;
   }
           
    public static boolean FindElementD2(int[][] A, int n, int p) {
        int i = 0;
        int j = n-1;
        while(i < n && j< n){
            if(p==A[j][i]){
                return true;
            }
            if(p<A[j][i]){
                j -= 1;
            }else{
                i += 1;
            }
            if(j<=0){
                return false;
            }
        }
        return false;
    }
    
    public static int[][] buildMatrix1(int n) {
        //buildMatrix1 can be used for question 1 and 2 
        int A[][] = new int[n][n];
        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++){
                A[i][j] = j;
                if(j == n-1)//the end of the row reached skip a number 
                    A[i][j] = j+1;
            }
        }
        return A;
    }
    
    public static int[][] buildMatrix2(int n) {
        //buildMatrix2 is used for question 3
        int A[][] = new int[n][n];
        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++){
                A[i][j] = i + (j*10);
            }
        }
        A[0][n-1] += 1;
        return A;
    }
    
    public static double[] timeAlgorithm(int[][] array, int size, int searchTerm, int whichAlg, int reps) {
        // Record mean and std deviation of performing an operation
        // reps times
        long t1, t2;
        double mvs[] = new double[3];
        double sum=0,s=0;
        double sumSquared=0;
        for(int i=0;i<reps;i++){
            if(whichAlg == 0){
                t1=System.nanoTime();
                //call FindElement method for an instance of size n
                FindElementD(array, size, searchTerm);
                t2=System.nanoTime()-t1;
            }else if(whichAlg == 1){
                t1=System.nanoTime();
                //call FindElement method for an instance of size n
                FindElementD1(array, size, searchTerm);
                t2=System.nanoTime()-t1;  
                
            }else{
                t1=System.nanoTime();
                //call FindElement method for an instance of size n
                FindElementD2(array, size, searchTerm);
                t2=System.nanoTime()-t1;                
            }
            //Recording it in nanoseconds
            sum+=(double)t2;
            sumSquared+=(t2)*(t2);
//            sum+=(double)t2/1000000.0;
//            sumSquared+=(t2/1000000.0)*(t2/1000000.0);
        }
        
        mvs[0]=sum/reps;
        mvs[1]=sumSquared/reps-(mvs[0]*mvs[0]);
        mvs[2]=Math.sqrt(mvs[1]);
        System.out.println(size+"\t" + mvs[0]);
        
        return mvs;
    }
    
    public static String arrayToString(int[][] a) {
        String aString;     
        aString = "";
        int column;
        int row;

        for (row = 0; row < a.length; row++) {
            for (column = 0; column < a[0].length; column++ ) {
            aString = aString + " \t" + a[row][column];
            }
        aString = aString + "\n";
        }

        return aString;
    }
    
    public static void testAlgorithmD(){
        System.out.println("\nTesting algorithm D:");
        double[][] results = new double[22][4];        
        //enter the value of n for each test into the first row of array
        int j = 0;
        for(int i = 10; i <= 100; i+=10){
            results[j++][0] = i;
        }
        for(int i = 200; i <= 1000; i+=100){
            results[j++][0] = i;
        }        
        for(int i = 2000; i <= 4000; i+=1000){
            results[j++][0] = i;
        }        
        //Run tests and send output to the relevant part of the array
        j = 0;        
        double mvs[];
        System.out.println("Size:"+"\t" + "Time:");
        for(int i = 10; i <= 100; i+=10){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix1(i), i, i+1, 0, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }
        for(int i = 200; i <= 1000; i+=100){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix1(i), i, i+1, 0, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }        
        for(int i = 2000; i <= 4000; i+=1000){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix1(i), i, i+1, 0, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }        
        //Write array to a csv file.
        CSVwriter.writeCsvFile("results0.csv", results);
    }
    
    public static void testAlgorithmD1(){
        System.out.println("\nTesting algorithm D1:");
        double[][] results = new double[22][4];        
        //enter the value of n for each test into the first row of array
        int j = 0;
        for(int i = 10; i <= 100; i+=10){
            results[j++][0] = i;
        }
        for(int i = 200; i <= 1000; i+=100){
            results[j++][0] = i;
        }        
        for(int i = 2000; i <= 4000; i+=1000){
            results[j++][0] = i;
        }        
        //Run tests and send output to the relevant part of the array
        j = 0;        
        double mvs[];
        System.out.println("Size:"+"\t" + "Time:");
        for(int i = 10; i <= 100; i+=10){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix1(i), i, i-1, 1, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }
        for(int i = 200; i <= 1000; i+=100){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix1(i), i, i-1, 1, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }        
        for(int i = 2000; i <= 4000; i+=1000){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix1(i), i, i-1, 1, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }       
        //Write array to a csv file.
        CSVwriter.writeCsvFile("results1.csv", results);
    }

    public static void testAlgorithmD2(){
        System.out.println("\nTesting algorithm D2:");
        double[][] results = new double[22][4];        
        //enter the value of n for each test into the first row of array
        int j = 0;
        for(int i = 10; i <= 100; i+=10){
            results[j++][0] = i;
        }
        for(int i = 200; i <= 1000; i+=100){
            results[j++][0] = i;
        }        
        for(int i = 2000; i <= 4000; i+=1000){
            results[j++][0] = i;
        }        
        //Run tests and send output to the relevant part of the array
        j = 0;        
        double mvs[];
        System.out.println("Size:"+"\t" + "Time:");
        for(int i = 10; i <= 100; i+=10){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix2(i), i, (i-1)*10, 2, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }
        for(int i = 200; i <= 1000; i+=100){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix2(i), i, (i-1)*10, 2, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }        
        for(int i = 2000; i <= 4000; i+=1000){
            results[j][0] = i;
            mvs = timeAlgorithm(buildMatrix2(i), i, (i-1)*10, 2, 1000);
            results[j][1] = mvs[0];
            results[j][2] = mvs[1];
            results[j][3] = mvs[2];
            j++;
        }        
        //Write array to a csv file.
        CSVwriter.writeCsvFile("results2.csv", results);
    }
}
