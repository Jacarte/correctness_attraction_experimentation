package services.interpolator;

import services.engine.ISpaceExplorer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SudokuInputProvider implements ISpaceExplorer.IInputProvider<int[][]> {


    private List<int[][]> grids;
    private BufferedReader br;
    int index = 0;


    public SudokuInputProvider(String fileName){
        grids = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while (br.readLine() != null) {//Trash Header
                int[][] grid = new int[9][9];
                for (int row = 0; row < 9; row++) {
                    String rowAsStr = br.readLine();
                    for (int col = 0; col < rowAsStr.length(); col++) {
                        grid[row][col] = Integer.parseInt(rowAsStr.charAt(col) + "");
                    }
                }
                grids.add(grid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean canNext() {
        return index < grids.size();
    }

    @Override
    public int[][] getIn() {
        return grids.get(index++);
    }

    @Override
    public int getSize() {
        return grids.size();
    }

    @Override
    public int[][] copy(int[][] in) {

        int[][] result = new int[in[0].length][in.length];

        for(int i = 0; i < result[0].length; i++)
            for(int j= 0; j < result.length; j++)
                result[i][j] = in[i][j];

        return result;
    }
}
