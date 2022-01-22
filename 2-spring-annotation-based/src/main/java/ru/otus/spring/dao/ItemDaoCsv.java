package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.spring.domain.Item;
import ru.otus.spring.input.FileReader;

import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
public class ItemDaoCsv implements ItemDao {

    private String pathToCsv;
    private FileReader fileReader;

    public ItemDaoCsv(String pathToCsv, FileReader fileReader) {
        this.pathToCsv = pathToCsv;
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
