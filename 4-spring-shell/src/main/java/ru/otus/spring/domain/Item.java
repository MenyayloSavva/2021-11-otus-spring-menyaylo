package ru.otus.spring.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @CsvBindByName(column = "Question")
    private String question;

    @CsvBindByName(column = "Answer")
    private String answer;
}
