import javax.swing.*;

public class MonochromeFilter implements PixelFilter {
    int threshold;

    public MonochromeFilter() {
        int threshold = Integer.parseInt(JOptionPane.showInputDialog("Choose a threshold from 0 to 255"));
        this.threshold = threshold;
    }

    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);
        short[][] img = PixelLib.convertTo2dArray(bwpixels, width, height);

        for (int r = 0; r < img.length; r++) {
            for (int c = 0; c < img[r].length; c++) {
                if (img[r][c] < threshold) {
                    img[r][c] = 0;
                } else {
                    img[r][c] = 255;
                }
            }
        }
        PixelLib.fill1dArray(img, pixels);
        return pixels;
    }
}
