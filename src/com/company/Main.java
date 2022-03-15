package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //создание папки 1 способ
        File dir = new File("E://Test_java");
        boolean created = dir.mkdir();
        if (created)
            System.out.println("папка создана");
        else
            System.out.println("ошибка создания папки");

        //создание папки 2 способ
        try {
            Files.createDirectory(Path.of("E://Test_java/test"));
            System.out.println("папка 2 способом создана");
        } catch (IOException e) {
            System.out.println("папка 2 способом не создана");
        }

        //создание папки и подпапок
        try {
            Files.createDirectories(Path.of("E://test1/test2/test3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //создание файла 1 способ
        File file = new File("E://test1/test2/test3/text1.txt");
        try {
            created = file.createNewFile();
            System.out.println("результат создания файла " + created);
        } catch (IOException e) {
        }

        //создание файла 2 способ
        try {
            Files.createFile(Path.of("E://test1/test2/test3/text2.txt"));
        } catch (IOException e) {
        }

        //размер файла
        try {
            System.out.println(Files.size(Path.of("E://test1/test2/test3/text3.txt")) + "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //дерево каталогов и файлов
        File filesTree = new File("C://");
        if (filesTree.isDirectory()){
            //цикл for each
            for (File item : filesTree.listFiles()){
                if (item.isDirectory()){
                    System.out.println(item.getName() + " папка");
                }
                else {
                    System.out.println(item.getName() + " файл");
                }
            }
        }

        //запись в файл 1 способ
        File file2 = new File("E://test1/test2/test3/text4.txt");
        try {
            FileWriter fileWriter = new FileWriter(file2,true);
            fileWriter.write("1 строка \n");
            fileWriter.write("2 строка");
            fileWriter.write("3 строка \n");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // чтение из файла 1 способ
        try {
            FileReader fileReader = new FileReader(file2);
            Scanner in = new Scanner(fileReader);
            while (in.hasNext()){
                System.out.println(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Чтение из файла 2 способом
        System.out.println("2 способ");
        List<String> list = null ;
        try {
            list = Files.readAllLines(Path.of("E://test1/test2/test3/text4.txt"));
            for (String str: list
                 ) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Запись 2 способом
        list.clear();
        list.add("весна");
        list.add("осень");
        list.add("зима");

        Path path = Path.of("E://test1/test2/test3/text4.txt");
        try {
            //строка в файл
            Files.writeString(path,"Какая-то строка",
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            //строка через биты в файл
            String stroka = "просто строка";
            byte[] bs = stroka.getBytes();
            Files.write(path,bs,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            //список в файл
            Files.write(path,list,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
