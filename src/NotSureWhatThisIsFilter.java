public class NotSureWhatThisIsFilter implements PixelFilter {
    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);

        for (int i = 0; i < pixels.length; i++) {
            bwpixels[i] = (short) PixelLib.color(255, 10, 10);
        }

        PixelLib.fill1dArray(bwpixels, pixels);
        return pixels;
    }
}
