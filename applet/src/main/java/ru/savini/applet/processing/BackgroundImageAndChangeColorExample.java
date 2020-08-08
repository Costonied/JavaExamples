package ru.savini.applet.processing;


import java.time.LocalTime;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Example where we add a background image, ellipse and
 * change the color of ellipse.
 *
 * NOTE: example works just for org.processing:core version 2.2.1
 * @author SavinI
 */
public class BackgroundImageAndChangeColorExample extends PApplet {

    PImage backgroundImg = null;
    LocalTime time = LocalTime.now();

    /**
     * setup() method performs just once when applet is start
     */
    @Override
    public void setup() {

        // Setup the size of canvas
        size(500, 400);

        // Get free image from internet and load it to applet
        String imageURL = "http://images.freeimages.com/images/large-previews/d8f/beach-1309743.jpg";
        backgroundImg = loadImage(imageURL, "jpg");
        backgroundImg.resize(0, height);

    }

    /**
     * draw() method performs in loop order to every time refresh the canvas
     */
    @Override
    public void draw() {

        // If image was successfully loaded then show it on canvas
        if (backgroundImg != null)
            image(backgroundImg, 0,0);

        // Set color of all shapes on canvas. In our case it is just ellipse will be.
        // Also we set behaviour to change the color depends on current seconds,
        // So it will looks like light yellow color changing to deep yellow.
        fill(255, 255, 230 - time.getSecond() * 3);
        // draw() working in loop so time will be change while Applet is working
        time = LocalTime.now();

        // Set relative position and size of ellipse order to autosize if window will change
        ellipse(width/4.0f, height/5.0f, width/6.0f, height/5.0f);

    }
}
