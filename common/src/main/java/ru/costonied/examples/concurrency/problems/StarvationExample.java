package ru.costonied.examples.concurrency.problems;


import java.awt.*;
import javax.swing.*;

/**
 * Example showing Starvation problem.
 * Got it from here:
 * https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/thread-starvation.html
 */
public class StarvationExample {
    private static Object sharedObj = new Object();

    public static void main (String[] args) {

        JFrame frame = createFrame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        for (int i = 0; i < 5; i++) {
            ProgressThread progressThread = new ProgressThread();
            frame.add(progressThread.getProgressComponent());
            progressThread.start();
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Creating Swing frame
     * @return JFrame
     */
    private static JFrame createFrame () {
        JFrame frame = new JFrame("Starvation Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        return frame;
    }


    private static class ProgressThread extends Thread {
        JProgressBar progressBar;

        ProgressThread () {
            progressBar = new JProgressBar();
            progressBar.setString(this.getName());
            progressBar.setStringPainted(true);
        }

        JComponent getProgressComponent () {
            return progressBar;
        }

        @Override
        public void run () {

            int c = 0;
            while (true) {
                synchronized (sharedObj) {
                    if (c == 100) {
                        c = 0;
                    }
                    progressBar.setValue(++c);
                    try {
                        //sleep the thread to simulate long running task
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
