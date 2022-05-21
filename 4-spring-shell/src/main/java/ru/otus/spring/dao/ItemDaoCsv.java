package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.configuration.ProjectConfigs;
import ru.otus.spring.domain.Item;
import ru.otus.spring.input.FileReader;
import ru.otus.spring.utils.ConfigUtils;

import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
@Component
@RequiredArgsConstructor
public class ItemDaoCsv implements ItemDao {

    private final FileReader fileReader;
    private final ProjectConfigs configs;

    @Override
    public List<Item> findAll() {
        return new CsvToBeanBuilder(fileReader.readData(ConfigUtils.getPathToCsv(configs)))
                .withType(Item.class)
                .build()
                .parse();
    }

}
