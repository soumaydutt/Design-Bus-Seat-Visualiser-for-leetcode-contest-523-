# Bus Booking Visualizer 🚌

**LeetCode Weekly Contest 523 — Question 3: Design Bus Seat Booking**

An interactive dark-mode seat booking visualizer with a Java implementation.

## Why I made this

I was having trouble visualizing how booking and cancellation affect the total time, especially why a window seat keeps its original 3-second cost even after the adjacent middle seat gets cancelled. So I made this small visualizer to experiment with bookings and understand the problem better. Hope it helps anyone else stuck on the same detail.

## Problem statement

Design a seat booking system for a bus with `n` rows, each with four seats `A`, `B`, `C`, `D`. A and D are window seats, B and C are middle seats. A is adjacent to B, while D is adjacent to C.

- Booking B or C always takes **1 second**.
- Booking A or D takes **1 second** if the adjacent middle seat is free, or **3 seconds** if that middle seat is already booked.
- `toggle(String seat)` books an empty seat or frees a booked seat.
- `getTotalTime()` returns the sum of the *original booking costs* of currently booked seats.
- `getMinTime()` returns the minimum time required to book the currently booked seats in any order.
- A booked seat retains its original cost even if a neighbor is later booked or freed.
- If nothing is booked, both queries return 0.

**Constraints:** `1 <= n <= 100000`; at most `100000` operations.

### Example

| Action | Total | Minimum | Explanation |
|---|---:|---:|---|
| `toggle("B3")` | 1 | 1 | Middle seat |
| `toggle("A3")` | 4 | 2 | B3 booked, so A3 costs 3 |
| `toggle("C3")` | 5 | 3 | Middle seat |
| `toggle("D3")` | 8 | 4 | C3 booked, so D3 costs 3 |
| `toggle("B3")` | 7 | 3 | B3 removed; A3 still costs 3 |

## Solution (Java)

See [BusBooking.java](BusBooking.java).

Keep two arrays:
```java
boolean[][] booked;  // Whether each seat is booked
int[][] bookingCost; // Original cost of each booked seat
```

Also keep `totalTime` and `bookedCount`. Booking checks the adjacent middle seat at that moment, records either 1 or 3 seconds, and updates both counters. Cancellation subtracts the **stored** cost; it does not change other seats' costs.

**Key insight:** `getMinTime() == bookedCount`. Every seat costs at least 1 second, and booking window seats before middle seats avoids all delays.

| Operation | Complexity |
|---|---|
| Constructor | O(n) time and space |
| `toggle` | O(1) |
| `getTotalTime` | O(1) |
| `getMinTime` | O(1) |

## Run

**Browser:** Open [index.html](index.html), click seats to book/cancel, inspect per-seat costs and total vs minimum time. Choose **Step through example** to advance through **B3 → A3 → cancel B3 → cancel A3 → rebook A3**. Use Previous, Next, Play/Pause, or Restart to inspect the total and minimum after each operation. Click a seat at any time to return to manual mode.

**Java desktop:** `javac BusBooking.java BusBookingDemo.java`, then `java BusBookingDemo`.

## Publish as a website

In GitHub, open **Settings → Pages**, choose **Deploy from a branch**, branch `main`, folder `/ (root)`. Once deployed, share the GitHub Pages URL with the LeetCode community.

## LeetCode Discuss

**Suggested title:** Contest 523 Q3 — Interactive Bus Booking Visualizer (Java + Dark Mode)

I found it difficult to visualize why a window seat keeps its original booking cost even when the adjacent middle seat is cancelled, so I built a little interactive demo to test the booking rules. Hope it helps others working through the problem!
