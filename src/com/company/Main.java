package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public List<String>  readFile() throws IOException {
        List<String>  stringList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader("input.txt"))){

            for (String line; (line = bufferedReader.readLine()) !=null;){
                stringList.add(line);
            }
        }
        return  stringList;
    }
    public Main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Search: ");
        String search = input.nextLine().toLowerCase();
        List<String> stringList =  new ArrayList<>();

        try {
            long start = System.currentTimeMillis();
            stringList = readFile();
            long end = System.currentTimeMillis()-start;
            System.out.println("readFile time:"+ end);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stringList. stream()
                .filter(s -> s.contains(search))
                .limit(10)
                .forEach(System.out::println);
    }
    public static void main(String[] args) throws IOException {
       new Main();
    }
}
