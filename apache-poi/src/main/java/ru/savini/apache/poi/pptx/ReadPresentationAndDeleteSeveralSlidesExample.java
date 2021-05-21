package ru.savini.apache.poi.pptx;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class ReadPresentationAndDeleteSeveralSlidesExample {
    public static void main(String[] args) throws IOException {
        try (XMLSlideShow ppt = new XMLSlideShow(new FileInputStream("./tmp/slideshow.pptx"));
             FileOutputStream out = new FileOutputStream("./tmp/new-slideshow.pptx")) {
            TreeSet<Integer> slidesForDeleteIndexes = new TreeSet<>();
            slidesForDeleteIndexes.add(1);
            slidesForDeleteIndexes.add(3);
            Iterator<Integer> slidesForDelete = slidesForDeleteIndexes.descendingIterator();
            while (slidesForDelete.hasNext()) {
                ppt.removeSlide(slidesForDelete.next());
            }
            ppt.write(out);
        }
    }
}
