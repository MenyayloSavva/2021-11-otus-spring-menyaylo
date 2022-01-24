package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.spring.domain.Item;
import ru.otus.spring.input.FileReader;

import java.util.List;

public class ItemDaoCsv implements ItemDao {

    private FileReader fileReader;
    private String pathToCsv;

    public ItemDaoCsv(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public List<Item> findAll() {
        return new CsvToBeanBuilder(fileReader.readData(pathToCsv))
                .withType(Item.class)
                .build()
                .parse();
    }

    public String getPathToCsv() {
        return pathToCsv;
    }

    public void setPathToCsv(String pathToCsv) {
        this.pathToCsv = pathToCsv;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }
}
