package com.school;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();

        List <String> replyList = main.readCsv();

        List<Map<String, Object>> schoolList = main.getSchoolList();

        for(String reply : replyList) {
            String [] words = reply.split(" ");

            for(String word : words){
                if(word.contains("중학교") || word.contains("고등학교")){
                    System.out.println(word);
                }
            }
        }
    }

    private List<Map<String, Object>> getSchoolList() {
        List<Map<String, Object>> schoolList = new ArrayList<>();
        try {
            String url = "https://www.edufindkorea.com/edu/list.php?schoolgubun=%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90";
            Connection connection = Jsoup.connect(url);

            Document document = connection.get();

            Elements schoolNames = document.select("table.datatable12 tbody tr td div a");
            for(Element element : schoolNames) {
                System.out.println(element.text());
            }
        } catch (Exception e) {
            System.out.println("[ getSchoolList ERROR ] =========  " + e.getMessage());
        }
        return schoolList;
    }

    private List<String> readCsv() {
        List<String> replyList = new ArrayList<>();
        File file = new File("C:\\Users\\kja33\\Desktop\\kakao\\comments.csv");
        BufferedReader br  = null;
        String orgContent = "";

        try {
            br = new BufferedReader(new FileReader(file));
            while((orgContent = br.readLine()) != null) {
                replyList.add(orgContent);
            }
        }catch (Exception e) {
            System.out.println("READ CSV ERR : " + e.getMessage());
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return replyList;
    }
}