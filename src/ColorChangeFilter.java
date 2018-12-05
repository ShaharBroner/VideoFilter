import java.util.logging.Filter;

public class ColorChangeFilter implements PixelFilter {
    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);
        for (int i = 0; i < pixels.length; i++) {
            bwpixels[i] = (short) (255 - bwpixels[i]);
        }
        PixelLib.fill1dArray(bwpixels, pixels);
        return pixels;
    }
}
