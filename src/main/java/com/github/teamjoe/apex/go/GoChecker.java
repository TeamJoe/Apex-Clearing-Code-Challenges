package com.github.teamjoe.apex.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoChecker {
    public List<List<Boolean>> isEvaluated;

    public boolean havePiecesBeenTaken(List<List<Integer>> board) {
        isEvaluated = new ArrayList<>(board.size());
        for(int x = 0; x < board.size(); x++) {
            isEvaluated.add(new ArrayList<>(Collections.nCopies(board.get(x).size(), false)));
        }
        for(int x = 0; x < board.size(); x++) {
            for(int y = 0; y < board.get(x).size(); y++) {
                if (!isEvaluated.get(x).get(y)) {
                    if (hasPieceBeenTaken(board, board.get(x).get(y), x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasPieceBeenTaken(List<List<Integer>> board, int player, int x, int y) {
        if (x < 0
                || x >= board.size()
                || y < 0
                || y >= board.get(x).size()) {
            return true;
        } else if (board.get(x).get(y) == 0) {
            return false;
        } else if (board.get(x).get(y) != player) {
            return true;
        } else if (isEvaluated.get(x).get(y)) {
            return true;
        }
        isEvaluated.get(x).set(y, true);

        boolean b1 = hasPieceBeenTaken(board, player, x-1, y);
        boolean b2 = hasPieceBeenTaken(board, player, x+1, y);
        boolean b3 = hasPieceBeenTaken(board, player, x, y-1);
        boolean b4 = hasPieceBeenTaken(board, player, x, y+1);

        return b1 && b2 && b3 && b4;
    }
}
