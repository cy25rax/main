package com.example.homework204;

import java.util.ArrayList;

public class PeopleInChat {
    private ArrayList<String> arrayList = new ArrayList<>();

    public PeopleInChat() {

    }

    public void add (String peopleInChat){
        this.arrayList.add(peopleInChat);
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }
}
