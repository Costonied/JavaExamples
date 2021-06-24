package ru.savini.apache.poi.pptx;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadPresentationAndDeleteOneSlideExample {
    private static final int SLIDE_INDEX_FOR_DELETE = 1;

    public static void main(String[] args) throws IOException {
        try (XMLSlideShow ppt = new XMLSlideShow(new FileInputStream("./tmp/slideshow.pptx"));
             FileOutputStream out = new FileOutputStream("./tmp/new-slideshow.pptx")) {
            XSLFSlide slide = ppt.getSlides().get(SLIDE_INDEX_FOR_DELETE);
            deleteSlideShapes(slide);
            ppt.removeSlide(SLIDE_INDEX_FOR_DELETE);
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
