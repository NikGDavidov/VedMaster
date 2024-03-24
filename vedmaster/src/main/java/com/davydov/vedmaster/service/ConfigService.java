package com.davydov.vedmaster.service;

import com.davydov.vedmaster.models.Config;
import com.davydov.vedmaster.models.Parameters;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@RequiredArgsConstructor
@Data
public class ConfigService {
    @Autowired
    SupplierService supplierService;
    /*
        public void showConfig(Config config) throws FileNotFoundException {
            System.out.println(config.getFileName());
            GetInfo.readFile(config.getFileName());
        }
    */
    public void readFile(Config config) throws FileNotFoundException {
        FileInputStream file = new FileInputStream(config.getFileName());
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        List<ItemRequest> requestList = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ItemRequest itemRequest = new ItemRequest();
            PriceRequest priceRequest = new PriceRequest();
            RemainsRequest remainsRequest = new RemainsRequest();

            itemRequest.setName(row.getCell(0).getStringCellValue());
            itemRequest.setArticle(row.getCell(1).getStringCellValue());
            priceRequest.setPurchasePrice(row.getCell(2).getNumericCellValue());
            itemRequest.setPriceRequest(priceRequest);

            remainsRequest.setCurrentQty(row.getCell(3).getNumericCellValue());
            itemRequest.setRemainsRequest(remainsRequest);

            for (int i=4; i<=Parameters.getWeeksNum()*2+5; i++) {
                if (row.getCell(i)==null) itemRequest.getSalesPerWeek().add(0d);
                else itemRequest.getSalesPerWeek().add(row.getCell(i).getNumericCellValue());

            }

            requestList.add(itemRequest);

        }
        supplierService.addItems(requestList);
    }
}


