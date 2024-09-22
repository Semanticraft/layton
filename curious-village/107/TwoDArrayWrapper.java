import java.util.Arrays;

public class TwoDArrayWrapper {
    private int[][] array;

    public TwoDArrayWrapper(int[][] array) {
        this.array = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i].clone();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoDArrayWrapper that = (TwoDArrayWrapper) o;
        return Arrays.deepEquals(this.array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.array);
    }
}
