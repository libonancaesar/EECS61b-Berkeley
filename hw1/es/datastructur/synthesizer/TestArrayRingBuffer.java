package es.datastructur.synthesizer;
//import org.junit.Test;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> arbb = new ArrayRingBuffer<>(5);
        assertTrue(arb.isEmpty());
        assertEquals(arb.capacity,10);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(1);
        arbb.enqueue(2);
        arbb.enqueue(3);
        arbb.enqueue(4);
        arbb.enqueue(1);
        for (Integer t:arb){
            System.out.println(t);
        }
        System.out.println(arb.equals(arbb));
    }
}