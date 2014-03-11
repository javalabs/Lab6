package lab.yegorov;

import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by AdminPC on 13.02.14.
 */
/*
1.	Написать на java класс, который реализует функционал работы с массивом, приведенный в варианте задания.
    Обязательно создать методы: введение массива, вывод массива, сортировка массива (любым методом).
2.	Создать класс для тестирования методов работы с массивом, создать минимум 10 тестов.

Variant 3.	Создать матрицу M на N, для которой подсчитать сумму элементов каждой строки.
 */
public class Main {
    public static void main(String args[]) {
        Test.testing();
    }
}

class Matrix {
    private double[][] matrix;

    public Matrix(int m, int n) {
        matrix = new double[m][n];

    }

    public void watchMatrix() {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);

        for(int i = 0; i < matrix.length; ++i) {
            for(int j = 0; j < matrix[i].length; ++j) {
                System.out.print(format.format(matrix[i][j]) + "\t");
            }
            System.out.println();
        }
    }
    public void inputMatrix() {
        Scanner scan = new Scanner(System.in);

        double tempInput = 0;
        for(int i = 0; i < matrix.length; ++i) {
            for(int j = 0; j < matrix[i].length; ++j) {

                while (true) {
                    try {
                        System.out.print("Введите элемент матрицы (" + (i + 1) + ", " + (j + 1) + ") :\n>>> ");

                        if(scan.hasNextDouble())
                            tempInput = scan.nextDouble();
                        else {
                            scan.nextLine();
                            continue;
                        }

                        if (tempInput < 0)
                            throw new NegativDoubleNumber("Отрицательное число");
                        else
                            break;
                    } catch (NegativDoubleNumber ex) {
                        System.out.println("Exception. " + ex.about() + " Try Again...");
                            //scan.nextLine(); //Clear buffer
                    }
                }
                matrix[i][j] = tempInput;

                //Random rand = new Random();
                //matrix[i][j] = 20 + (100 - 20) * rand.nextDouble();
            }
        }
        scan.close();

    }

    private double input(int i, int j, Scanner scan) throws NegativDoubleNumber {
        double tempInput;
        System.out.print("Введите элемент матрицы (" + (i + 1) + ", " + (j + 1) + ") :\n>>> ");
        tempInput = Double.parseDouble(scan.nextLine());
        if (tempInput < 0) throw new NegativDoubleNumber("Отрицательное число");
        return tempInput;
    }

    public void sortMatrix() {
        for(int i = 0; i < matrix.length; ++i) {
            /* Сортировка методом вставки с прямым включением */
            int k;
            double temp;
            for(int ii = 1; ii < matrix[i].length; ++ii ) {
                if(matrix[i][ii] < matrix[i][ii-1]) {
                    temp = matrix[i][ii];
                    k = ii - 1;
                    while(k >= 0 && temp <  matrix[i][k]) {
                        matrix[i][k + 1] = matrix[i][k];
                        k--;
                    }
                    matrix[i][k+1] = temp;
                }
            }
        }
    }

    public void sumMatrixElementLine() {
        System.out.println("Сумма элементов строк: ");
        double tempSum = 0;
        for(int i = 0; i < matrix.length; ++i) {
            for(int j = 0; j < matrix[i].length; ++j) {
                tempSum += matrix[i][j];
            }
            System.out.println("" + (i + 1) + "\t = " + tempSum);
            tempSum = 0;
        }
    }
}

class Test {
    public static void testing() {
        Matrix m = new Matrix(3,3);
        m.inputMatrix();
        System.out.println("Введенная матрица:");
        m.watchMatrix();
        m.sumMatrixElementLine();
        m.sortMatrix();
        System.out.println("\nОтсортированная по строкам матрица:");
        m.watchMatrix();
        m = null;
    }
}

class NegativDoubleNumber extends Exception {
    public NegativDoubleNumber(String m) {
        super(m);
    }
    public String about() {
        return super.getMessage();
    }
}