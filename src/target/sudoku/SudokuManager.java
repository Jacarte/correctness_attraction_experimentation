package target.sudoku;

import services.engine.ISpaceExplorer;

import java.util.ArrayList;
import java.util.List;

public class SudokuManager implements ISpaceExplorer.IManager<int[][], int[][]> {


    private List<Integer> buildListOf9Integer() {
        List<Integer> lst = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++)
            lst.add(i);
        return lst;
    }

    @Override
    public int[][] _do(int[][] in) {
        SudokuIntr instr = new SudokuIntr(in);
        instr.initSubsets();
        instr.solve();
        return instr.getGrid();
    }

    @Override
    public boolean _do(int[][] output, int[][] input) {
        for (int row = 0; row < output.length; row++) {
            List<Integer> listOfInteger = buildListOf9Integer();
            for (int col = 0; col < output[row].length; col++) {
                if (!listOfInteger.remove(new Integer(output[row][col])))
                    return false;
                if (input[row][col] != 0 && input[row][col] != output[row][col])
                    return false;
            }
        }
        for (int col = 0; col < output.length ; col++) {
            List<Integer> listOfInteger = buildListOf9Integer();
            for (int row = 0; row < output[col].length ; row++) {
                if (!listOfInteger.remove(new Integer(output[row][col])))
                    return false;
                if (input[row][col] != 0 && input[row][col] != output[row][col])
                    return false;
            }
        }

        return true;
    }

    @Override
    public int[][] get(int[][] in) {
        Sudoku su = new Sudoku(in);
        su.initSubsets();
        su.solve();

        return su.getGrid();
    }
}
