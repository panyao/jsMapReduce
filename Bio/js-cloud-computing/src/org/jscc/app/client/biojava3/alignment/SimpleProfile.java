/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 * Created on June 14, 2010
 * Author: Mark Chapman
 */

package org.jscc.app.client.biojava3.alignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jscc.app.client.biojava3.alignment.template.AlignedSequence;
import org.jscc.app.client.biojava3.alignment.template.AlignedSequence.Step;
import org.jscc.app.client.biojava3.alignment.template.Profile;
import org.jscc.app.client.biojava3.alignment.template.ProfileView;
import org.jscc.app.client.biojava3.core.sequence.location.template.Location;
import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.CompoundSet;
import org.jscc.app.client.biojava3.core.sequence.template.Sequence;

/**
 * Implements a data structure for the results of sequence alignment.  Every {@link List} returned is unmodifiable.
 *
 * @author Mark Chapman
 * @param <S> each element of the alignment {@link Profile} is of type S
 * @param <C> each element of an {@link AlignedSequence} is a {@link Compound} of type C
 */
public class SimpleProfile<S extends Sequence<C>, C extends Compound> implements Profile<S, C> {

    private List<AlignedSequence<S, C>> list;
    private List<S> originals;
    private int length;

    /**
     * Creates a pair profile for the given already aligned sequences.
     *
     * @param query the first sequence of the pair
     * @param target the second sequence of the pair
     * @throws IllegalArgumentException if sequences differ in size
     */
    protected SimpleProfile(AlignedSequence<S, C> query, AlignedSequence<S, C> target) {
        if (query.getLength() != target.getLength()) {
            throw new IllegalArgumentException("Aligned sequences differ in size");
        }
        list = new ArrayList<AlignedSequence<S, C>>();
        list.add(query);
        list.add(target);
        list = Collections.unmodifiableList(list);
        originals = new ArrayList<S>();
        originals.add((S) query.getOriginalSequence());
        originals.add((S) target.getOriginalSequence());
        originals = Collections.unmodifiableList(originals);
        length = query.getLength();
    }

    /**
     * Creates a profile from a single sequence.
     *
     * @param sequence sequence to seed profile
     */
    public SimpleProfile(S sequence) {
        List<Step> s = new ArrayList<Step>();
        for (int i = 0; i < sequence.getLength(); i++) {
            s.add(Step.COMPOUND);
        }
        list = new ArrayList<AlignedSequence<S, C>>();
        list.add(new SimpleAlignedSequence<S, C>(sequence, s));
        list = Collections.unmodifiableList(list);
        originals = new ArrayList<S>();
        originals.add(sequence);
        originals = Collections.unmodifiableList(originals);
        length = sequence.getLength();
    }

    /**
     * Creates a pair profile for the given sequences.
     *
     * @param query the first sequence of the pair
     * @param target the second sequence of the pair
     * @param sx lists whether the query sequence aligns a {@link Compound} or gap at each index of the alignment
     * @param xb number of {@link Compound}s skipped in the query sequence before the aligned region
     * @param xa number of {@link Compound}s skipped in the query sequence after the aligned region
     * @param sy lists whether the target sequence aligns a {@link Compound} or gap at each index of the alignment
     * @param yb number of {@link Compound}s skipped in the target sequence before the aligned region
     * @param ya number of {@link Compound}s skipped in the target sequence after the aligned region
     * @throws IllegalArgumentException if alignments differ in size or given sequences do not fit in alignments
     */
    protected SimpleProfile(S query, S target, List<Step> sx, int xb, int xa, List<Step> sy, int yb, int ya) {
        if (sx.size() != sy.size()) {
            throw new IllegalArgumentException("Alignments differ in size");
        }
        list = new ArrayList<AlignedSequence<S, C>>();
        list.add(new SimpleAlignedSequence<S, C>(query, sx, xb, xa));
        list.add(new SimpleAlignedSequence<S, C>(target, sy, yb, ya));
        list = Collections.unmodifiableList(list);
        originals = new ArrayList<S>();
        originals.add(query);
        originals.add(target);
        originals = Collections.unmodifiableList(originals);
        length = sx.size();
    }

    /**
     * Creates a pair profile for the given profiles.
     *
     * @param query the first profile of the pair
     * @param target the second profile of the pair
     * @param sx lists whether the query profile aligns a {@link Compound} or gap at each index of the alignment
     * @param sy lists whether the target profile aligns a {@link Compound} or gap at each index of the alignment
     * @throws IllegalArgumentException if alignments differ in size or given profiles do not fit in alignments
     */
    protected SimpleProfile(Profile<S, C> query, Profile<S, C> target, List<Step> sx, List<Step> sy) {
        if (sx.size() != sy.size()) {
            throw new IllegalArgumentException("Alignments differ in size");
        }
        list = new ArrayList<AlignedSequence<S, C>>();
        for (AlignedSequence<S, C> s : query) {
            list.add(new SimpleAlignedSequence<S, C>(s, sx));
        }
        for (AlignedSequence<S, C> s : target) {
            list.add(new SimpleAlignedSequence<S, C>(s, sy));
        }
        list = Collections.unmodifiableList(list);
        originals = new ArrayList<S>();
        originals.addAll(query.getOriginalSequences());
        originals.addAll(target.getOriginalSequences());
        originals = Collections.unmodifiableList(originals);
        length = sx.size();
    }

    @Override
    public AlignedSequence<S, C> getAlignedSequence(int listIndex) {
        return list.get(listIndex - 1);
    }

    @Override
    public AlignedSequence<S, C> getAlignedSequence(S sequence) {
        for (AlignedSequence<S, C> s : list) {
            if (s.equals(sequence) || s.getOriginalSequence().equals(sequence)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<AlignedSequence<S, C>> getAlignedSequences() {
        return list;
    }

    @Override
    public List<AlignedSequence<S, C>> getAlignedSequences(int... listIndices) {
        List<AlignedSequence<S, C>> tempList = new ArrayList<AlignedSequence<S, C>>();
        for (int i : listIndices) {
            tempList.add(getAlignedSequence(i));
        }
        return Collections.unmodifiableList(tempList);
    }

    @Override
    public List<AlignedSequence<S, C>> getAlignedSequences(S... sequences) {
        List<AlignedSequence<S, C>> tempList = new ArrayList<AlignedSequence<S, C>>();
        for (S s : sequences) {
            tempList.add(getAlignedSequence(s));
        }
        return Collections.unmodifiableList(tempList);
    }

    @Override
    public C getCompoundAt(int listIndex, int alignmentIndex) {
        return getAlignedSequence(listIndex).getCompoundAt(alignmentIndex);
    }

    @Override
    public C getCompoundAt(S sequence, int alignmentIndex) {
        AlignedSequence<S, C> s = getAlignedSequence(sequence);
        return (s == null) ? null : s.getCompoundAt(alignmentIndex);
    }

    @Override
    public int[] getCompoundCountsAt(int alignmentIndex) {
        return getCompoundCountsAt(alignmentIndex, getCompoundSet().getAllCompounds());
    }

    @Override
    public int[] getCompoundCountsAt(int alignmentIndex, List<C> compounds) {
        int[] counts = new int[compounds.size()];
        C gap = getCompoundSet().getCompoundForString("-");
        int igap = compounds.indexOf(gap);
        for (C compound : getCompoundsAt(alignmentIndex)) {
            int i = compounds.indexOf(compound);
            if (i >= 0 && i != igap && !getCompoundSet().compoundsEquivalent(compound, gap)) {
                counts[i]++;
            }
        }
        return counts;
    }

    @Override
    public List<C> getCompoundsAt(int alignmentIndex) {
        // TODO handle circular alignments
        List<C> column = new ArrayList<C>();
        for (AlignedSequence<S, C> s : list) {
            column.add(s.getCompoundAt(alignmentIndex));
        }
        return Collections.unmodifiableList(column);
    }

    @Override
    public CompoundSet<C> getCompoundSet() {
        return list.get(0).getCompoundSet();
    }

    @Override
    public float[] getCompoundWeightsAt(int alignmentIndex) {
        return getCompoundWeightsAt(alignmentIndex, getCompoundSet().getAllCompounds());
    }

    @Override
    public float[] getCompoundWeightsAt(int alignmentIndex, List<C> compounds) {
        float[] weights = new float[compounds.size()];
        int[] counts = getCompoundCountsAt(alignmentIndex, compounds);
        float total = 0.0f;
        for (int i : counts) {
            total += i;
        }
        if (total > 0.0f) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] = counts[i]/total;
            }
        }
        return weights;
    }

    @Override
    public int getIndexOf(C compound) {
        for (int i = 1; i <= length; i++) {
            if (getCompoundsAt(i).contains(compound)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int[] getIndicesAt(int alignmentIndex) {
        int[] indices = new int[list.size()];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = list.get(i).getSequenceIndexAt(alignmentIndex);
        }
        return indices;
    }

    @Override
    public int getLastIndexOf(C compound) {
        for (int i = length; i >= 1; i--) {
            if (getCompoundsAt(i).contains(compound)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public List<S> getOriginalSequences() {
        return originals;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (AlignedSequence<S, C> s : list) {
            size += s.getOverlapCount();
        }
        return size;
    }

    @Override
    public ProfileView<S, C> getSubProfile(Location location) {
        // TODO ProfileView<S, C> getSubProfile(Location)
        return null;
    }

    @Override
    public boolean isCircular() {
        for (AlignedSequence<S, C> s : list) {
            if (s.isCircular()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(int width) {
        // TODO String toString(int)
        return null;
    }

    @Override
    public String toString() {
        // TODO handle circular alignments
        StringBuilder s = new StringBuilder();
        for (AlignedSequence<S, C> as : list) {
            //s.append(String.format("%s%n", as.toString()));
        	s.append(as.toString());
        }
        return s.toString();
    }

    @Override
    public Iterator<AlignedSequence<S, C>> iterator() {
        return list.iterator();
    }

}
