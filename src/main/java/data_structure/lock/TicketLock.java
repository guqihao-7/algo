package data_structure.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketLock {
    private final AtomicInteger ticket;
    private final AtomicInteger turn;

    public TicketLock() {
        ticket = new AtomicInteger();
        turn = new AtomicInteger();
    }

    public void lock() {
        int myTurn = this.ticket.getAndIncrement();
        while (myTurn != turn.get());
    }

    public void unlock() {
        turn.getAndIncrement();
    }
}
