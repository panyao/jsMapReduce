package org.jscc.app.client.biojava3.core.sequence.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.Sequence;

/**
 * A sliding window view of a sequence which does not implement any
 * interfaces like {@link Sequence} because they do not fit how this works.
 * For each index requested we return a List of compounds back.
 *
 * If you perform a view on a Sequence whose length is not a multiple of the
 * window the final window will be omitted i.e. if we have the sequence AGCGG
 * and a window of 3 then you will only see AGC since GG exceeds the calculated
 * length of this sequence.
 *
 * Because this does not implement a Sequence interface we do not recommend
 * passing this class around. If you need to represent a windowed sequence
 * as a real Sequence then translate it into a new Compound
 *
 * @author ayates
 *
 * @param <C> The type of compound we return from a window
 */
public class WindowedSequence<C extends Compound> implements Iterable<List<C>>{

  private final Sequence<C> sequence;
  private final int windowSize;

  public WindowedSequence(Sequence<C> sequence, int windowSize) {
    this.sequence = sequence;
    this.windowSize = windowSize;
  }

  /**
   * Access the current window size
   */
  public int getWindowSize() {
    return windowSize;
  }

  /**
   * Access the sequence which backs this window
   */
  public Sequence<C> getBackingSequence() {
    return sequence;
  }

  /**
   * Calculates start index according to the equation start = ( (index-1) -
   * windowSize) +1
   */
  protected int toStartIndex(int index) {
    return ((index - 1) * getWindowSize()) + 1;
  }

  /**
   * Returns the size of the windowed sequence which is the length by the
   * window size. Trailing Compounds are omitted.
   */
  public int getLength() {
    return getBackingSequence().getLength() / getWindowSize();
  }

  /**
   * For a given position into the windowed view this will return those
   * compounds we can see in the window. i.e. in the sequence AGGCCT requesting
   * index 1 returns AGG and requesting index 2 return CCT.
   *
   * @param index Windowed index position
   * @return The List of compounds
   */
  public List<C> getCompounds(int index) {
    int start = toStartIndex(index);
    int window = getWindowSize();
    List<C> output = new ArrayList<C>(window);
    for(int i = 0; i < window; i++) {
      output.add(getBackingSequence().getCompoundAt(start+i));
    }
    return output;
  }

  /**
   * Iterator of all List of compounds available in a windowed sequence.
   */
  public Iterator<List<C>> iterator() {
    final int start = 1;
    final int end = getLength();
    return new Iterator<List<C>>() {
      private int currentIndex = start;

      public boolean hasNext() {
        return currentIndex <= end;
      }

      public List<C> next() {
        return WindowedSequence.this.getCompounds(currentIndex++);
      }
      public void remove() {
        throw new UnsupportedOperationException("Cannot remove from a Windowed view");
      }
    };
  }
}
