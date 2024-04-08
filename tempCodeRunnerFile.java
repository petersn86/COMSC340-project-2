    public void merge(File outFile, File leftFile, File rightFile, int h, int m) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        BufferedReader leftReader = new BufferedReader(new FileReader(leftFile));
        BufferedReader rightReader = new BufferedReader(new FileReader(rightFile));

        String leftLine = leftReader.readLine();
        String rightLine = rightReader.readLine();

        while (leftLine != null && rightLine != null) {
            if (leftLine.compareTo(rightLine) <= 0) {
                    writer.write(leftLine);
                    writer.newLine();
                    leftLine = leftReader.readLine();
            } 
            else {
                    writer.write(rightLine);
                    writer.newLine();
                    rightLine = rightReader.readLine();
            }
        }

        // Write remaining lines from leftFile
        while (leftLine != null) {
            writer.write(leftLine);
            writer.newLine();
            leftLine = leftReader.readLine();
        }

        // Write remaining lines from rightFile
        while (rightLine != null) {
            writer.write(rightLine);
            writer.newLine();
            rightLine = rightReader.readLine();
        }

        writer.close();
        leftReader.close();
        rightReader.close();
    }