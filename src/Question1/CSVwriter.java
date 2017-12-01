package Question1;

import java.io.FileWriter;

class CSVwriter {
    public static void writeCsvFile(String fileName, double[][] results) {		
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 22; i++) {
                    fileWriter.append("" + results[i][j]);
                    fileWriter.append(",");
                }                            
                fileWriter.append("\n");
            }
            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }    
}
