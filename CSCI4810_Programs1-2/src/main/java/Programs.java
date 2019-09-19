package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Scanner;

public class Programs {

    private static JFrame frame = new JFrame();
    private static JLabel label = new JLabel();
    private static BufferedImage image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
    private static int rgb1 = Color.CYAN.getRGB();
    private static int rgb2 = Color.GREEN.getRGB();
    private static int rgb4 = Color.GRAY.getRGB();
    private static int rgb5 = Color.WHITE.getRGB();
    private static int rgb6 = Color.MAGENTA.getRGB();
    private static int rgb7 = Color.RED.getRGB();
    private static ImageIcon icon = new ImageIcon();
    private static Random r = new Random();

    private static long basic(int x0, int y0, int x1, int y1) {
        int dy = y1-y0;
        int dx = x1-x0;
        int x = x0;
        int y = y0;
        float m;
        long start, end;

        if (dx == 0) { //Perfectly Vertical Line
            if (dy > 0) { //Positive Vertical Line
                start = System.nanoTime();
                for (int i = 0; i <= (dy - 1); i++) {
                    setPixel(x, y, rgb4);
                    y++;
                } //for
                end = (System.nanoTime() - start);
            } //if
            else { //Negative Vertical Line
                start = System.nanoTime();
                for (int i = (-dy); i > 0; i--) {
                    setPixel(x, y, rgb5);
                    y--;
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //if
        else if (dy == 0) { //Perfectly Horizontal Line
            if (dx > 0) { //Positive Horizontal Line
                start = System.nanoTime();
                for (int i = 0; i <= (dx - 1); i++) {
                    setPixel(x, y, rgb4);
                    x++;
                } //for
                end = (System.nanoTime() - start);
            } //if
            else { //Negative Horizontal Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dx)-1); i++) {
                    setPixel(x, y, rgb5);
                    x--;
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //else-if
        else if (dx == dy) { //Perfectly Diagonal Line
            if (dx > 0) { //Positive Diagonal Line
                start = System.nanoTime();
                for (int i = 0; i <= (dx - 1); i++) {
                    setPixel(x, y, rgb4);
                    x++;
                    y++;
                } //for
                end = (System.nanoTime() - start);
            } //if
            else { //Negative Diagonal Line
                start = System.nanoTime();
                for (int i = (-dx); i > 0; i--) {
                    setPixel(x, y, rgb5);
                    x--;
                    y--;
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //else-if
        else if (Math.abs(dx) > Math.abs(dy)) { //One pixel activated for each x
            m = ((float)dy/(float)dx);
            if ((dx > 0) & (dy > 0)) { //Positive Line
                start = System.nanoTime();
                for (int i = 0; i <= (dx - 1); i++) {
                    x = x0 + i;
                    y = Math.round(m * i + y0);
                    setPixel(x, y, rgb1);
                } //for
                end = (System.nanoTime() - start);
            } //if
            else if ((dx > 0) & (dy < 0)) { //Negative dy Line
                start = System.nanoTime();
                for (int i = 0; i <= (dx - 1); i++) {
                    x = x0 + i;
                    y = Math.round(y0 + m * i);
                    setPixel(x, y, rgb2);
                } //for
                end = (System.nanoTime() - start);
            } //else-if
            else if ((dx < 0) & (dy > 0)) { //Negative dx Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dx) - 1); i++) {
                    x = x0 - i;
                    y = Math.round(y0 - m * i);
                    setPixel(x, y, rgb6);
                } //for
                end = (System.nanoTime() - start);
            } //else-if
            else { //Negative dx and dy Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dx) - 1); i++) {
                    x = x0 - i;
                    y = Math.round(y0 - m * i);
                    setPixel(x, y, rgb7);
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //else-if
        else { //One pixel activated for each y
            m = ((float)dy/(float)dx);
            if ((dx > 0) & (dy > 0)) { //Positive Line
                start = System.nanoTime();
                for (int i = 0; i <= (dy - 1); i++) {
                    y = y0 + i;
                    x = Math.round(i/m + x0);
                    setPixel(x, y, rgb1);
                } //for
                end = (System.nanoTime() - start);
            } //if
            else if ((dx < 0) & (dy > 0)) { //Negative dx Line
                start = System.nanoTime();
                for (int i = 0; i <= (dy - 1); i++) {
                    y = y0 + i;
                    x = Math.round(x0 + i/m);
                    setPixel(x, y, rgb2);
                } //for
                end = (System.nanoTime() - start);
            } //else-if
            else if ((dx > 0) & (dy < 0)) { //Negative dy Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dy) - 1); i++) {
                    y = y0 - i;
                    x = Math.round(x0 - i/m);
                    setPixel(x, y, rgb6);
                } //for
                end = (System.nanoTime() - start);
            } //else-if
            else { //Negative dx and dy Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dy) - 1); i++) {
                    y = y0 - i;
                    x = Math.round(x0 - i/m);
                    setPixel(x, y, rgb7);
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //else
        return end;
    } //basic

    private static long bresenhams(int x0, int y0, int x1, int y1) {
        int dy = y1 - y0;
        int dx = x1 - x0;
        int y = y0;
        int x = x0;
        long start, end;

        if (dx == 0) { //Vertical Line
            if (dy > 0) { //Positive Vertical Line
                start = System.nanoTime();
                for (int i = 0; i <= (dy - 1); i++) {
                    setPixel(x, y, rgb4);
                    y++;
                } //for
                end = (System.nanoTime() - start);
            } //if
            else { //Negative Vertical Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dy) - 1); i++) {
                    setPixel(x, y, rgb5);
                    y--;
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //if
        else if (dy == 0) { //Horizontal Line
            if (dx > 0) { //Positive Horizontal Line
                start = System.nanoTime();
                for (int i = 0; i <= (dx - 1); i++) {
                    setPixel(x, y, rgb4);
                    x++;
                } //for
                end = (System.nanoTime() - start);
            } //if
            else { //Negative Horizontal Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dx) - 1); i++) {
                    setPixel(x, y, rgb5);
                    x--;
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //else-if
        else if (dx == dy) { //Diagonal Line
            if (dx > 0) { //Positive Diagonal Line
                start = System.nanoTime();
                for (int i = 0; i <= (dx - 1); i++) {
                    setPixel(x, y, rgb4);
                    x++;
                    y++;
                } //for
                end = (System.nanoTime() - start);
            } //if
            else { //Negative Diagonal Line
                start = System.nanoTime();
                for (int i = 0; i <= ((-dx) - 1); i++) {
                    setPixel(x, y, rgb5);
                    x--;
                    y--;
                } //for
                end = (System.nanoTime() - start);
            } //else
        } //else-if
        else if (Math.abs(dx) > Math.abs(dy)) { //Dx > Dy Line
            if ((dx > 0) & (dy > 0)) { //Positive Line
                int E = 2 * dy - dx;
                int inc1 = 2 * dy;
                int inc2 = 2 * (dy - dx);
                start = System.nanoTime();
                while (x < x1) {
                    setPixel(x, y, rgb1);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y + 1;
                    } //else
                    x = x + 1;
                } //while
                end = (System.nanoTime() - start);
            } //if
            else if ((dx > 0) & (dy < 0)) { //Negative dy Line
                int E = 2 * (-dy) - dx;
                int inc1 = 2 * (-dy);
                int inc2 = 2 * ((-dy) - dx);
                start = System.nanoTime();
                while (x < x1) {
                    setPixel(x, y, rgb2);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y - 1;
                    } //else
                    x = x + 1;
                } //while
                end = (System.nanoTime() - start);
            } //else-if
            else if ((dx < 0) & (dy > 0)) { //Negative dx Line
                int E = 2 * dy + dx;
                int inc1 = 2 * dy;
                int inc2 = 2 * (dy + dx);
                start = System.nanoTime();
                while (x > x1) {
                    setPixel(x, y, rgb6);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y + 1;
                    } //else
                    x = x - 1;
                } //while
                end = (System.nanoTime() - start);
            } //else-if
            else { //Negative dx and dy Line
                int E = -(2 * dy - dx);
                int inc1 = 2 * (-dy);
                int inc2 = 2 * (-(dy - dx));
                start = System.nanoTime();
                while (x > x1) {
                    setPixel(x, y, rgb7);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        y = y - 1;
                    } //else
                    x = x - 1;
                } //while
                end = (System.nanoTime() - start);
            } //else
        } //else-if
        else { //Dy > Dx Line
            if ((dx > 0) & (dy > 0)) { //Positive Line
                int E = 2 * dx - dy;
                int inc1 = 2 * dx;
                int inc2 = 2 * (dx - dy);
                start = System.nanoTime();
                while (y < y1) {
                    setPixel(x, y, rgb1);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x + 1;
                    } //else
                    y = y + 1;
                } //while
                end = (System.nanoTime() - start);
            } //if
            else if ((dx > 0) & (dy < 0)) { //Negative dy Line
                int E = 2 * dx + dy;
                int inc1 = 2 * dx;
                int inc2 = 2 * (dx + dy);
                start = System.nanoTime();
                while (y > y1) {
                    setPixel(x, y, rgb2);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x + 1;
                    } //else
                    y = y - 1;
                } //while
                end = (System.nanoTime() - start);
            } //else-if
            else if ((dx < 0) & (dy > 0)) { //Negative dx Line
                int E = 2 * (-dx) - dy;
                int inc1 = 2 * (-dx);
                int inc2 = 2 * ((-dx) - dy);
                start = System.nanoTime();
                while (y < y1) {
                    setPixel(x, y, rgb6);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x - 1;
                    } //else
                    y = y + 1;
                } //while
                end = (System.nanoTime() - start);
            } //else-if
            else { //Negative dx and dy Line
                int E = -(2 * dx - dy);
                int inc1 = 2 * (-dx);
                int inc2 = 2 * (-(dx - dy));
                start = System.nanoTime();
                while (y > y1) {
                    setPixel(x, y, rgb7);
                    if (E < 0) {
                        E = E + inc1;
                    } //if
                    else {
                        E = E + inc2;
                        x = x - 1;
                    } //else
                    y = y - 1;
                } //while
                end = (System.nanoTime() - start);
            } //else
        } //else
        return end;
    } //bse

    private static void setPixel(int x, int y, int rgb) {
        image.setRGB(x, y, rgb);
        icon.setImage(image);
        label.setIcon(icon);
        frame.add(label);
    } //setPixel

    private static long printHorizontal(String type) {
        int x0 = r.nextInt(600);
        int x1 = x0;
        int y0 = r.nextInt(600);
        int y1 = r.nextInt(600);
        if (type.equalsIgnoreCase("basic")) {
            return basic(x0, y0, x1, y1);
        } //if
        else if (type.equalsIgnoreCase("bresenhams")) {
            return bresenhams(x0, y0, x1, y1);
        } //else-if
        else {
            return 0;
        } //else
    } //printHorizontal

    private static long printVertical(String type) {
        int x0 = r.nextInt(600);
        int x1 = r.nextInt(600);
        int y0 = r.nextInt(600);
        int y1 = y0;
        if (type.equalsIgnoreCase("basic")) {
            return basic(x0, y0, x1, y1);
        } //if
        else if (type.equalsIgnoreCase("bresenhams")) {
            return bresenhams(x0, y0, x1, y1);
        } //else-if
        else {
            return 0;
        } //else
    } //printVertical

    private static long printDiagonal(String type) {
        int x0 = r.ints(200, 400).findFirst().getAsInt();
        int x1 = r.ints(200, 400).findFirst().getAsInt();
        int y0 = r.ints(200, 400).findFirst().getAsInt();
        int y1 = y0 + (x1 - x0);
        if (type.equalsIgnoreCase("basic")) {
            return basic(x0, y0, x1, y1);
        } //if
        else if (type.equalsIgnoreCase("bresenhams")) {
            return bresenhams(x0, y0, x1, y1);
        } //else-if
        else {
            return 0;
        } //else
    } //printDiagonal

    private static long printDyDx (String type) {
        int x0 = r.ints(200, 400).findFirst().getAsInt();
        int x1 = r.ints(200, 400).findFirst().getAsInt();
        int y0 = r.ints(0, 200).findFirst().getAsInt();
        int y1 = r.ints(400, 600).findFirst().getAsInt();
        if (type.equalsIgnoreCase("basic")) {
            return basic(x0, y0, x1, y1);
        } //if
        else if (type.equalsIgnoreCase("bresenhams")) {
            return bresenhams(x0, y0, x1, y1);
        } //else-if
        else {
            return 0;
        } //else
    } //printDyDx

    private static long printDxDy (String type) {
        int x0 = r.ints(0, 200).findFirst().getAsInt();
        int x1 = r.ints(400, 600).findFirst().getAsInt();
        int y0 = r.ints(200, 400).findFirst().getAsInt();
        int y1 = r.ints(200, 400).findFirst().getAsInt();
        if (type.equalsIgnoreCase("basic")) {
            return basic(x0, y0, x1, y1);
        } //if
        else if (type.equalsIgnoreCase("bresenhams")) {
            return bresenhams(x0, y0, x1, y1);
        } //else-if
        else {
            return 0;
        } //else
    } //printDxDy

    private static void printDemo(String type) {
        printVertical(type);
        printHorizontal(type);
        printDiagonal(type);
        printDyDx(type);
        printDxDy(type);
    } //printDemo

    public static void main(String[] args) {
        int x0, y0, x1, y1;

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Which algorithm should be used? (basic or bresenhams) ");
        String input = keyboard.nextLine();
        System.out.println("How many lines should be drawn?");
        int n = keyboard.nextInt();
        long average = 0;
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        label.setMaximumSize(new Dimension(600, 600));
        label.setMinimumSize(new Dimension(600, 600));
        label.setPreferredSize(new Dimension(600, 600));

        if (input.equalsIgnoreCase("basic")) {
            for (int i = 0; i < n; i++) {
                //Random Values
                x0 = r.nextInt(600);
                x1 = r.nextInt(600);
                y0 = r.nextInt(600);
                y1 = r.nextInt(600);
                average += basic(x0, y0, x1, y1);
                frame.update(frame.getGraphics());
                frame.setVisible(true);
            } //for
            System.out.println("Line Drawing Completed!");
            System.out.println("Average = " + (average/(long)n));
        } //if
        else if (input.equalsIgnoreCase("bresenhams")) {
            for (int i = 0; i < n; i++) {
                //Random Values
                x0 = r.nextInt(600);
                x1 = r.nextInt(600);
                y0 = r.nextInt(600);
                y1 = r.nextInt(600);
                average += bresenhams(x0, y0, x1, y1);
                frame.update(frame.getGraphics());
                frame.setVisible(true);
            } //for
            System.out.println("Line Drawing Completed!");
            System.out.println("Average = " + (average/(long)n));
        } //else-if
        else {
            System.out.println("Error: Invalid entry");
            System.exit(0);
        } //else
    } //main

} //class
