//YuYang Liu 300299216 and Wajeeh Syed 300299214

public class SimilaritySearch {
    private ColourImage queryImage;
    private ColourHistogram queryHistogram;
    private ArrayList<ColourHistogram> dataset = new ArrayList<>();
    private PriorityQueue<Pair<Double, ColourHistogram>> intersections;
    private int depth;
    private Boolean error = false;

    public SimilaritySearch(String filename, String directory, int depth) {
        this.depth = depth;
        File dir = new File(directory);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File image : directoryListing) {
                if (image.getName().contains("txt")) {
                    // Do something
                } else {
                    // Do something else
                }
                try {
                    String fullName = directory + "/" + image.getName();
                    ColourHistogram histogram = new ColourHistogram(fullName);
                    histogram.normalize();
                    dataset.add(histogram);
                } catch (Exception e) {
                    System.out.println("Unable to create histogram for " + image.getName());
                    System.out.println("Invalid Directory.");
                    error = true;
                }
            }
            try {
                queryImage = new ColourImage(filename);
                queryImage.reduceColour(depth);
            } catch (Exception e) {
                System.out.println("Unable to read query image.");
                error = true;
            }
        }
    }

    public void search() {
        if (!error) {
            queryHistogram = new ColourHistogram(queryImage, depth);
            queryHistogram.normalize();
        }

        if (!error) {
            intersections = new PriorityQueue<>(6, new PairComparator());
            Iterator<ColourHistogram> iter = dataset.iterator();

            while (iter.hasNext()) {
                ColourHistogram histogram = iter.next();
                double intersection = queryHistogram.compare(histogram);
                Pair<Double, ColourHistogram> entry = new Pair<>(intersection, histogram);
                intersections.add(entry);

                if (intersections.size() == 6) {
                    intersections.poll();
                }
            }

            Iterator<Pair<Double, ColourHistogram>> iter2 = intersections.iterator();

            while (iter2.hasNext()) {
                Pair<Double, ColourHistogram> current = iter2.next();
                ColourHistogram histogram = current.getValue();
                System.out.println(current.getKey() + "" + histogram.getFilename());
            }
        }
    }

    public ColourHistogram getHistogram() {
        return queryHistogram;
    }

    public ColourImage getImage() {
        return queryImage;
    }
}
