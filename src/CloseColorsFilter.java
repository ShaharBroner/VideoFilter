import javax.swing.*;

public class CloseColorsFilter implements PixelFilter {
    int r, g, b;
    final static int Threshold = 100;

    public CloseColorsFilter() {
        int threshold = Integer.parseInt(JOptionPane.showInputDialog("Choose a red"));
        this.r = threshold;
        threshold = Integer.parseInt(JOptionPane.showInputDialog("Choose a green"));
        this.g = threshold;
        threshold = Integer.parseInt(JOptionPane.showInputDialog("Choose a blue"));
        this.b = threshold;
    }


    public int[] filter(int[] pixels, int width, int height) {
        PixelLib.ColorComponents2d vals = PixelLib.getColorComponents2d(pixels, width, height);
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int red = vals.red[r][c];
                int green = vals.green[r][c];
                int blue = vals.blue[r][c];
                double distance = Math.sqrt(((r - red) * (r - red)) + ((b - blue) * (b - blue)) + (g - green) * (g - green));
                if (distance < Threshold) {
                    vals.red[r][c] = 255;
                    vals.green[r][c] = 255;
                    vals.blue[r][c] = 255;
                } else {
                    vals.red[r][c] = 0;
                    vals.green[r][c] = 0;
                    vals.blue[r][c] = 0;
                }

            }
        }
        pixels = PixelLib.combineColorComponents(vals);
        return pixels;
    }
}