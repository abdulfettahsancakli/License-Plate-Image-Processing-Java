package com.company;

public class DrawProjection {

    public int[] pixels =null;

    public void createVerticalProjection(int height, int width, int[][] originalPixel) {
        //// Metodun uygulanması için width,height ve 2 boyutlu dizi parametrelerini olusturduk
        pixels = new int[width]; //Dosyanın üzerinde sütun sütun okuyacağımız için dosyanın genişliği kadar okumamız gereken width değerini pixels dizisine atadık.
        int[][] pixelsTmp = new int[height][width];
        //0 ve 1 değerlerini belirledikten sonra değerleri tutmak için oluşturduğumuz dizi
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                //Yükseklik ve genişlik boyunca okuduğumuz dosyada siyah pixeller ile karşılaştığında
                // PixelsTmp in değerlerini 1 olarak belirledik.
                if (originalPixel[row][col] == 0) {
                    pixelsTmp[col][row] = 1;
                }
                // Convert white spots to zeros
                //Yükseklik ve genişlik boyunca okuduğumuz dosyada beyaz pixeller ile karşılaştığında
                // PixelsTmp in değerlerini 0 olarak belirledik.
                else if (originalPixel[row][col] == 255) {
                    pixelsTmp[col][row] = 0;
                }
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //Sütun değerlerini alarak oluşturacağımız histogram için her sütun için satır değerlerini okuyarak,
                // oluşturduğumuz tek boyutlu diziye değerleri atadık.
                pixels[i] += pixelsTmp[j][i];
            }
        }
    }



    public void createHorizontalProjection(int height, int width, int[][] originalPixel) {
        //Metodun uygulanması için width,height ve 2 boyutlu dizi parametrelerini olusturduk
        pixels = new int[height]; // Dosyanın üzerinde sütun sütun okuyacağımız için dosyanın genişliği kadar okumamız gereken width değerini pixels dizisine atadık.
        int[][] pixelsTmp = new int[height][width];
        ////0 ve 1 değerlerini belirledikten sonra değerleri tutmak için oluşturduğumuz dizi
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                //Yükseklik ve genişlik boyunca okuduğumuz dosyada siyah pixeller ile karşılaştığında
                // PixelsTmp in değerlerini 1 olarak belirledik.
                if (originalPixel[row][col] == 0) {
                    pixelsTmp[col][row] = 1;
                }
                // Convert white spots to zeros
                //Yükseklik ve genişlik boyunca okuduğumuz dosyada beyaz pixeller ile karşılaştığında
                // PixelsTmp in değerlerini 0 olarak belirledik.
                else if (originalPixel[row][col] == 255) {
                    pixelsTmp[col][row] = 0;
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //Satırdaki değerleri alarak oluşturacağımız histogram için her satır için sütun değerlerini okuyarak,
                // oluşturduğumuz tek boyutlu diziye değerleri atadık.
                pixels[i] += pixelsTmp[i][j];
            }
        }


    }



}
