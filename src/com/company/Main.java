package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static int topToDown(int[][] tri) {
        int length = tri.length;

        int res = -1;
        for (int i = 0; i < length - 2; i++)
            res = Math.max(res, tri[0][i]);

        for (int i = 1; i < length; i++) {
            res = -1;
            for (int j = 0; j < length; j++) {
                if (j == 0 && tri[i][j] != -1) {
                    if (tri[i - 1][j] != -1)
                        tri[i][j] += tri[i - 1][j];
                    else
                        tri[i][j] = -1;
                } else if (j > 0 && j < length - 1 && tri[i][j] != -1) {
                    int tmp = calculatedValue(tri[i - 1][j],
                            tri[i - 1][j - 1]);
                    if (tmp == -1) {
                        tri[i][j] = -1;
                    } else
                        tri[i][j] += tmp;
                } else if (j > 0 && tri[i][j] != -1) {
                    int tmp = calculatedValue(tri[i - 1][j],
                            tri[i - 1][j - 1]);
                    if (tmp == -1) {
                        tri[i][j] = -1;
                    } else
                        tri[i][j] += tmp;
                } else if (j != 0 && j < length - 1 && tri[i][j] != -1) {
                    int tmp = calculatedValue(tri[i - 1][j],
                            tri[i - 1][j - 1]);
                    if (tmp == -1) {
                        tri[i][j] = -1;
                    } else
                        tri[i][j] += tmp;
                }
                res = Math.max(tri[i][j], res);
            }
        }
        return res;
    }

    static int calculatedValue(int input1, int input2)  //returns max value
    {
        if (input1 == -1 && input2 == -1 || input1 == 0 && input2 == 0)
            return -1;
        else
            return Math.max(input1, input2);
    }

    private static Boolean checkPrime(int number) {
        if ((number & 1) == 0) {
            if (number == 2) {
                return true;
            }
            return false;
        }
        for (var i = 3; (i * i) <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return number != 1;
    }

    private static int[][] deletePrimes(int[][] tri) {
        int length = tri.length;
        for (var i = 0; i < length; i++) {
            for (var j = 0; j < length; j++) {
                if (tri[i][j] == 0) {
                    continue;
                } else if (checkPrime(tri[i][j]))      
                {
                    tri[i][j] = -1;
                }
            }
        }
        return tri;
    }


    public static void main(String[] args) {



        int tri4[][] = new int[15][15];
        


            try {
                File myObj = new File("src/com/company/triangle.txt");
                Scanner myReader = new Scanner(myObj);
                int c = 0;
                while (myReader.hasNextLine()) {

                    String data = myReader.nextLine();
                    String[] arrSplit = data.split("\\s+");
                    for (int i = 0; i < arrSplit.length; i++) {
                        System.out.print(arrSplit[i] + " ");

                        tri4[c][i] = Integer.parseInt(arrSplit[i]);

                    }
                    c++;
                    System.out.println();
                }

                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


            int maxSum4 = topToDown(deletePrimes(tri4));
            System.out.println("the result is " + maxSum4);

        }


    }
