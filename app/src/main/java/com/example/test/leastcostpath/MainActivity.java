package com.example.test.leastcostpath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.output);
        setResult();

    }

    private void setResult() {
        ResultSet rs = getResult();
        output.setText(rs.toString());

    }

    private ResultSet getResult() {

        int input[][]  = Input.getInput();

        int rows = input.length;
        int columns = input[0].length;
        ResultSet rs = new ResultSet(columns);
        int[][] output= new int[rows][columns];

        for (int x=0; x<rows; x++) {
            for (int y=0; y<columns; y++) {
                output[x][y] = 51;
            }
        }

        for (int x=0; x<rows; x++) {
            output[x][0] = input[x][0];
        }

        for (int y=0; y<columns-1; y++) {
            for (int x=0; x<rows; x++) {
                int outX = x;
                int outY = y+1;

                if (output[x][y] < 51) {
                    int sum;
                    if (y==0) {
                        sum = input[x][y] + input[outX][outY];
                    } else {
                        sum = output[x][y] + input[outX][outY];
                    }
                    if (sum < output[outX][outY]) {
                        output[outX][outY] = sum;
                    }
                } else {
                    input[x][y] = 51;
                }

                // x -1
                if (x == 0) {
                    outX = rows - 1;
                } else {
                    outX = x - 1;
                }
                outY = y+1;

                if (output[x][y] < 51) {
                    int sum;
                    if (y==0) {
                        sum = input[x][y] + input[outX][outY];
                    } else {
                        sum = output[x][y] + input[outX][outY];
                    }
                    if (sum < output[outX][outY]) {
                        output[outX][outY] = sum;
                    }
                } else {
                    input[x][y] = 51;
                }

                // x  + 1
                if (x == rows - 1) {
                    outX = 0;
                } else {
                    outX = x + 1;
                }
                outY = y+1;

                if (output[x][y] < 51) {
                    int sum;
                    if (y==0) {
                        sum = input[x][y] + input[outX][outY];
                    } else {
                        sum = output[x][y] + input[outX][outY];
                    }
                    if (sum < output[outX][outY]) {
                        output[outX][outY] = sum;
                    }
                } else {
                    input[x][y] = 51;
                }
            }
        }

        int sum = 51;
        int sumX = -1;
        int sumY = -1;

        int y = columns -1;
        for (int x = 0 ; x < rows; x++) {
            if (output[x][y] < rs.totalCost) {
                rs.totalCost = output[x][y];
                sumX = x;
                sumY = y;
            }
        }
        if (rs.totalCost < 51) {
            rs.pathMade = true;
            rs.rows[columns -1] = sumX;
            sum = rs.totalCost;
        }
        if (rs.pathMade ) {
            for (y = columns -2; y >=0; y--) {
                sum = sum - input[sumX][sumY];

                for (int x= 0; x < rows; x++) {
                    if (sum == output[x][y] && isAdjacent(x, sumX, rows)) {
                        sumX = x;
                        sumY = y;
                        rs.rows[y]= x;
                        break;
                    }
                }
                sumY= y;
            }
        }
        return rs;
    }

    public static boolean isAdjacent(int x, int sumX, int rows) {
        if (sumX == x) {
            return true;
        } else if (Math.abs(sumX - x) == 1) {
            return true;
        } else if ((sumX == 0) && (x == (rows-1))) {
            return true;
        }else if ((x ==0) && (sumX == (rows-1))) {
            return true;
        }
        return false;
    }
}
