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
 * Created on 01-21-2010
 *
 * @author Richard Holland
 *
 *
 */
package org.jscc.app.client.biojava3.core.sequence.storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.jscc.app.client.biojava3.core.exceptions.CompoundNotFoundError;
import org.jscc.app.client.biojava3.core.sequence.AccessionID;
import org.jscc.app.client.biojava3.core.sequence.Strand;
import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.CompoundSet;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceMixin;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceProxyView;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceReader;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceView;

public class ArrayListSequenceReader<C extends Compound> implements SequenceReader<C> {

    private CompoundSet<C> compoundSet;
    private List<C> parsedCompounds = new ArrayList<C>();

    public ArrayListSequenceReader() {
        //Do nothing
    }

    public ArrayListSequenceReader(List<C> compounds, CompoundSet<C> compoundSet) {
        setCompoundSet(compoundSet);
        setContents(compounds);
    }

    public ArrayListSequenceReader(String sequence, CompoundSet<C> compoundSet) {
        setCompoundSet(compoundSet);
        setContents(sequence);
    }

    @Override
    public String getSequenceAsString() {
        return getSequenceAsString(1, getLength(), Strand.POSITIVE);
    }

    @Override
    public String getSequenceAsString(Integer begin, Integer end, Strand strand) {
        // TODO Optimise/cache.
        SequenceAsStringHelper<C> sequenceAsStringHelper = new SequenceAsStringHelper<C>();
        return sequenceAsStringHelper.getSequenceAsString(this.parsedCompounds, compoundSet, begin, end, strand);
    }

    @Override
    public List<C> getAsList() {
        return this.parsedCompounds;
    }

    @Override
    public C getCompoundAt(int position) {
        return this.parsedCompounds.get(position - 1);
    }

    @Override
    public int getIndexOf(C compound) {
        return this.parsedCompounds.indexOf(compound) + 1;
    }

    @Override
    public int getLastIndexOf(C compound) {
        return this.parsedCompounds.lastIndexOf(compound) + 1;
    }

    @Override
    public int getLength() {
        return this.parsedCompounds.size();
    }

    @Override
    public Iterator<C> iterator() {
        return this.parsedCompounds.iterator();
    }

    @Override
    public void setCompoundSet(CompoundSet<C> compoundSet) {
        this.compoundSet = compoundSet;
    }

    @Override
    public CompoundSet<C> getCompoundSet() {
        return compoundSet;
    }

    @Override
    public void setContents(String sequence) {
        // Horrendously inefficient - pretty much the way the old BJ did things.
        // TODO Should be optimised.
        this.parsedCompounds.clear();
        for (int i = 0; i < sequence.length();) {
            String compoundStr = null;
            C compound = null;
            for (int compoundStrLength = 1; compound == null && compoundStrLength <= compoundSet.getMaxSingleCompoundStringLength(); compoundStrLength++) {
                compoundStr = sequence.substring(i, i + compoundStrLength);
                compound = compoundSet.getCompoundForString(compoundStr);
            }
            if (compound == null) {
                throw new CompoundNotFoundError(compoundStr);
            } else {
                i += compoundStr.length();
            }
            this.parsedCompounds.add(compound);
        }
    }

    public void setContents(List<C> list) {
        parsedCompounds.clear();
        for (C c : list) {
            parsedCompounds.add(c);
        }
    }

    @Override
    public SequenceView<C> getSubSequence(final Integer bioBegin, final Integer bioEnd) {
        return new SequenceProxyView<C>(ArrayListSequenceReader.this, bioBegin, bioEnd);
    }

    @Override
    public AccessionID getAccession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countCompounds(C... compounds) {
       return SequenceMixin.countCompounds(this, compounds);
    }
}
