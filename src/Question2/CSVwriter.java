/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author benbriggs
 */
class CSVwriter {
    public static void writeCsvFile(String fileName, double[][] results) {		
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 18; i++) {
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
