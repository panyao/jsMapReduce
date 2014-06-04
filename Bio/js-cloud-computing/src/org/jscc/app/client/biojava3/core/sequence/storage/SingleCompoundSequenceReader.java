package org.jscc.app.client.biojava3.core.sequence.storage;

import java.util.Iterator;
import java.util.List;

import org.jscc.app.client.biojava3.core.sequence.AccessionID;
import org.jscc.app.client.biojava3.core.sequence.Strand;
import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.CompoundSet;
import org.jscc.app.client.biojava3.core.sequence.template.ProxySequenceReader;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceMixin;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceProxyView;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceView;

/**
 * An implementation of the SequenceReader interface which for every
 * call will return only 1 compound (given to it during construction; a String
 * is also valid but will require a CompoundSet). The idea is to represent
 * large runs of a single compound without the memory footprint of storing these
 * compounds e.g. a run of 10KB of Ns in a DNASequence.
 *
 * @author ayates
 */
public class SingleCompoundSequenceReader<C extends Compound> implements ProxySequenceReader<C> {

    private final C compound;
    private final CompoundSet<C> compoundSet;
    private final int length;

    /**
     * Public constructor to be used with String based constructor
     */
    public SingleCompoundSequenceReader(String compound, CompoundSet<C> compoundSet, int length) {
        this(compoundSet.getCompoundForString(compound), compoundSet, length);
    }

    /**
     * Build the object with a compound rather than a String
     */
    public SingleCompoundSequenceReader(C compound, CompoundSet<C> compoundSet, int length) {
        this.compound = compound;
        this.compoundSet = compoundSet;
        this.length = length;
    }

    /**
     * Unsupported
     */
    @Override
    public void setCompoundSet(CompoundSet<C> compoundSet) {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Unsupported
     */
    @Override
    public void setContents(String sequence) {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Returns the length given during construction
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * Always returns the compound given at construction
     */
    @Override
    public C getCompoundAt(int position) {
        return compound;
    }

    /**
     * Returns 1 if the given compound is equal to the one given during
     * construction; otherwise will return -1.
     */
    @Override
    public int getIndexOf(C compound) {
        if(compound.equals(this.compound)) {
            return 1;
        }
        return -1;
    }

    /**
     * Returns the length of the Sequence if the given compound was equal to
     * the one given during construction. Otherwise returns -1
     */
    @Override
    public int getLastIndexOf(C compound) {
        if(compound.equals(this.compound)) {
            return getLength();
        }
        return -1;
    }

    /**
     * Delegates to {@link SequenceMixin#toList(org.jscc.app.client.biojava3.core.sequence.template.Sequence) }
     */
    @Override
    public String getSequenceAsString() {
        return SequenceMixin.toString(this);
    }

    /**
     * Unsupported
     */
    @Override
    public String getSequenceAsString(Integer start, Integer end, Strand strand) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Delegates to {@link SequenceMixin#toList(org.jscc.app.client.biojava3.core.sequence.template.Sequence) }
     */
    @Override
    public List<C> getAsList() {
        return SequenceMixin.toList(this);
    }

    /**
     * Creates a {@link SequenceProxyView} for the given coordinates
     */
    @Override
    public SequenceView<C> getSubSequence(Integer start, Integer end) {
        return new SequenceProxyView<C>(this, start, end);
    }

    /**
     * Returns the compound set given at construction
     */
    @Override
    public CompoundSet<C> getCompoundSet() {
        return compoundSet;
    }

    /**
     * Unsupoorted
     */
    @Override
    public AccessionID getAccession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Delegates to {@link SequenceMixin#countCompounds(org.jscc.app.client.biojava3.core.sequence.template.Sequence, C[]) }
     */
    @Override
    public int countCompounds(C... compounds) {
        return SequenceMixin.countCompounds(this, compounds);
    }

    /**
     * Returns an instance of {@link SequenceMixin.SequenceIterator}
     */
    @Override
    public Iterator<C> iterator() {
        return new SequenceMixin.SequenceIterator<C>(this);
    }
}