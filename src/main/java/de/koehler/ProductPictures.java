package de.koehler;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProductPictures {
    Properties prop = new Properties();
    FileInputStream ip = new FileInputStream(".\\src\\main\\resources\\config.properties");

    String[] cases = {
            "-NOFB.jpg",
            "-alert-product-picture.jpg",
            "-desktop-wallpaper-product-picture.jpg",
            "-intermission-banners-product-picture.jpg",
            "-panels-product-picture.jpg",
            "-talking-banner-product-picture.jpg",
            "-transition-product-picture.jpg",
            "-twitch-profile-picture.jpg",
            "-webcam-product-picture.jpg",
            "-youtube-banner-product-picture.jpg"
    };

    ProductPictures(String[] languages) throws IOException {
        prop.load(ip);

        for (String language : languages) {
            String path = prop.getProperty("seriesfolder") + prop.getProperty("seriesname") + "\\SHOP\\" + language + "\\";
            for (String aCase : cases) {
                try {
                    FilesByName files = new FilesByName(path, aCase);
                    if (files.files.length == 0){
                        System.err.println("Lang: " + language + " File: " + aCase + " Desc: File does not exist.");
                    }

                    for (File file : files.files) {
                        File copied = new File(prop.getProperty("targetPath") + prop.getProperty("seriesname") + "\\ProductPictures\\" + language + "\\" + file.getName());
                        FileUtils.copyFile(file, copied);
                    }
                } catch (NullPointerException ignored){}
            }
        }
        System.out.println("2 / 7 - ProductPictures completed.");
    }
}
