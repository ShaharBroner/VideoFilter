public class MotionBlurFilter implements PixelFilter {
    final static int blurR = 30;

    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);
        short[][] img = PixelLib.convertTo2dArray(bwpixels, width, height);

        for (int r = 0; r < img.length; r++) {
            for (int c = 0; c < img[r].length - blurR; c++) {
                short newVal = 0;
                for (int i = c; i < c + blurR; i++) {
                    newVal += img[r][i];
                }
                newVal /= blurR;
                img[r][c] = newVal;
            }
        }
        PixelLib.fill1dArray(img, pixels);
        return pixels;
    }
}
