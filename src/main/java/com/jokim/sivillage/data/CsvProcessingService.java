package com.jokim.sivillage.data;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class CsvProcessingService {


    public void processCsvFile() throws IOException, CsvValidationException {
        String filePath = "C:\\Users\\ssginc41\\Desktop\\dev\\be\\backend\\src\\main\\java\\com\\jokim\\sivillage\\data\\골프공.csv";

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                System.out.println(Arrays.toString(nextLine));
            }

        }
    }


}
