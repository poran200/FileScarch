package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public List<String>  readFile(String filename) throws IOException {
        List<String>  stringList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader(filename))){

            for (String line; (line = bufferedReader.readLine()) !=null;){
                stringList.add(line);
            }
        }
        return  stringList;
    }
    public  List<String> outputList (List<String> stringList , String search){
          return stringList. stream()
                  .map(String::toLowerCase)
                  .filter(s -> s.contains(search) || s.startsWith(search))
                  .limit(10)
                 .collect(Collectors.toList());
    }

    public  void fileCreate(List<String> outputList , String fileName) throws IOException {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName+".txt", true), StandardCharsets.UTF_8)
        )){
            for (String s : outputList) {
                writer.write(s+"\n");
            }
        }
    }

    public Main() {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Search: ");
//        String search = input.nextLine().toLowerCase();
        List<String> stringList =  new ArrayList<>();
        List<String> inputting = new ArrayList<>();
        try {
            long start = System.currentTimeMillis();
            stringList = readFile("scarch.txt");
            long end = System.currentTimeMillis()-start;
            System.out.println("readFile time:"+ end);
            inputting = readFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> finalStringList = stringList;
        inputting.forEach(s-> {
                    try {
                        List<String> outputListForEachSearch = outputList(finalStringList, s);
                        fileCreate( outputListForEachSearch,s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    public static void main(String[] args) throws IOException {
       new Main();
    }
}
