package com.gamsa.activtydata;

import com.gamsa.activity.dto.DistrictSaveRequest;
import com.gamsa.activity.service.DistrictService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

@Service
@Component
@RequiredArgsConstructor
public class DistrictDataUpdateService {
    private final DistrictService districtService;

    @Value("{gamsa.csvPath}")
    private String csvPath;

    @PostConstruct
    public void DistrictInit() {
        if (!isDataChanged()) {
            loadDataFromCSV(csvPath);
        }
    }

    private boolean isDataChanged() {
        // 나중에 file 변경에 따른 로직으로 수정
        if ((districtService.findAllGungu().size() == 0)
                && (districtService.findAllSido().size() == 0)) {
            return true;
        }
        return false;
    }

    @Transactional
    public void loadDataFromCSV(String csvPath) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(csvPath).getFile());
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);

            csvReader.readNext();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                DistrictSaveRequest districtSaveRequest = DistrictSaveRequest.builder()
                        .sidoGunguCode(Integer.getInteger(nextRecord[0]))
                        .sidoCode(Integer.getInteger(nextRecord[1]))
                        .sidoName(nextRecord[2])
                        .gunguName(nextRecord[3])
                        .latitude(new BigDecimal(nextRecord[4]))
                        .longitude(new BigDecimal(nextRecord[5]))
                        .sido(Boolean.parseBoolean(nextRecord[6]))
                        .build();

                districtService.save(districtSaveRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
