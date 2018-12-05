public class FourWayFilter implements PixelFilter {
    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);
        short[][] img = PixelLib.convertTo2dArray(bwpixels, width, height);

        int lastRowIndex = img.length - 1;
        for (int r = 0; r < img.length / 2; r++) {
            for (int c = 0; c < img[r].length / 2; c++) {
                short tmp = img[r][c];
                img[img.length - 1 - r][c] = tmp;
                img[r][img[0].length - 1 - c] = tmp;
                img[img.length - 1 - r][img[0].length - 1 - c] = tmp;
            }
        }
        PixelLib.fill1dArray(img, pixels);
        return pixels;
    }
}
