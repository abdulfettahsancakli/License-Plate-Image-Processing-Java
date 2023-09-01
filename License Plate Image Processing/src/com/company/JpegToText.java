package com.company;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class JpegToText {
    BufferedImage image;
    int width,height;
    String type = "3"; //Dosyanın türünü belirttik.
    String maxValue = "255"; //Dosyanın en büyük değerini atadık.



    public JpegToText(){
        try {
            File inputFile = new File("circle1.jpg");//Okunacak dosyanın yolunu belirttik.
            File outputFile = new File("ADVANCED FETTAH-MERYEM/circle1.txt");//Okunan dosyanın çevirileceği dosyayı belirttik
            FileOutputStream fos = new FileOutputStream(outputFile);

            BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(fos));
            image = ImageIO.read(inputFile); //inputFile ile okuduğumuz resmi IMAGEIO.read özelliğini kullanarak image objesine eşitledik.
            width = image.getWidth(); // Resmin genişliğini aldık.
            height = image.getHeight(); //Resmin yüksekliğini aldık
            bufferedwriter.write(type); //Dosyanın içerisine type ını yazdırdık
            bufferedwriter.newLine(); //Satır atladık
            bufferedwriter.write(width+" "+height); //Elde ettiğimiz yükseklik ve genişliği dosyaya yazdırdık
            bufferedwriter.newLine();
            bufferedwriter.write(maxValue); // Elde ettiğimiz maksimum değeri dosyaya yazdırdık.
            bufferedwriter.newLine();

            System.out.println("Width:"+width+" Height:"+height);



            for(int i=0; i<height; i++) {

                for(int j=0; j<width; j++) {

                    //Dosyanın genişliği ve yüksekliğini dönerek renklerine erişip renk pixellerini dosyaya yazdırdık.
                    Color color = new Color(image.getRGB(j, i));
                    bufferedwriter.write(color.getRed()+" "+color.getGreen()+" "+color.getBlue()+" ");

                }
            }
            bufferedwriter.close();// Dosyayı kapattık.

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        JpegToText jpgToTxt = new JpegToText();
    }
}