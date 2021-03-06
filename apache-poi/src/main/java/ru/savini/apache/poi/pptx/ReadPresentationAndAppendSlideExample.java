package ru.savini.apache.poi.pptx;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadPresentationAndAppendSlideExample {
    public static void main(String[] args) throws IOException {
        try (XMLSlideShow ppt = new XMLSlideShow(new FileInputStream("./tmp/slideshow.pptx"));
             FileOutputStream out = new FileOutputStream("./tmp/new-slideshow.pptx")) {
            ppt.createSlide();
            ppt.write(out);
        }
    }
}
