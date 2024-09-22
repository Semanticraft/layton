public class Main {
    public static void main(String[] args) {
        Logic.generateStepsToSolution(new int[][]{{8, 2, 3}, {4, 7, 6}, {5, 0, 1}})
                .stream().map(Logic::stateToString).forEach(System.out::println);
    }
}
