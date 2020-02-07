import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import static javax.swing.JOptionPane.YES_OPTION;

public class Main {

    private static JFrame frame = new JFrame();
    private static JLabel label = new JLabel();
    private static BufferedImage image = new BufferedImage(601, 601, BufferedImage.TYPE_INT_RGB);
    private static ImageIcon icon = new ImageIcon();
    private static JMenuBar mainMenu;
    private static JMenu menu, menu1, menu2, menu3;
    private static JMenuItem open, save, exit, bT, bS, bR, scl, rot, open2, save2, exit2, bT3D, bS3D, rotX, rotY, rotZ;
    private static JTextField ta;
    private static int rgb1 = Color.GREEN.getRGB();
    private static int rgb2 = Color.CYAN.getRGB();
    private static int rgb3 = Color.MAGENTA.getRGB();
    private static int rgb4 = Color.YELLOW.getRGB();
    private static int rgb5 = Color.WHITE.getRGB();
    private static double[][] datalines = new double[5000][4];
    private static double[][] datalines3D = new double[5000][6];
    private static double[][] datalines3DPP = new double[5000][6];
    private static int num = 0;
    private static String filename;
    private static final double D = 60, S = 15;
    private static final double Vsx = 300, Vsy = 300, Vcx = 300, Vcy = 300;
    private static double[][] N = {{(D/S), 0, 0, 0}, {0, (D/S), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
    private static double[] origin = new double[3];

    private static void bresenhams(int x0, int y0, int x1, int y1, int rgb) {
        int dy = y1 - y0;
        int dx = x1 - x0;
        int y = y0;
        int x = x0;

        if (dx == 0) { //Vertical Line
            if (dy > 0) { //Positive Vertical Line
                for (int i = 0; i <= (dy - 1); i++) {
                    setPixel(x, y, rgb);
                    y++;
                } //for
            } //if
            else { //Negative Vertical Line
                for (int i = 0; i <= ((-dy) - 1); i++) {
                    setPixel(x, y, rgb);
                    y--;
                } //for
            } //else
        } //if
        else if (dy == 0) { //Horizontal Line
            if (dx > 0) { //Positive Horizontal Line
                for (int i = 0; i <= (dx - 1); i++) {
                    setPixel(x, y, rgb);
                    x++;
                } //for
            } //if
            else { //Negative Horizontal Line
                for (int i = 0; i <= ((-dx) - 1); i++) {
                    setPixel(x, y, rgb);
                    x--;
                } //for
            } //else
        } //else-if
        else if (dx == dy) { //Diagonal Line
            if (dx > 0) { //Positive Diagonal Line
                for (int i = 0; i <= (dx - 1); i++) {
                    setPixel(x, y, rgb);
                    x++;
                    y++;
                } //for
            } //if
            else { //Negative Diagonal Line
                for (int i = 0; i <= ((-dx) - 1); i++) {
                    setPixel(x, y, rgb);
                    x--;
                    y--;
                } //for
            } //else
        } //else-if
        else if (Math.abs(dx) > Math.abs(dy)) { //Dx > Dy Line
            if ((dx > 0) & (dy > 0)) { //Positive Line
                int E = 2 * dy - dx;
                int inc1 = 2 * dy;
                int inc2 = 2 * (dy - dx);

                while (x < x1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y + 1;
                    } //else
                    x = x + 1;
                } //while

            } //if
            else if ((dx > 0) & (dy < 0)) { //Negative dy Line
                int E = 2 * (-dy) - dx;
                int inc1 = 2 * (-dy);
                int inc2 = 2 * ((-dy) - dx);

                while (x < x1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y - 1;
                    } //else
                    x = x + 1;
                } //while

            } //else-if
            else if ((dx < 0) & (dy > 0)) { //Negative dx Line
                int E = 2 * dy + dx;
                int inc1 = 2 * dy;
                int inc2 = 2 * (dy + dx);

                while (x > x1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y + 1;
                    } //else
                    x = x - 1;
                } //while

            } //else-if
            else { //Negative dx and dy Line
                int E = -(2 * dy - dx);
                int inc1 = 2 * (-dy);
                int inc2 = 2 * (-(dy - dx));

                while (x > x1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y - 1;
                    } //else
                    x = x - 1;
                } //while

            } //else
        } //else-if
        else { //Dy > Dx Line
            if ((dx > 0) & (dy > 0)) { //Positive Line
                int E = 2 * dx - dy;
                int inc1 = 2 * dx;
                int inc2 = 2 * (dx - dy);

                while (y < y1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x + 1;
                    } //else
                    y = y + 1;
                } //while

            } //if
            else if ((dx > 0) & (dy < 0)) { //Negative dy Line
                int E = 2 * dx + dy;
                int inc1 = 2 * dx;
                int inc2 = 2 * (dx + dy);

                while (y > y1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x + 1;
                    } //else
                    y = y - 1;
                } //while

            } //else-if
            else if ((dx < 0) & (dy > 0)) { //Negative dx Line
                int E = 2 * (-dx) - dy;
                int inc1 = 2 * (-dx);
                int inc2 = 2 * ((-dx) - dy);

                while (y < y1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x - 1;
                    } //else
                    y = y + 1;
                } //while

            } //else-if
            else { //Negative dx and dy Line
                int E = -(2 * dx - dy);
                int inc1 = 2 * (-dx);
                int inc2 = 2 * (-(dx - dy));

                while (y > y1) {
                    setPixel(x, y, rgb);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x - 1;
                    } //else
                    y = y - 1;
                } //while

            } //else
        } //else
    } //bse

    private static void setPixel(int x, int y, int rgb) {
        image.setRGB(x, y, rgb);
        icon.setImage(image);
        label.setIcon(icon);
        frame.add(label);
    } //setPixel

    private static double[][] basicTranslate(double tx, double ty) {
        double[][] m = {{1, 0, 0}, {0, 1, 0}, {tx, ty, 1}};
        return m;
    } //basicTranslate

    private static double[][] basicTranslate3D(double tx, double ty, double tz) {
        double[][] m = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {tx, ty, tz, 1}};
        return m;
    } //basicTranslate3D

    private static double[][] basicScale(double sx, double sy) {
        double[][] m = {{sx, 0, 0}, {0, sy, 0}, {0, 0, 1}};
        return m;
    } //basicScale

    private static double[][] basicScale3D(double sx, double sy, double sz) {
        double[][] m = {{sx, 0, 0, 0}, {0, sy, 0, 0}, {0, 0, sz, 0}, {0, 0, 0, 1}};
        return m;
    } //basicScale3D

    private static double[][] basicRotate(int angle) {
        double cosine = Math.cos(Math.toRadians(angle));
        double sine = Math.sin(Math.toRadians(angle));
        double[][] m = {{cosine, (-sine), 0}, {sine, cosine, 0}, {0, 0, 1}};
        return m;
    } //basicRotate

    private static double[][] basicRotateX(int angle) {
        double cosine = Math.cos(Math.toRadians(angle));
        double sine = Math.sin(Math.toRadians(angle));
        double[][] m = {{1, 0, 0, 0}, {0, cosine, sine, 0}, {0, (-sine), cosine, 0}, {0, 0, 0, 1}};
        return m;
    } //basicRotateX

    private static double[][] basicRotateY(int angle) {
        double cosine = Math.cos(Math.toRadians(angle));
        double sine = Math.sin(Math.toRadians(angle));
        double[][] m = {{cosine, 0, (-sine), 0}, {0, 1, 0, 0}, {sine, 0, cosine, 0}, {0, 0, 0, 1}};
        return m;
    } //basicRotateY

    private static double[][] basicRotateZ(int angle) {
        double cosine = Math.cos(Math.toRadians(angle));
        double sine = Math.sin(Math.toRadians(angle));
        double[][] m = {{cosine, sine, 0, 0}, {(-sine), cosine, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return m;
    } //basicRotateZ

    private static double[][] scale(double sx, double sy, int cx, int cy) {
        double[][] m = concat(basicTranslate(Math.negateExact(cx), Math.negateExact(cy)), basicScale(sx, sy));
        return concat(m, basicTranslate(cx, cy));
    } //scale

    private static double[][] scale3D(double sx, double sy, double sz, int cx, int cy, int cz) {
        double[][] m = concat(concat(basicTranslate3D(Math.negateExact(cx), Math.negateExact(cy), Math.negateExact(cz)), basicScale3D(sx, sy, sz)), basicTranslate3D(cx, cy, cz));
        return m;
    } //scale3D

    private static double[][] rotate(int angle, int cx, int cy) {
        double[][] m = concat(basicTranslate(Math.negateExact(cx), Math.negateExact(cy)), basicRotate(angle));
        return concat(m, basicTranslate(cx, cy));
    } //rotate

    private static void applyTransformation(double[][] matrix, double[][] datalines) {
        double[] x = new double[3];
        double[] x2 = new double[3];
        for (int i = 0; i < num; i++) {
            x[0] = datalines[i][0];
            x[1] = datalines[i][1];
            x[2] = 1;
            x2[0] = datalines[i][2];
            x2[1] = datalines[i][3];
            x2[2] = 1;
            x = dotProduct(x, matrix);
            x2 = dotProduct(x2, matrix);
            datalines[i][0] = x[0];
            datalines[i][1] = x[1];
            datalines[i][2] = x2[0];
            datalines[i][3] = x2[1];
        } //for
    } //applyTransformation

    private static void apply3DTransformation(double[][] matrix, double[][] datalines3D) {
        double[] x = new double[4];
        double[] x2 = new double[4];
        for (int i = 0; i < num; i++) {
            x[0] = datalines3D[i][0];
            x[1] = datalines3D[i][1];
            x[2] = datalines3D[i][2];
            x[3] = 1;
            x2[0] = datalines3D[i][3];
            x2[1] = datalines3D[i][4];
            x2[2] = datalines3D[i][5];
            x2[3] = 1;
            x = dotProduct(x, matrix);
            x2 = dotProduct(x2, matrix);
            datalines3D[i][0] = x[0];
            datalines3D[i][1] = x[1];
            datalines3D[i][2] = x[2];
            datalines3D[i][3] = x2[0];
            datalines3D[i][4] = x2[1];
            datalines3D[i][5] = x2[2];
        } //for
    } //apply3DTransformation

    private static void convert3Dto2D(double[][] datalines3D, double[][] datalines) {
        //Take each point in datalines3D and turn them from xyz -> xy and place in datalines
        for (int i = 0; i < num; i++) {
            double x0 = datalines3D[i][0];
            double y0 = datalines3D[i][1];
            double z0 = datalines3D[i][2];
            datalines[i][0] = ((x0/z0) * Vsx + Vcx);
            datalines[i][1] = ((y0/z0) * Vsy + Vcy);
            double x1 = datalines3D[i][3];
            double y1 = datalines3D[i][4];
            double z1 = datalines3D[i][5];
            datalines[i][2] = ((x1/z1) * Vsx + Vcx);
            datalines[i][3] = ((y1/z1) * Vsy + Vcy);
        } //for
    } //convert2Dto3D

    private static double[][] computeV(double x, double y, double z) {
        double[][] V;
        double[][] t1 = basicTranslate3D((-x), (-y), (-z));
        double[][] t2 = {{1, 0, 0, 0}, {0, 0, -1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}};
        double sqrt = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double[][] t3 = {{(-(y/sqrt)), 0, (x/sqrt), 0}, {0, 1, 0, 0}, {(-(x/sqrt)), 0, (-(y/sqrt)), 0}, {0, 0, 0, 1}};
        double sqrt2 = Math.sqrt(Math.pow(z, 2) + Math.pow(sqrt, 2));
        double[][] t4 = {{1, 0, 0, 0}, {0, (sqrt/sqrt2), (z/sqrt2), 0}, {0, (-(z/sqrt2)), (sqrt/sqrt2), 0}, {0, 0, 0, 1}};
        double[][] t5 = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, -1, 0}, {0, 0, 0, 1}};
        V = concat(concat(concat(t1, t2), concat(t3, t4)), t5);
        return V;
    } //computeV

    private static void perspectiveProjection(double[][] datalines3D, double[][] datalines3DPP) {
        //Turn points into perspective projection
        double[] m = new double[4];
        double[] m2 = new double[4];
        double[][] V = computeV(origin[0], origin[1], origin[2]);
        double[][] VN = concat(V, N);
        for (int i = 0; i < num; i++) {
            m[0] = datalines3D[i][0];
            m[1] = datalines3D[i][1];
            m[2] = datalines3D[i][2];
            m[3] = 1;
            m = dotProduct(m, VN);
            datalines3DPP[i][0] = m[0];
            datalines3DPP[i][1] = m[1];
            datalines3DPP[i][2] = m[2];
            m2[0] = datalines3D[i][3];
            m2[1] = datalines3D[i][4];
            m2[2] = datalines3D[i][5];
            m2[3] = 1;
            m2 = dotProduct(m2, VN);
            datalines3DPP[i][3] = m2[0];
            datalines3DPP[i][4] = m2[1];
            datalines3DPP[i][5] = m2[2];
        } //for
    } //perspectiveProjection

    private static void displayPixels(double[][] datalines, int num) {
        String s = "Coordinates: ";
        for (int i = 0; i < num; i++) {
            int x0 = (int)datalines[i][0]+300;
            int y0 = (int)datalines[i][1]+300;
            int x1 = (int)datalines[i][2]+300;
            int y1 = (int)datalines[i][3]+300;
            bresenhams(x0, y0, x1, y1, rgb1);
            s += ("(" + (x0-300) + ", " + (y0-300) + ") " + "(" + (x1-300) + ", " + (y1-300) + ") ");
            if (i != num-1) {
                s += "-> ";
            } //if
        } //for
        ta.setText(s);
        frame.setVisible(true);
    } //displayPixels

    private static void display3DPixels(double[][] datalines, int count, int rgb) {
        String s = "Coordinates: ";
        for (int i = 0; i < count; i++) {
            int x0 = (int)datalines[i][0];
            int y0 = (int)datalines[i][1];
            int x1 = (int)datalines[i][2];
            int y1 = (int)datalines[i][3];
            boolean visible = lineClip(x0, y0, x1, y1);
            if (visible) {
                bresenhams(x0, y0, x1, y1, rgb);
                s += ("(" + x0 + ", " + y0 + ") " + "(" + x1 + ", " + y1 + ") ");
                if (i != count - 1) {
                    s += "-> ";
                } //if
            } //if
            else {
                if (count < num) {
                    count = num;
                } //if
            } //else
        } //for
        ta.setText(s);
        frame.setVisible(true);
    } //display3DPixels

    private static void display3D(double[][] datalines3DPP, double[][] datalines, int rgb) {
        convert3Dto2D(datalines3DPP, datalines);
        display3DPixels(datalines, num, rgb);
    } //display3D

    private static boolean lineClip(int x0, int y0, int x1, int y1) {
        boolean visible;
        int c = getC(x0, y0);
        int c2 = getC(x1, y1);
        if ((c | c2) == 0) {
            visible = true;
        } //if
        else if ((c & c2) != 0) {
            visible = false;
        } //else-if
        else {
            //Split the line where it meets the viewpoint then add both new lines to datalines
            double[] line;
            double[] line2;
            if (c != 0) {
                if ((c == 1) | (c == 5) | (c == 9)) {
                    if (c == 1) {
                        line = new double[]{x0, y0, 0, y1};
                        line2 = new double[]{0, y0, x1, y1};
                    } //if
                    else if (c == 5) {
                        line = new double[]{x0, y0, 0, 0};
                        line2 = new double[]{0, 0, x1, y1};
                    } //if
                    else {
                        line = new double[]{x0, y0, 0, 600};
                        line2 = new double[]{0, 600, x1, y1};
                    } //else
                } //if
                else if ((c == 2) | (c == 6) | (c == 10)) {
                    if (c == 2) {
                        line = new double[]{x0, y0, 600, y1};
                        line2 = new double[]{600, y0, x1, y1};
                    } //if
                    else if (c == 6) {
                        line = new double[]{x0, y0, 600, 0};
                        line2 = new double[]{600, 0, x1, y1};
                    } //if
                    else {
                        line = new double[]{x0, y0, 600, 600};
                        line2 = new double[]{600, 600, x1, y1};
                    } //else
                } //else-if
                else {
                    if (c == 4) {
                        line = new double[]{x0, y0, x1, 0};
                        line2 = new double[]{x0, 0, x1, y1};
                    } //if
                    else {
                        line = new double[]{x0, y0, x1, 600};
                        line2 = new double[]{x0, 600, x1, y1};
                    } //else
                } //else
            } //if
            else {
                if ((c2 == 1) | (c2 == 5) | (c2 == 9)) {
                    if (c2 == 1) {
                        line = new double[]{x0, y0, 0, y1};
                        line2 = new double[]{0, y0, x1, y1};
                    } //if
                    else if (c2 == 5) {
                        line = new double[]{x0, y0, 0, 0};
                        line2 = new double[]{0, 0, x1, y1};
                    } //if
                    else {
                        line = new double[]{x0, y0, 0, 600};
                        line2 = new double[]{0, 600, x1, y1};
                    } //else
                } //if
                else if ((c2 == 2) | (c2 == 6) | (c2 == 10)) {
                    if (c2 == 2) {
                        line = new double[]{x0, y0, 600, y1};
                        line2 = new double[]{600, y0, x1, y1};
                    } //if
                    else if (c2 == 6) {
                        line = new double[]{x0, y0, 600, 0};
                        line2 = new double[]{600, 0, x1, y1};
                    } //if
                    else {
                        line = new double[]{x0, y0, 600, 600};
                        line2 = new double[]{600, 600, x1, y1};
                    } //else
                } //else-if
                else {
                    if (c2 == 4) {
                        line = new double[]{x0, y0, x1, 0};
                        line2 = new double[]{x0, 0, x1, y1};
                    } //if
                    else {
                        line = new double[]{x0, y0, x1, 600};
                        line2 = new double[]{x0, 600, x1, y1};
                    } //else
                } //else
            } //else
            datalines[num] = line;
            num++;
            datalines[num] = line2;
            num++;
            visible = false;
        } //else
        return visible;
    } //lineClip

    private static int getC(int x, int y) {
        int c = 0;
        if (x < 0) {
            c += 1;
        } //if
        if (x > 600) {
            c += 2;
        } //if
        if (y < 0) {
            c += 4;
        } //if
        if (y > 600) {
            c += 8;
        } //if
        return c;
    } //getC

    private static double[] dotProduct(double[] x, double[][] matrix) {
        double[] y = new double[x.length];
        for (int i = 0; i < matrix.length; i++) {
            int r = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                r += (x[j] * matrix[j][i]);
            } //for
            y[i] = r;
        } //for
        return y;
    } //dotProduct

    private static double[][] concat(double[][] m1, double[][] m2) {
        double[][] result = new double[m1.length][m1.length];
        double num;
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                num = 0;
                num += m1[i][0] * m2[0][j];
                num += m1[i][1] * m2[1][j];
                num += m1[i][2] * m2[2][j];
                if (m1.length == 4) {
                    num += m1[i][3] * m2[3][j];
                } //if
                result[i][j] = num;
            } //for
        } //for
        return result;
    } //concat

    private static int inputLines(double[][] datalines, int num) {
        try {
            FileReader in = new FileReader(filename);
            BufferedReader br = new BufferedReader(in);
            String s = br.readLine();
            while (s != null) {
                String[] line = s.split(" ");
                datalines[num][0] = Integer.parseInt(line[0]);
                datalines[num][1] = Integer.parseInt(line[1]);
                datalines[num][2] = Integer.parseInt(line[2]);
                datalines[num][3] = Integer.parseInt(line[3]);
                num++;
                s = br.readLine();
            } //while
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //catch
        displayPixels(datalines, num);
        return num;
    } //inputLines

    private static int input3DLines(double[][] datalines3D, int num) {
        try {
            FileReader in = new FileReader(filename);
            BufferedReader br = new BufferedReader(in);
            String s = br.readLine();
            while (s != null) {
                String[] line = s.split(" ");
                datalines3D[num][0] = Integer.parseInt(line[0]);
                datalines3D[num][1] = Integer.parseInt(line[1]);
                datalines3D[num][2] = Integer.parseInt(line[2]);
                datalines3D[num][3] = Integer.parseInt(line[3]);
                datalines3D[num][4] = Integer.parseInt(line[4]);
                datalines3D[num][5] = Integer.parseInt(line[5]);
                num++;
                s = br.readLine();
            } //while
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //catch
        return num;
    } //input3DLines

    private static void outputLines(double[][] datalines, int num) {
        try {
            FileWriter out = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(out);
            for (int i = 0; i < num; i++) {
                String s = (int)datalines[i][0] + " " + (int)datalines[i][1] + " " + (int)datalines[i][2] + " " + (int)datalines[i][3] + "\n";
                bw.write(s);
            } //for
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //catch
    } //outputLines

    private static void output3DLines(double[][] datalines3D, int num) {
        try {
            FileWriter out = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(out);
            for (int i = 0; i < num; i++) {
                String s = datalines3D[i][0] + " " + datalines3D[i][1] + " " + datalines3D[i][2] + " " + datalines3D[i][3] + " " + datalines3D[i][4] + " " + datalines3D[i][5] + "\n";
                bw.write(s);
            } //for
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //catch
    } //outputLines

    public static void main(String[] args) {
        createGUI();
        bresenhams(300, 0, 300, 600, rgb5);
        bresenhams(0, 300, 600, 300, rgb5);
        setPixel(300, 300, rgb5);
        frame.update(frame.getGraphics());
        frame.setVisible(true);
    } //main

    private static void createGUI() {
        frame = new JFrame("CSCI 4810 Assignment 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        mainMenu = new JMenuBar();
        menu = new JMenu("2D File");
        mainMenu.add(menu);
        ButtonListener b = new ButtonListener();
        open = new JMenuItem("Open");
        open.addActionListener(b);
        save = new JMenuItem("Save");
        save.addActionListener(b);
        exit = new JMenuItem("Exit");
        exit.addActionListener(b);
        menu.add(open);
        menu.add(save);
        menu.add(exit);
        menu1 = new JMenu("3D File");
        open2 = new JMenuItem("Open");
        open2.addActionListener(b);
        save2 = new JMenuItem("Save");
        save2.addActionListener(b);
        exit2 = new JMenuItem("Exit");
        exit2.addActionListener(b);
        menu1.add(open2);
        menu1.add(save2);
        menu1.add(exit2);
        mainMenu.add(menu1);
        menu2 = new JMenu("2D Transformation");
        mainMenu.add(menu2);
        bT = new JMenuItem("Translate");
        bT.addActionListener(b);
        bS = new JMenuItem("Basic Scale");
        bS.addActionListener(b);
        bR = new JMenuItem("Basic Rotate");
        bR.addActionListener(b);
        scl = new JMenuItem("Scale");
        scl.addActionListener(b);
        rot = new JMenuItem("Rotate");
        rot.addActionListener(b);
        menu2.add(bT);
        menu2.add(bS);
        menu2.add(bR);
        menu2.add(scl);
        menu2.add(rot);
        menu3 = new JMenu("3D Transformation");
        mainMenu.add(menu3);
        bT3D = new JMenuItem("Translate");
        bT3D.addActionListener(b);
        bS3D = new JMenuItem("Scale");
        bS3D.addActionListener(b);
        rotX = new JMenuItem("Rotate X");
        rotX.addActionListener(b);
        rotY = new JMenuItem("Rotate Y");
        rotY.addActionListener(b);
        rotZ = new JMenuItem("Rotate Z");
        rotZ.addActionListener(b);
        menu3.add(bT3D);
        menu3.add(bS3D);
        menu3.add(rotX);
        menu3.add(rotY);
        menu3.add(rotZ);

        label.setMaximumSize(new Dimension(600, 600));
        label.setMinimumSize(new Dimension(600, 600));
        label.setPreferredSize(new Dimension(600, 600));
        icon.setImage(image);
        label.setIcon(icon);

        ta = new JTextField();
        ta.setText("Coordinates of figure");

        frame.getContentPane().add(BorderLayout.NORTH, mainMenu);
        frame.getContentPane().add(BorderLayout.CENTER, label);
        frame.getContentPane().add(BorderLayout.SOUTH, ta);
        frame.setVisible(true);
    } //createGUI

    private static class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == open) {
                filename = JOptionPane.showInputDialog(frame, "Which file should be opened? ", "Open File", JOptionPane.PLAIN_MESSAGE);
                num = inputLines(datalines, num);
                frame.update(frame.getGraphics());
                frame.setVisible(true);
            } //if
            else if (e.getSource() == open2) {
                filename = JOptionPane.showInputDialog(frame, "Which file should be opened? ", "Open File", JOptionPane.PLAIN_MESSAGE);
                String s = JOptionPane.showInputDialog(frame, "Enter the point of origin: ", JOptionPane.PLAIN_MESSAGE);
                String[] s2 = s.split(" ");
                origin[0] = Double.parseDouble(s2[0]);
                origin[1] = Double.parseDouble(s2[1]);
                origin[2] = Double.parseDouble(s2[2]);
                num = input3DLines(datalines3D, num);
                perspectiveProjection(datalines3D, datalines3DPP);
                display3D(datalines3DPP, datalines, rgb1);
                frame.update(frame.getGraphics());
                frame.setVisible(true);
            } //else-if
            else if (e.getSource() == save) {
                filename = JOptionPane.showInputDialog(frame, "Which file should be written to? ", "Save File", JOptionPane.PLAIN_MESSAGE);
                outputLines(datalines, num);
                JOptionPane.showMessageDialog(frame, "Data saved to file " + filename);
            } //else-if
            else if (e.getSource() == save2) {
                filename = JOptionPane.showInputDialog(frame, "Which file should be written to? ", "Save File", JOptionPane.PLAIN_MESSAGE);
                output3DLines(datalines3D, num);
                JOptionPane.showMessageDialog(frame, "Data saved to file " + filename);
            } //else-if
            else if ((e.getSource() == exit) | (e.getSource() == exit2)) {
                int exitQA = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (exitQA == YES_OPTION) {
                    System.exit(0);
                } //if
            } //else-if
            else if (e.getSource() == bT) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter two integers for translation: ", JOptionPane.PLAIN_MESSAGE);
                    String[] s = input.split(" ");
                    double tx = Double.parseDouble(s[0]);
                    double ty = Double.parseDouble(s[1]);
                    applyTransformation(basicTranslate(tx, ty), datalines);
                    displayPixels(datalines, num);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == bR) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter the degree of rotation: ", JOptionPane.PLAIN_MESSAGE);
                    int angle = Integer.parseInt(input);
                    applyTransformation(basicRotate(angle), datalines);
                    displayPixels(datalines, num);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == bS) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter two integers for scaling: ", JOptionPane.PLAIN_MESSAGE);
                    String[] s = input.split(" ");
                    double sx = Double.parseDouble(s[0]);
                    double sy = Double.parseDouble(s[1]);
                    applyTransformation(basicScale(sx, sy), datalines);
                    displayPixels(datalines, num);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == scl) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter two integers for scaling and two integers for the center point: ", JOptionPane.PLAIN_MESSAGE);
                    String[] s = input.split(" ");
                    double sx = Double.parseDouble(s[0]);
                    double sy = Double.parseDouble(s[1]);
                    int cx = Integer.parseInt(s[2]);
                    int cy = Integer.parseInt(s[3]);
                    applyTransformation(scale(sx, sy, cx, cy), datalines);
                    displayPixels(datalines, num);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == rot) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter the degree of rotation and two integers for center point: ", JOptionPane.PLAIN_MESSAGE);
                    String[] s = input.split(" ");
                    int angle = Integer.parseInt(s[0]);
                    int cx = Integer.parseInt(s[1]);
                    int cy = Integer.parseInt(s[2]);
                    applyTransformation(rotate(angle, cx, cy), datalines);
                    displayPixels(datalines, num);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == bT3D) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter three integers for translation: ", JOptionPane.PLAIN_MESSAGE);
                    String[] s = input.split(" ");
                    double tx = Double.parseDouble(s[0]);
                    double ty = Double.parseDouble(s[1]);
                    double tz = Double.parseDouble(s[2]);
                    apply3DTransformation(basicTranslate3D(tx, ty, tz), datalines3D);
                    perspectiveProjection(datalines3D, datalines3DPP);
                    display3D(datalines3DPP, datalines, rgb2);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == bS3D) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter three integers for scaling: ", JOptionPane.PLAIN_MESSAGE);
                    String[] s = input.split(" ");
                    double sx = Double.parseDouble(s[0]);
                    double sy = Double.parseDouble(s[1]);
                    double sz = Double.parseDouble(s[2]);
                    String input2 = JOptionPane.showInputDialog(frame, "Enter the center point coordinates: ", JOptionPane.PLAIN_MESSAGE);
                    String[] s2 = input2.split(" ");
                    int cx = Integer.parseInt(s2[0]);
                    int cy = Integer.parseInt(s2[1]);
                    int cz = Integer.parseInt(s2[2]);
                    apply3DTransformation(scale3D(sx, sy, sz, cx, cy, cz), datalines3D);
                    perspectiveProjection(datalines3D, datalines3DPP);
                    display3D(datalines3DPP, datalines, rgb3);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == rotX) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter the degree of rotation about the X-axis: ", JOptionPane.PLAIN_MESSAGE);
                    int angle = Integer.parseInt(input);
                    apply3DTransformation(basicRotateX(angle), datalines3D);
                    perspectiveProjection(datalines3D, datalines3DPP);
                    display3D(datalines3DPP, datalines, rgb4);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == rotY) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter the degree of rotation about the Y-axis: ", JOptionPane.PLAIN_MESSAGE);
                    int angle = Integer.parseInt(input);
                    apply3DTransformation(basicRotateY(angle), datalines3D);
                    perspectiveProjection(datalines3D, datalines3DPP);
                    display3D(datalines3DPP, datalines, rgb4);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else if (e.getSource() == rotZ) {
                try {
                    String input = JOptionPane.showInputDialog(frame, "Enter the degree of rotation about the Z-axis: ", JOptionPane.PLAIN_MESSAGE);
                    int angle = Integer.parseInt(input);
                    apply3DTransformation(basicRotateZ(angle), datalines3D);
                    perspectiveProjection(datalines3D, datalines3DPP);
                    display3D(datalines3DPP, datalines, rgb4);
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot perform transformation. Inputs not within bounds.");
                } //try-catch
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error with menu options.");
                System.exit(0);
            } //else
        } //actionPerformed
    } //ButtonListener
} //Main