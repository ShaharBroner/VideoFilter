import java.util.ArrayList;

public class Cluster {
    private ArrayList<Point> cluster;
    private Point center;
    private Point prevCenter;
    private int r = (int) (Math.random() * 256);
    private int g = (int) (Math.random() * 256);
    private int b = (int) (Math.random() * 256);

    public Cluster() {
        cluster = new ArrayList<>();
        center = new Point(0, 0);
        prevCenter = new Point(0, 0);
    }

    public void addPoint(Point p) {
        cluster.add(p);
    }

    public ArrayList<Point> getPoints() {
        return cluster;
    }

    public void clearPoint() {
        cluster.removeAll(cluster);
    }

    public Point getCenter() {
        return center;
    }

    public void reCalculateCenter() {
        prevCenter = getCenter();
        int xCount = 0;
        int yCount = 0;
        System.out.println(cluster.size());
        for (int i = 0; i < cluster.size(); i++) {
            Point p = cluster.get(i);
            xCount += p.getX();
            yCount += p.getY();
        }
        Point p = new Point(xCount / cluster.size(), yCount / cluster.size());
        center = p;
    }

    public boolean didCenterChange() {
        if (prevCenter.equals(center)) {
            return false;
        }
        return true;
    }

    public String ToString() {
        String a = "";
        for (int i = 0; i < cluster.size(); i++) {
            a += "(" + cluster.get(i).getX() + ", " + cluster.get(i).getY() + "), ";
        }
        return a;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
