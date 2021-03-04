package com.github.teamjoe.apex.go;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoCheckerTests {
    public GoChecker checker = new GoChecker();

    @Test
    void singleEmptyBoard() {
        var board = Arrays.asList(
                Arrays.asList(0)
        );
        assertEquals(false, checker.havePiecesBeenTaken(board));
    }

    @Test
    void singleElementSurrounded() {
        var board = Arrays.asList(
                Arrays.asList(1)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void singleRowSurrounded() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 2)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void singleRowNonSurrounded() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 0)
        );
        assertEquals(false, checker.havePiecesBeenTaken(board));
    }

    @Test
    void singleColumnSurrounded() {
        var board = Arrays.asList(
                Arrays.asList(0),
                Arrays.asList(1),
                Arrays.asList(2)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void singleColumnNonSurrounded() {
        var board = Arrays.asList(
                Arrays.asList(0),
                Arrays.asList(1),
                Arrays.asList(0)
        );
        assertEquals(false, checker.havePiecesBeenTaken(board));
    }

    @Test
    void simpleBoardEmpty() {
        var board = Arrays.asList(
                Arrays.asList(0, 0, 0),
                Arrays.asList(0, 0, 0),
                Arrays.asList(0, 0, 0)
        );
        assertEquals(false, checker.havePiecesBeenTaken(board));
    }


    @Test
    void simpleBoardSurrounded() {
        var board = Arrays.asList(
                Arrays.asList(0, 0, 0),
                Arrays.asList(1, 1, 1),
                Arrays.asList(2, 2, 2)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void simpleBoardNotSurrounded() {
        var board = Arrays.asList(
                Arrays.asList(0, 0, 0),
                Arrays.asList(1, 0, 1),
                Arrays.asList(2, 2, 2)
        );
        assertEquals(false, checker.havePiecesBeenTaken(board));
    }

    @Test
    void squareSolutionWithHole() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(1, 2, 0, 2, 1),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 1, 0)
        );
        assertEquals(false, checker.havePiecesBeenTaken(board));
    }

    @Test
    void squareSolutionWithoutHole() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 1, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void testTheLSolution() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 0, 0, 0),
                Arrays.asList(1, 2, 1, 0, 0),
                Arrays.asList(1, 2, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 1, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void testUpsidedownLSolution() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(1, 2, 1, 1, 0),
                Arrays.asList(1, 2, 1, 0, 0),
                Arrays.asList(0, 1, 0, 0, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void testMirrorLSolution() {
        var board = Arrays.asList(
                Arrays.asList(0, 0, 0, 1, 0),
                Arrays.asList(0, 0, 1, 2, 1),
                Arrays.asList(0, 1, 1, 2, 1),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 1, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void testNSolution() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(1, 2, 1, 2, 1),
                Arrays.asList(1, 2, 1, 2, 1),
                Arrays.asList(0, 1, 0, 1, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void testCSolution() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(1, 2, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 1, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void testUSolution() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 0, 1, 0),
                Arrays.asList(1, 2, 1, 2, 1),
                Arrays.asList(1, 2, 1, 2, 1),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 1, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }

    @Test
    void testBackwardsCSolution() {
        var board = Arrays.asList(
                Arrays.asList(0, 1, 1, 1, 0),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 2, 1),
                Arrays.asList(1, 2, 2, 2, 1),
                Arrays.asList(0, 1, 1, 1, 0)
        );
        assertEquals(true, checker.havePiecesBeenTaken(board));
    }
}
