import javax.swing.*;

public class ConvolutionFilter implements PixelFilter {
    int[][] kernal = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
    int[][] kernal2 = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
    short threshold;

    public ConvolutionFilter() {
        short threshold = Short.parseShort(JOptionPane.showInputDialog("Choose a threshold"));
        this.threshold = threshold;
    }

    @Override
    public int[] filter(int[] pixels, int width, int height) {
        short[] bwpixels = PixelLib.convertToShortGreyscale(pixels);
        short[][] img = PixelLib.convertTo2dArray(bwpixels, width, height);
        short[][] output = new short[height][width];
        short outp;
        short outp2;
        short sum = calculateWeight(kernal);
        short sum2 = calculateWeight(kernal2);
        for (int r = 0; r < img.length - (kernal.length - 1); r++) {
            for (int c = 0; c < img[r].length - (kernal[0].length - 1); c++) {
                outp = 0;
                outp2 = 0;
                for (int i = r; i < r + kernal.length; i++) {
                    for (int j = c; j < c + kernal[0].length; j++) {
                        outp += img[i][j] * kernal[i - r][j - c];
                        outp2 += img[i][j] * kernal2[i - r][j - c];
                    }
                }
                if (sum != 0) {
                    outp /= sum;
                }
                if (sum2 != 0) {
                    outp2 /= sum2;
                }
                if (outp < 0) {
                    outp = 0;
                }
                if (outp2 < 0) {
                    outp2 = 0;
                }
                short finalOut = (short) Math.sqrt((outp * outp) + (outp2 * outp2));
                if (finalOut < threshold) {
                    finalOut = 0;
                } else {
                    finalOut = 255;
                }
                output[r + (kernal.length / 2)][c + (kernal[0].length / 2)] = finalOut;
            }
        }
        thin(output);
        PixelLib.fill1dArray(output, pixels);
        return pixels;
    }

    public short calculateWeight(int[][] kernal) {
        short sum = 0;
        for (int i = 0; i < kernal.length; i++) {
            for (int j = 0; j < kernal[0].length; j++) {
                sum += kernal[i][j];
            }
        }
        return sum;
    }

    public short[][] thin(short[][] output) {
        int[][] kernal = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] kernal2 = {{0, 0, 0}, {1, 1, 0}, {0, 1, 0}};
        for (int i = 0; i < 4; i++) {
            output = runOneKernal(output, kernal);
            output = runOneKernal(output, kernal2);
            kernal = rotateArray(kernal);
            kernal2 = rotateArray(kernal2);
        }
        return output;
    }

    private short[][] runOneKernal(short[][] output, int[][] kernal) {
        short sum = calculateWeight(kernal);
        for (int r = 0; r < output.length - (kernal.length - 1); r++) {
            for (int c = 0; c < output[r].length - (kernal[0].length - 1); c++) {
                short outp = 0;
                for (int i = r; i < r + kernal.length; i++) {
                    for (int j = c; j < c + kernal[0].length; j++) {
                        outp += output[i][j] * kernal[i - r][j - c];
                    }
                }
                if (sum != 0) {
                    outp /= sum;
                }
                if (outp < 0) {
                    outp = 0;
                }
                output[r + (kernal.length / 2)][c + (kernal[0].length / 2)] = outp;
            }
        }
        return output;
    }

    public static int[][] rotateArray(int[][] arr) {
        int[][] newArr = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = arr.length - 1; j >= 0; j--) {
                newArr[i][j] = arr[j][i];
            }
        }
        return arr;
    }
}