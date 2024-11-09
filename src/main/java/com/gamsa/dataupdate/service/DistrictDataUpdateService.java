package com.gamsa.dataupdate.service;

import com.gamsa.activity.dto.DistrictSaveRequest;
import com.gamsa.activity.service.DistrictService;
import com.gamsa.dataupdate.DataUpdateErrorCode;
import com.gamsa.dataupdate.DataUpdateException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DistrictDataUpdateService {
    private final DistrictService districtService;
    private static final Logger logger = LoggerFactory.getLogger(DistrictDataUpdateService.class);

    @Value("${data.csvpath}")
    private String csvPath;

    @PostConstruct
    public void DistrictInit() {
        logger.info("DistrictInit : path = {}", csvPath);

        loadDataFromCSV(csvPath);
    }

    private boolean isDataChanged() {
        // 나중에 file 변경에 따른 로직으로 수정
        return (districtService.findAllGungu().isEmpty())
                && (districtService.findAllSido().isEmpty());
    }

    @Transactional
    public void loadDataFromCSV(String csvPath) {
        try {
            ClassPathResource resource = new ClassPathResource(csvPath);
            File file = resource.getFile();
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);

            logger.info("csvReader : {}", csvReader);

            csvReader.readNext();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                DistrictSaveRequest districtSaveRequest = DistrictSaveRequest.builder()
                        .sidoGunguCode(Integer.parseInt(nextRecord[0]))
                        .sidoCode(Integer.parseInt(nextRecord[1]))
                        .sidoName(nextRecord[2])
                        .gunguName(nextRecord[3])
                        .latitude(new BigDecimal(nextRecord[4]))
                        .longitude(new BigDecimal(nextRecord[5]))
                        .sido(Boolean.parseBoolean(nextRecord[6]))
                        .build();

                districtService.save(districtSaveRequest);
            }
        } catch (IOException e) {
            throw new DataUpdateException(DataUpdateErrorCode.INVALID_FILE_SOURCE);
        } catch (CsvValidationException e) {
            throw new DataUpdateException(DataUpdateErrorCode.INVALID_CSV);
        } catch (Exception e) {
            logger.info("Error :{}", e.getMessage());
        }
    }
}
