package org.california.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ISImages {

    public Map<Long, String> getImagesLinks() throws IOException {

        Set<File> fileSet = ItemFromISFileBuilder.getFiles(new File("IS"));
        File images_urls = new File("images_urls");

        Map<Long, String> urls = new HashMap<>();

        try {
            FileWriter writer = new FileWriter(images_urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = null;
        Long barcode;
        String url;

        for(File file: fileSet) {

            try {

                System.out.println("Read " + file.getName() );
                reader = new BufferedReader(new FileReader(file));

                barcode = Long.valueOf(reader.readLine());

                reader.readLine(); // name
                reader.readLine(); // item link

                url = reader.readLine();

                urls.put(barcode, url);

                barcode = null;
                url = null;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        if (reader != null) {
            reader.close();
        }

        return urls;


    }

    public void write(Map<Long, String> map) throws IOException {

        FileWriter writer = new FileWriter("images_urls");

        for(Long key: map.keySet()) {

            System.out.println("WRITING " + map.get(key) + " " + key);

            writer.write(key + ";" + map.get(key) + "\n");
        }

        writer.close();

    }

    public void  download(String barcode, String link) throws IOException {
        link = "https://internetowysupermarket.pl/" + link;
        URL url = new URL(link);

        System.out.println("DOWNLOADING " + link );

        BufferedImage image = null;


        try {
            image = ImageIO.read(url);

            ImageIO.write(image, "jpg", new File("images/" + barcode + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
