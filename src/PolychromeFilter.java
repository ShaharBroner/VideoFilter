import javax.swing.*;

public class PolychromeFilter implements PixelFilter {
    int divider;

    public PolychromeFilter() {
        int threshold = Integer.parseInt(JOptionPane.showInputDialog("Choose a divider"));
        this.divider = threshold;
    }

    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);
        short[][] img = PixelLib.convertTo2dArray(bwpixels, width, height);
        int size = 255 / divider;
        for (int r = 0; r < img.length; r++) {
            for (int c = 0; c < img[r].length; c++) {
                int interval = img[r][c] / size;
                interval = Math.min(interval, divider - 1);
                img[r][c] = (short) ((size * interval) + ((size + 1) * interval) / 2);
            }
        }
        PixelLib.fill1dArray(img, pixels);
        return pixels;
    }
}
