package ru.costonied.examples.io.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class create input stream from File and output stream for another File.
 * After it copy paste data from one to another.
 */
public class FileCopy
{
    public static void main(String[] args) throws IOException
    {
        String file1 = "/Users/igor/tmp/1.jpg";
        String file2 = "/Users/igor/tmp/2.jpg";

        FileInputStream fileInputStream = new FileInputStream(file1);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);

        while (fileInputStream.available() > 0)
        {
            int data = fileInputStream.read();
            fileOutputStream.write(data);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}