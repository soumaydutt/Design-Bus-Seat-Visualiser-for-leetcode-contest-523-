/** LeetCode-compatible bus booking engine. */
public class BusBooking {
    private final boolean[][] booked;
    private final int[][] bookingCost;
    private int totalTime;
    private int bookedCount;

    public BusBooking(int n) {
        booked = new boolean[n + 1][4];
        bookingCost = new int[n + 1][4];
    }

    public void toggle(String seat) {
        int column = seat.charAt(0) - 'A';
        int row = Integer.parseInt(seat.substring(1));

        if (booked[row][column]) {
            totalTime -= bookingCost[row][column];
            bookingCost[row][column] = 0;
            booked[row][column] = false;
            bookedCount--;
            return;
        }

        int cost = 1;
        if (column == 0 && booked[row][1]) cost = 3;
        if (column == 3 && booked[row][2]) cost = 3;

        booked[row][column] = true;
        bookingCost[row][column] = cost;
        totalTime += cost;
        bookedCount++;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getMinTime() {
        return bookedCount;
    }

    public boolean isBooked(int row, int index) {
        return booked[row][index];
    }

    public int getBookingCost(int row, int index) {
        return bookingCost[row][index];
    }
}
