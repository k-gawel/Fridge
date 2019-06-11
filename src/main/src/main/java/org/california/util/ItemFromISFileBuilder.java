package org.california.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemFromISFileBuilder {

    File file;
    String content;

    Pattern pattern;
    Matcher matcher;

    private Long itemBarcode;
    private String itemCategory;
    private String itemProducent;
    private List<String> itemNutritions;
    private Map<String, String> itemNutritionalValues;
    private String itemName;
    private String itemStorage;
    private List<String> itemAlergens;

    public ItemFromISFileBuilder(File file) throws IOException {
        this.file = file;

        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            this.itemBarcode = Long.valueOf(bufferedReader.readLine());
            this.itemProducent = bufferedReader.readLine();
            this.itemName = file.getName();
            String[] parent = file.getParent().split("/");
            this.itemCategory = parent[parent.length-1];


            StringBuilder contentBuilder = new StringBuilder();

            while(bufferedReader.ready()) {
                contentBuilder.append(bufferedReader.readLine());
            }

            this.content = contentBuilder.toString().replace("\n", "");

            this.itemNutritions = getNutritions();
            this.itemStorage = getStorage();
            this.itemAlergens = getAlergens();


        } catch (Exception ex) {
            if(bufferedReader != null) bufferedReader.close();
            System.out.println(file.getName() + ": file not found or file error");
        }

    }


    public static File[] searchFiles() {

        File file = new File("IS");

        File[] files = file.listFiles();

        return files;



    }

    public static Set<File> getFiles(File parent) {

        Set<File> result = new HashSet<>();


        for (File file : parent.listFiles()) {
            if (!file.isDirectory()) {
                result.add(file);
            }
            if (file.listFiles() != null) {
                result.addAll(getFiles(file));
            }
        }

        return result;
    }

    public List<String> getNutritions() {

        List<String> result = new ArrayList<>();

        String regex = "Skład: </p> <p>(.+?)<br></p>";



        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(this.content);

        if(matcher.find()) {

            String nutritions = matcher.group(1);
            nutritions.replaceAll(",\\s[0-9]", ",");
            result = Arrays.asList(nutritions.split(", "));
        }

        boolean isBracketOpen = false;
        boolean isBracketClosed = false;
        int bracketOpenLine = 0;

        String string;

        for(int i = 0; i < result.size() ; i++ ) {

            string = result.get(i);

            if(string.contains("(")) {
                isBracketOpen = true;
                bracketOpenLine = i;
            }
            if(string.contains(")")) {
                isBracketOpen = false;
            }

            if(isBracketOpen) {
                result.set(bracketOpenLine, result.get(i) + ", " + string);
                result.set(i, "");

            }
        }



        return result;

    }

    public String getStorage() {

        String result = "";

        String regex = "Przechowywanie:</span></p>(.+?)<p class=";

        pattern = Pattern.compile(regex);

        matcher = pattern.matcher(this.content);

        try {
            matcher.find();
            result = matcher.group(1);
            result = result.replaceAll("<(.+?>)", "");
            return result;
        } catch (Exception e) {
            return "";
        }

    }

    private List<String> getAlergens() {

        List<String> result = new ArrayList<>();

        String regex = "Zawiera: (.+?)<";

        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(this.content);

        while (matcher.find()) {

            result.add(matcher.group(1).replaceAll("<(.+?>)", ""));

        }

        return result;


    }

    private Map<String, String> getNutritionalValues() {

        Map<String, String> result = new HashMap<>();

        String regex = "<p class=\"tytul\">Wartości odżywcze: </p>(.+?)</p>";
        String line;

        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(this.content);

        if(matcher.find()) {
            line = matcher.group(1);
        } else {
            return result;
        }

        regex = "<br>(.+?)<br>";

        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(line);

            matcher.find();
            line = matcher.group(1);
            line = line.replaceAll("<(.+?>)", "");
        } catch (Exception e) {
            return result;
        }

        String[] nutritionalValues = line.split(", ");

        for (String column: nutritionalValues) {
            result.put(column.split(": ")[0], column.split(": ")[1]);
        }


        return result;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public Long getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(Long itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemProducent() {
        return itemProducent;
    }

    public void setItemProducent(String itemProducent) {
        this.itemProducent = itemProducent;
    }

    public List<String> getItemNutritions() {
        return itemNutritions;
    }

    public void setItemNutritions(List<String> itemNutritions) {
        this.itemNutritions = itemNutritions;
    }

    public Map<String, String> getItemNutritionalValues() {
        return itemNutritionalValues;
    }

    public void setItemNutritionalValues(Map<String, String> itemNutritionalValues) {
        this.itemNutritionalValues = itemNutritionalValues;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStorage() {
        return itemStorage;
    }

    public void setItemStorage(String itemStorage) {
        this.itemStorage = itemStorage;
    }

    public List<String> getItemAlergens() {
        return itemAlergens;
    }

    public void setItemAlergens(List<String> itemAlergens) {
        this.itemAlergens = itemAlergens;
    }
}
