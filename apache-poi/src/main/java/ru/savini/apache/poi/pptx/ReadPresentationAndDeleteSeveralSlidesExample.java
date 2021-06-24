package ru.savini.apache.poi.pptx;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

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
                int slideIndex = slidesForDelete.next();
                XSLFSlide slide = ppt.getSlides().get(slideIndex);
                deleteSlideShapes(slide);
                ppt.removeSlide(slideIndex);
            }
            ppt.write(out);
        }
    }

    /**
     * @implNote Удаление форм со слайдов перед удалением самого слайда
     * необходимо, чтобы уменьшить размер итоговой презентации, т.к.
     * некоторых версиях poi-ooxml замечено, что размер итогового файла
     * не уменьшается после удаления слайдов из-за того, что формы
     * записываются в итоговый файл хотя их и не видно
     */
    private static void deleteSlideShapes(XSLFSlide slide) {
        int countOfShapes = slide.getShapes().size();
        for (int i = countOfShapes - 1; i >= 0; i--) {
            XSLFShape shape = slide.getShapes().get(i);
            slide.removeShape(shape);
        }
    }
}
