package it.leo.rendicontationplatform.support.objects;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Report {
    private String name;
    private int value;


    public Report(String name, int value) {
        this.name = name;
        this.value = value;
    }


}
