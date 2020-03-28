package com.darryl.activiti.algorithm;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner (System.in);
        while(in.hasNext()){
            int a=in.nextInt();
            int b=in.nextInt();
            int x[][]=new int [50][50];
            x[1][0]=1;
            for (int i = 1; i <=b; i++) {
                for (int j = 2; j <=a; j++) {
                    x[j][i]=x[j-1][i-1]+x[j+1][i-1];
                }
                x[1][i]+=x[a][i-1]+x[2][i-1];
                x[a][i]+=x[1][i-1];
            }
            System.out.println(x[1][b]);
        }
    }
}

/*class Scanner {
    private BufferedReader br;
    private StringTokenizer st;
    Scanner(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
        st = new StringTokenizer("");
    }

    String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    boolean hasNext() {
        while (!st.hasMoreTokens()) {
            String s = nextLine();
            if (s == null) {
                return false;
            }
            st = new StringTokenizer(s);
        }
        return true;
    }

    String next() {
        hasNext();
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }
}*/
