package com.school;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();

        List <String> replyList = main.readCsv();
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