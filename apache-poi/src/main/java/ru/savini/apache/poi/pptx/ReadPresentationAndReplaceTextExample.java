package ru.savini.apache.poi.pptx;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReadPresentationAndReplaceTextExample {
    private static final String searchValue = "${change me}";
    private static final String replacement = "CHANGED";
    public static void main(String[] args) throws IOException {
        try (XMLSlideShow ppt = new XMLSlideShow(new FileInputStream("./tmp/slideshow.pptx"));
             FileOutputStream out = new FileOutputStream("./tmp/new-slideshow.pptx")) {
            List<XSLFSlide> slides = ppt.getSlides();
            for (XSLFSlide slide : slides) {
                replaceInSlidePlaceholders(slide, searchValue, replacement);
                replaceInSlideShapes(slide, searchValue, replacement);
            }
            ppt.write(out);
        }
    }

    private static void replaceInSlidePlaceholders(XSLFSlide slide, String searchValue, String replacement) {
        XSLFTextShape[] textShapes = slide.getPlaceholders();
        for (XSLFTextShape textShape : textShapes) {
            replaceInXSLFTextShape(textShape, searchValue, replacement);
        }
    }

    private static void replaceInSlideShapes(XSLFSlide slide, String searchValue, String replacement) {
        List<XSLFShape> shapes = slide.getShapes();
        for (XSLFShape shape : shapes) {
            if (shape instanceof XSLFTextBox) {
                XSLFTextBox textBox = (XSLFTextBox) shape;
                replaceInXSLFTextShape(textBox, searchValue, replacement);
                continue;
            }
            if (shape instanceof XSLFAutoShape) {
                XSLFAutoShape autoShape = (XSLFAutoShape) shape;
                replaceInXSLFTextShape(autoShape, searchValue, replacement);
            }
        }
    }

    private static void replaceInXSLFTextShape(XSLFTextShape textShape, String searchValue, String replacement) {
        List<XSLFTextParagraph> paragraphs = textShape.getTextParagraphs();
        for (XSLFTextParagraph paragraph : paragraphs) {
            replace(paragraph, searchValue, replacement);
        }
    }

    private static void replace(XSLFTextParagraph paragraph, String searchValue, String replacement) {
        List<XSLFTextRun> textRuns = paragraph.getTextRuns();
        for (XSLFTextRun textRun : textRuns) {
            if (hasReplaceableItem(textRun.getRawText(), searchValue)) {
                String replacedText = StringUtils.replace(textRun.getRawText(), searchValue, replacement);
                textRun.setText(replacedText);
                break;
            }
        }
    }

    private static boolean hasReplaceableItem(String runText, String searchValue) {
        return StringUtils.contains(runText, searchValue);
    }
}
