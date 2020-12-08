package com.example.pokedex;

import java.util.List;

public class FeedEntry {
    private String num;
    private String name;
    private String weight;
    private String height;
    private String img;
    private List<String> type;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "name=" + name + '\n' +
                "num=" + num + '\n' +
                "type=" + type + '\n' +
                "img=" + img + '\n';
                "height=" + height + '\n' +
                "weight=" + weight + '\n' +
    }
}