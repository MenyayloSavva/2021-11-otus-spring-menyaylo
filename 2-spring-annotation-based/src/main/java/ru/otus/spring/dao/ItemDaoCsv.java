package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Item;
import ru.otus.spring.input.FileReader;

import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
@Component
public class ItemDaoCsv implements ItemDao {

    private FileReader fileReader;

    @Value("${paths.path_to_csv}")
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
