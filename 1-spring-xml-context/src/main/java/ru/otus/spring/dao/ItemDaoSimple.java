package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.spring.domain.Item;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
public class ItemDaoSimple implements ItemDao {

    private String pathToCsv;

    @Override
    public List<Item> findAll() {
        InputStream in = getClass().getResourceAsStream(pathToCsv);
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

        return new CsvToBeanBuilder(reader)
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
}
