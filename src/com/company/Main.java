package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public List<String>  readInputFile(String filename) throws IOException {
        List<String>  stringList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader(filename))){
            for (String line; (line = bufferedReader.readLine()) !=null;){
                stringList.add(line);
            }
        }
        return  stringList;
    }

    public List<String>  readDataFile(String filename) throws IOException {
        List<String>  stringList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader(filename))){

            for (String line; (line = bufferedReader.readLine()) !=null;){
                   String[] topicArray = line.split("\t");
                   String sentence = topicArray[2];
                  // System.out.println("sentence = " + sentence);
                  stringList.add(sentence);
            }
        }
        return  stringList;
    }

    public  List<String> outputList (List<String> stringList , String search){
          return stringList. stream()
                  .map(String::toLowerCase)
                  .filter(s -> s.startsWith(search))
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

    public Main() throws IOException {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Search: ");
//        String search = input.nextLine().toLowerCase();

        List<String> sampleDataList =  new ArrayList<>();
        List<String> inputList = new ArrayList<>();
        try {
            long start = System.currentTimeMillis();
            sampleDataList=  readDataFile("sentences.csv");

            long end = System.currentTimeMillis()-start;
            System.out.println("Sample Data read file time readFile time:"+ end);

            inputList = readInputFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

      final   List<String> finalSampleDataList = sampleDataList;
        inputList.forEach(keyWord-> {
                    try {
                        long start = System.currentTimeMillis();
                        List<String> outputListForEachSearch = outputList(finalSampleDataList, keyWord);
                        long end = System.currentTimeMillis()-start;
                        System.out.println("search output  topic name: "+keyWord+" time:"+ end);
                        fileCreate( outputListForEachSearch,keyWord);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    public static void main(String[] args) throws IOException {
       new Main();
    }
}
