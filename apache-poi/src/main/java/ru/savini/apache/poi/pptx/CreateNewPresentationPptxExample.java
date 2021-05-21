package ru.savini.apache.poi.pptx;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateNewPresentationPptxExample {
    public static void main(String[] args) {
        try (XMLSlideShow ppt = new XMLSlideShow();
             FileOutputStream out = new FileOutputStream("./tmp/slideshow.pptx")) {
            ppt.createSlide();
            ppt.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
