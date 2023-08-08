package com.asayke.scheduleryandextaxi.model;

import lombok.Data;

import java.util.List;

@Data
public class Price {
    public List<Option> options;
    public String currency;
    public Double distance;
    public String time_text;
}