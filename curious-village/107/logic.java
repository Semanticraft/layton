package org.example;

import java.util.*;

public class Logic {
    private static final int[][] SOLUTION = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
    private static Set<TwoDArrayWrapper> alreadyVisited = new HashSet<>();

    static class Node {
        int[][] state;
        Node parent;

        public Node(int[][] state, Node parent) {
            this.state = state;
            this.parent = parent;
        }
    }

    public static ArrayList<int[][]> generateStepsToSolution(int[][] initialState) {
        ArrayList<int[][]> stepsToSolution = new ArrayList<>();
        Queue<Node> nextStates = new LinkedList<>();

        nextStates.add(new Node(initialState, null));
        alreadyVisited.add(new TwoDArrayWrapper(initialState));

        Node solutionNode = null;  

        while (!nextStates.isEmpty()) {
            Node currentNode = nextStates.poll();

            if (Arrays.deepEquals(currentNode.state, SOLUTION)) {
                solutionNode = currentNode;
                break;
            }
            
            int[] posOfZero = searchElement(currentNode.state, 0);

            ArrayList<int[][]> frontier = generateFrontier(currentNode.state, posOfZero);

            for (int[][] neighbor : frontier) {
                TwoDArrayWrapper wrappedState = new TwoDArrayWrapper(neighbor);

                if (!alreadyVisited.contains(wrappedState)) {
                    nextStates.add(new Node(neighbor, currentNode));  // Store currentNode as parent
                    alreadyVisited.add(wrappedState);
                }
            }
        }

        // Backtrack from the solution node to the initial state to build the solution path
        if (solutionNode != null) {
            Node current = solutionNode;
            while (current != null) {
                stepsToSolution.add(0, current.state); 
                current = current.parent;
            }
        }

        return stepsToSolution; 
    }


    private static int[] searchElement(int[][] array, int element) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == element) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    private static ArrayList<int[][]> generateFrontier(int[][] currentState, int[] posOfZero) {
        ArrayList<int[][]> frontier = new ArrayList<>();
        int row = posOfZero[0];
        int col = posOfZero[1];

        // Check element above
        if (row - 1 >= 0) {
            int[][] newFrontierState = new int[currentState.length][];
            for (int i = 0; i < currentState.length; i++) {
                newFrontierState[i] = currentState[i].clone(); 
            }
            int nextPieceToMove = newFrontierState[row - 1][col];
            newFrontierState[row - 1][col] = 0;
            newFrontierState[posOfZero[0]][posOfZero[1]] = nextPieceToMove;
            frontier.add(newFrontierState);
        }

        // Check element below
        if (row + 1 < currentState.length) {
            int[][] newFrontierState = new int[currentState.length][];
            for (int i = 0; i < currentState.length; i++) {
                newFrontierState[i] = currentState[i].clone(); 
            }
            int nextPieceToMove = newFrontierState[row + 1][col];
            newFrontierState[row + 1][col] = 0;
            newFrontierState[posOfZero[0]][posOfZero[1]] = nextPieceToMove;
            frontier.add(newFrontierState);
        }

        // Check element to the left
        if (col - 1 >= 0) {
            int[][] newFrontierState = new int[currentState.length][];
            for (int i = 0; i < currentState.length; i++) {
                newFrontierState[i] = currentState[i].clone();
            }
            int nextPieceToMove = newFrontierState[row][col - 1];
            newFrontierState[row][col - 1] = 0;
            newFrontierState[posOfZero[0]][posOfZero[1]] = nextPieceToMove;
            frontier.add(newFrontierState);
        }

        // Check element to the right
        if (col + 1 < currentState[0].length) {
            int[][] newFrontierState = new int[currentState.length][];
            for (int i = 0; i < currentState.length; i++) {
                newFrontierState[i] = currentState[i].clone();
            }
            int nextPieceToMove = newFrontierState[row][col + 1];
            newFrontierState[row][col + 1] = 0;
            newFrontierState[posOfZero[0]][posOfZero[1]] = nextPieceToMove;
            frontier.add(newFrontierState);
        }

        return frontier;
    }

    public static String stateToString(int[][] state) {
        StringBuilder stateString = new StringBuilder("[\n");
        for (int[] row : state) {
            stateString.append("[");
            for (int integer : row) {
                stateString.append(integer).append(", ");
            }
            stateString.append("]\n");
        }
        stateString.append("]");
        return stateString.toString();
    }
}
