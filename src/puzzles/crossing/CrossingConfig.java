// Project02: Strings and Crossing Puzzles

package puzzles.crossing;

import puzzles.common.solver.Configuration;

import java.util.*;

/**
 * This class implements the Configuration and keeps track of all the
 * configurations.
 *
 * @author Anita Srbinovska (as2950@rit.edu)
 */
public class CrossingConfig implements Configuration {
    /**
     * the number of pups
     */
    private static int pups;
    /**
     * the number of wolves
     */
    private static int wolves;
    /**
     * the left side of the river
     */
    private final int[] left;
    /**
     * the right side of the river
     */
    private int[] right = new int[2];
    /**
     * the side of the river
     */
    private final Side side;

    /**
     * an enum representing the side of the river (LEFT and RIGHT)
     */
    public enum Side {
        LEFT,
        RIGHT
    }

    /**
     * A constructor that is setting the number of pups and wolves as well as
     * the side initially starting from the LEFT side.
     *
     * @param pups   number of pups
     * @param wolves number of wolves
     */
    public CrossingConfig(int pups, int wolves) {
        CrossingConfig.pups = pups;
        CrossingConfig.wolves = wolves;
        this.left = new int[]{pups, wolves};
        this.side = Side.LEFT;
    }

    /**
     * The second constructor sets the side and left and right native arrays.
     *
     * @param side  which side of the river the pups and wolves are
     * @param left  the left list (side of the river)
     * @param right the right list (side of the river)
     */
    public CrossingConfig(Side side, int[] left, int[] right) {
        this.side = side;
        this.left = left;
        this.right = right;
    }

    /**
     * This method checks if the left list is empty and everything is crossed
     * on the right side of the river.
     *
     * @return true/false
     */
    @Override
    public boolean isSolution() {
        return this.left[0] == 0 && this.left[1] == 0;
    }

    /**
     * The getNeighbors() method checks how many pups and wolves can cross the
     * river since the boat can support up to two pups, or a single wolf, but i
     * t cannot be empty.
     *
     * @return the neighbours ArrayList
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        List<Configuration> neighbours = new ArrayList<>();
        if (side == Side.LEFT) { // pups
            if (left[0] >= 2) {
                int[] left = this.left.clone();
                int[] right = this.right.clone();
                left[0] -= 2;
                right[0] += 2;
                CrossingConfig config = new CrossingConfig(Side.RIGHT,
                        left, right);
                neighbours.add(config);
            }
            if (left[0] >= 1) { // pups
                int[] left = this.left.clone();
                int[] right = this.right.clone();
                left[0] -= 1;
                right[0] += 1;
                CrossingConfig config = new CrossingConfig(Side.RIGHT,
                        left, right);
                neighbours.add(config);
            }
            if (left[1] >= 1) { // wolf
                int[] left = this.left.clone();
                int[] right = this.right.clone();
                left[1] -= 1;
                right[1] += 1;
                CrossingConfig config = new CrossingConfig(Side.RIGHT,
                        left, right);
                neighbours.add(config);
            }
        }
        if (side == Side.RIGHT) { // pups
            if (right[0] >= 2) {
                int[] left = this.left.clone();
                int[] right = this.right.clone();
                left[0] += 2;
                right[0] -= 2;
                CrossingConfig config = new CrossingConfig(Side.LEFT,
                        left, right);
                neighbours.add(config);
            }
            if (right[0] >= 1) { // pups
                int[] left = this.left.clone();
                int[] right = this.right.clone();
                left[0] += 1;
                right[0] -= 1;
                CrossingConfig config = new CrossingConfig(Side.LEFT,
                        left, right);
                neighbours.add(config);
            }
            if (right[1] >= 1) { // wolf
                int[] left = this.left.clone();
                int[] right = this.right.clone();
                left[1] += 1;
                right[1] -= 1;
                CrossingConfig config = new CrossingConfig(Side.LEFT,
                        left, right);
                neighbours.add(config);
            }
        }
        return neighbours;
    }

    /**
     * This method gives the textual representation of the steps, the lists and
     * what side the boat is on.
     *
     * @return the textual representation of the steps, the lists and what side
     * the boat is on.
     */
    @Override
    public String toString() {
        return (side == Side.LEFT ? " (BOAT) " : "        ") + "left=" +
                Arrays.toString(this.left) + ", right=" +
                Arrays.toString(this.right) + (side == Side.RIGHT ? " " +
                "(BOAT) " : "        ");
    }

    /**
     * The equals() method checks if the other is an instance of the left side
     * and the right side (two lists), and it compares the side with the other
     * side.
     *
     * @param o the other object
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrossingConfig that = (CrossingConfig) o;
        return Arrays.equals(left, that.left) && Arrays.equals(right,
                that.right) && side == that.side;
    }

    /**
     * This method gives the hash code of the left, right lists and the side.
     *
     * @return the hash code of the left, right lists and the side.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(side);
        result = 31 * result + Arrays.hashCode(left);
        result = 31 * result + Arrays.hashCode(right);
        return result;
    }
}
