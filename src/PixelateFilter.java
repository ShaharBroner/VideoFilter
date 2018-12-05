import javax.swing.*;

public class PixelateFilter implements PixelFilter {
    int blockSize;

    public PixelateFilter() {
        int blockSize = Integer.parseInt(JOptionPane.showInputDialog("Type a block size"));
        this.blockSize = blockSize;
    }

    int radius = (blockSize - 1) / 2;

    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);
        short[][] img = PixelLib.convertTo2dArray(bwpixels, width, height);

        for (int r = 0; r < img.length - blockSize; r += blockSize) {
            for (int c = 0; c < img[r].length - blockSize; c += blockSize) {
                short newVal = img[r + radius][c + radius];
                for (int i = r; i < r + blockSize; i++) {
                    for (int j = c; j < c + blockSize; j++) {
                        img[i][j] = newVal;
                    }
                }
            }
        }
        PixelLib.fill1dArray(img, pixels);
        return pixels;
    }
}
