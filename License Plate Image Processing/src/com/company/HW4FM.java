package com.company;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class HW4FM {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the converted image as txt:");
        String fileName = scan.nextLine();
        //Kullanıcıdan image proccessing işlemlerinin uygulanması için bir txt belgesi istedik.
        //Bu dönüştürme işlemini daha önceden jpegtotext clasından elde etmiştik.
        class ReadFile {
            int width;  //Okunacak dosyanın genişliği
            int height; //Okuncacak dosyanın yüksekliği
            int[][][] pixels = null; //Okuncak dosyanın içerisindeki pixellerin değerlerinin tutulacağı yer

            public void readImage(String fileName) {
                Scanner inFile = null;
                try {
                    inFile = new Scanner(new File(fileName));
                    int fileType = inFile.nextInt();
                    width = inFile.nextInt();  //Dosya içeriğinin genişliğini aldık.
                    height = inFile.nextInt(); //Dosya içeriğinin yüksekliğini aldık.
                    inFile.nextInt();
                    System.out.printf("type: %d, width: %d, height:%d\n",
                            fileType, width, height);
                    pixels = new int[width][height][3]; //pixels değerlerini tutan değerleri belirledik.
                    for (int col = 0; col < height; col++)
                        for (int row = 0; row < width; row++) {
                            for (int rgb = 0; rgb < 3; rgb++) {
                                pixels[row][col][rgb] = inFile.nextInt();//Satır sütun okuyarak pixels arrayinin değerlerini içine atadık.
                            }
                        }

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        ReadFile read = new ReadFile(); //ReadFile sınıfndan bir read objesi olusturduk
        read.readImage(fileName); // Oluşturduğumuz obje ile ReadFile sınıfındaki readImage metodunu kullanıcıdan aldığımız dosya ismini gönderdik.

        int width = read.width; // Okuduğumuz dosyadan elde ettiğimiz genişlik değerini width değişkenine atadık.
        int height = read.height; //Okuduğumuz dosyadan elde ettiğimiz yükseklik değerini height değişkenine atadık.
        int[][][] Imagepixels = read.pixels; //Okuduğumuz dosyadan elde ettiğimiz r g b değerlerini Imagepixels arrayinde eşitledik.



        class ConvertImages {
            // Bu classda original resmimizi grayscale ve binary metodlarını uygulayarak değiştirdik.
            public int[][] pixels = null;
            public void convertGrayScale(int width, int height, int[][][] graypixels) { // Metodun uygulanması için width,height ve 3 boyutlu bir dizi parametrelerini olusturduk
                pixels = new int[width][height];
                for (int col = 0; col < width; col++) {
                    for (int row = 0; row < height; row++) {
                        pixels[col][row] = (int)((0.30* graypixels[col][row][0]) + (0.59 *graypixels[col][row][1]) + (0.11 * graypixels[col][row][2]));
                        //Metoda gönderdiğimiz 3 boyutlu dizinin pixel değerlerini grayscale methodunu uygularak değiştirdik.
                    }
                }


            }


            public void convertBinary(int width, int height, int[][][] binarypixels) { // Metodun uygulanması için width,height ve 3 boyutlu bir dizi parametrelerini olusturduk
                pixels = new int[width][height]; // pixels arrayine gelen yükseklik ve genişliği atadık.
                int threshold = 127;    // Pixellerin siyah ve beyaz noktalarının değişim değeri için bir eşik değeri belirledik.
                for (int col = 0; col < width; col++) {
                    for (int row = 0; row < height; row++) {
                        int value = (binarypixels[col][row][0] + binarypixels[col][row][1] + binarypixels[col][row][2]) / 3;
                        // Methoda gönderdiğimiz dizi renkli olduğundan dolayı önce onu 3 e bölerek siyah beyaz formatına dönüştürdük
                        //Ardından oluşan değerimiz eşik değerimizden büyük veya küçük olmasına göre siyah ve beyaz değerlerini aldı.
                        if (value < threshold) {
                            pixels[col][row] = 0; // Siyah
                        } else {
                            pixels[col][row] = 255;// Beyaz
                        }
                    }
                }

            }
        }


        ConvertImages grayscale = new ConvertImages();
        grayscale.convertGrayScale(width,height,Imagepixels);
        int [][] GrayScalePxl = grayscale.pixels;
        // ConvertImages sınıfından grayscale objesi oluşturduk.
        //Oluşturduğumuz objeden o sınıfa ait convertGrayscale methoduna yükseklik,genişlik ve renkli resim dizimizi gönderdik.
        //Bu methoddan oluşan değeri 2 boyutlu pixelsForGrayScale değerine eşitledik.

        ConvertImages binary = new ConvertImages();
        binary.convertBinary(width,height,Imagepixels);
        int [][] BinaryPxl =binary.pixels;
        // ConvertImages sınıfından binary objesi oluşturduk.
        //Oluşturduğumuz objeden o sınıfa ait convertBinary methoduna yükseklik,genişlik ve renkli resim dizimizi gönderdik.
        //Bu methoddan oluşan değeri 2 boyutlu pixelsForBinary değerine eşitledik.


        //BinaryPxlde elde ettiğimiz iki boyutlu arrayi pixelsForVerticalProjection dizisine eşitledik.
        // BinaryPxl dizimizin içeriği siyah beyaz olduğudan dolayı bunu tercih ettik.
        int[][] pixelsForVerticalProjection=BinaryPxl;
        //DrawProjection sınıfından Vertical objesi oluşturduk.
        DrawProjection Vertical = new  DrawProjection();
        //Oluşturduğumuz objeden o sınıfa ait createVerticalProjection methoduna yükseklik,genişlik ve pixelsForVerticalProjection  dizimizi gönderdik.
        Vertical.createVerticalProjection(height,width,pixelsForVerticalProjection);
        int [] pixelsForVertical =Vertical.pixels;
        //Bu methoddan gelen değeri tek boyutlu pixelsForVertical değerine eşitledik.



        //BinaryPxlde elde ettiğimiz iki boyutlu arrayi pixelsForHorizontalProjection dizisine eşitledik.
        // BinaryPxl dizimizin içeriği siyah beyaz olduğudan dolayı bunu tercih ettik.
        int[][] pixelsForHorizontalProjection=BinaryPxl;
        //DrawProjection sınıfından Horizontal objesi oluşturduk.
        DrawProjection Horizontal = new DrawProjection();
        //Oluşturduğumuz objeden o sınıfa ait createHorizontalProjection methoduna yükseklik,genişlik ve pixelsForHorizontalProjection  dizimizi gönderdik.
        Horizontal.createHorizontalProjection(height,width,pixelsForHorizontalProjection);
        int [] pixelsForHorizontal = Horizontal.pixels;
        //Bu methoddan gelen değeri tek boyutlu pixelsForHorizontal değerine eşitledik.



        class OriginalImage extends JPanel {
            int width, height;
            int[][][] pixels;

            public OriginalImage(int width, int height, int[][][] pixel) {
                // OriginalImage constructorı oluşturup methoda gelen değerleri değişkenlerimize eşitledik.
                this.width = width;
                this.height = height;
                this.pixels = pixel;

            }

            public void paintComponent(Graphics g) {
                for (int row = 0; row < height; row++)      // Dosyanın yüksekliği baz alarak tüm satırları okuduk
                    for (int col = 0; col < width; col++) { // Dosyanın genişliği baz alarak tüm sütunları okuduk.

                        g.setColor(new Color(
                                pixels[col][row][0],  // Okunan dosyanın pixellerini tek tek gezerek g.setColor özelliği ile bastırdık.
                                pixels[col][row][1],
                                pixels[col][row][2]
                        ));
                        g.fillRect(col, row, 1, 1);

                    }
            }
        }


        class DrawConvertedImages extends JPanel {
            int width, height;
            int[][] pixels;

            public DrawConvertedImages(int width, int height, int[][] pixel) {
                // DrawConvertedImages constructorı oluşturup methoda gelen değerleri değişkenlerimize eşitledik.
                this.width = width;
                this.height = height;
                this.pixels = pixel;

            }

            public void paintComponent(Graphics g) {
                for (int row = 0; row < height; row++)      //Dosyanın yüksekliği ve genişliğini boyunca tüm satırları okuduk
                    for (int col = 0; col < width; col++) {
                        // Okunan dosyanın pixellerini col row belirleyerek g.setColor özelliği ile bastırdık.
                        g.setColor(new Color(pixels[col][row], pixels[col][row], pixels[col][row]));
                        g.fillRect(col, row, 1, 1);
                    }
            }
        }


        class ShowHistogram  extends JPanel {
            int size;   // Dosyamızın genişliği
            int[] pixels; //Pixelleri tutacağımız dizi

            public ShowHistogram(int size, int[] pixel) {
                // DrawConvertedImages constructorı oluşturup methoda gelen değerleri değişkenlerimize eşitledik.
                this.size = size;
                this.pixels = pixel;
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < size; i++) {
                    //Graphics e ait drawLine özelliğini kullanarak elde ettiğimiz histogramı bastırdık.
                    g.drawLine(i, 300, i, 300 - pixels[i]);
                }

            }
        }


        JFrame frame = new JFrame("AdvanceProgramming");
        JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);

        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 1));
        frame.add(tp);// tp değişkenine atadığımız değerleri  oluşturduğumuz frame objesine ekledik

        OriginalImage original = new OriginalImage(width, height, Imagepixels);
        //OriginalImage sınıfında bulunan metoda çalışması için yükseklik,genişlik ve 3 boyutlu dizi değerlerini gönderdik.
        DrawConvertedImages grayscaleimg = new DrawConvertedImages(width, height,GrayScalePxl);
        DrawConvertedImages binaryimg = new DrawConvertedImages(width, height, BinaryPxl);
        //DrawConvertedImages sınıfında bulunan metoda çalışması için yükseklik,genişlik ve 2 boyutlu dizi değerlerini gönderdik.
        ShowHistogram vertical = new ShowHistogram(width, pixelsForVertical);
        //ShowHistogram sınıfında bulunan metoda çalışması için genişlik ve tek boyutlu dizi değerlerini gönderdik.
        ShowHistogram horizontal = new ShowHistogram(height, pixelsForHorizontal);
        //ShowHistogram sınıfında bulunan metoda çalışması için yükseklik ve tek boyutlu dizi değerlerini gönderdik.



        tp.add("Original Image", original);//original objesi ile ulaştığımız çıktıyı TabbedPane in OriginalImage sekmesine bastırdık.
        tp.add("Grayscale Image", grayscaleimg);//grayscaleimg objesi ile ulaştığımız çıktıyı TabbedPane in GrayScale Image sekmesine bastırdık.
        tp.add("Binary Image", binaryimg);//binaryimg objesi ile ulaştığımız çıktıyı TabbedPane in Binary Image sekmesine bastırdık.
        tp.add("Vertical Projection", vertical);//vertical objesi ile ulaştığımız çıktıyı TabbedPane in Vertical Projection sekmesine bastırdık.
        tp.add("Horizontal Projection",horizontal);//horizontal objesi ile ulaştığımız çıktıyı TabbedPane in Horizontal Projection sekmesine bastırdık.



    }
}

