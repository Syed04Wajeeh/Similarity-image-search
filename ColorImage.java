//YuYang Liu 300299216 and Wajeeh Syed 300299214

import java.awt.image.BufferedImage;

public class ColorImage {
    private String filePath;
    private ColorHistogram histogram;
    
    int width;
    int height;
    int depth;

	public ColorImage(String filename) {
        this.filePath = filename;
        this.histogram = new ColorHistogram();
    }

    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

    public ColorHistogram getHistogram() {
        return histogram;
    }


    public int[] getPixel(int i, int j) {
        return null;
    }

    public void reduceColor(int d) {
	}

}
