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
 * Created on DATE
 *
 */
package org.jscc.app.client.biojava3.core.sequence;

/**
 *
 * @author Scooter Willis
 */
import java.util.LinkedHashMap;

import org.jscc.app.client.biojava3.core.sequence.compound.DNACompoundSet;
import org.jscc.app.client.biojava3.core.sequence.compound.NucleotideCompound;
import org.jscc.app.client.biojava3.core.sequence.loader.StringProxySequenceReader;
import org.jscc.app.client.biojava3.core.sequence.template.AbstractSequence;
import org.jscc.app.client.biojava3.core.sequence.template.CompoundSet;
import org.jscc.app.client.biojava3.core.sequence.template.ProxySequenceReader;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceMixin;
import org.jscc.app.client.biojava3.core.sequence.template.SequenceView;
import org.jscc.app.client.biojava3.core.sequence.transcription.Frame;
import org.jscc.app.client.biojava3.core.sequence.transcription.TranscriptionEngine;
import org.jscc.app.client.biojava3.core.sequence.views.ComplementSequenceView;
import org.jscc.app.client.biojava3.core.sequence.views.ReversedSequenceView;

public class DNASequence extends AbstractSequence<NucleotideCompound> {

    
    
    private LinkedHashMap<String, GeneSequence> geneSequenceHashMap = new LinkedHashMap<String, GeneSequence>();

    public enum DNAType {

        CHROMOSOME, MITOCHONDRIAL, PLASMID, PLASTID, UNKNOWN
    }
    private DNAType dnaType = DNAType.UNKNOWN;

    public DNASequence() {
//        throw new UnsupportedOperationException("Null constructor not supported");
    }

    public DNASequence(String seqString) {
        super(seqString, DNACompoundSet.getDNACompoundSet());
    }

    public DNASequence(ProxySequenceReader<NucleotideCompound> proxyLoader) {
        super(proxyLoader, DNACompoundSet.getDNACompoundSet());
    }

    public DNASequence(String seqString, CompoundSet<NucleotideCompound> compoundSet) {
        super(seqString, compoundSet);
    }

    public DNASequence(ProxySequenceReader<NucleotideCompound> proxyLoader, CompoundSet<NucleotideCompound> compoundSet) {
        super(proxyLoader, compoundSet);
    }


    public LinkedHashMap<String,GeneSequence> getGeneSequences(){
        return geneSequenceHashMap;
    }

    public GeneSequence removeGeneSequence(String accession) {
        return geneSequenceHashMap.remove(accession);
    }

    public GeneSequence addGene(AccessionID accession, int begin, int end) {
        GeneSequence geneSequence = new GeneSequence(this, begin, end);
        geneSequence.setAccession(accession);
        geneSequenceHashMap.put(accession.toString(), geneSequence);
        return geneSequence;
    }

    public GeneSequence getGene(String accession) {
        return geneSequenceHashMap.get(accession);
    }

    public SequenceView<NucleotideCompound> getReverseComplement() {
        return new ComplementSequenceView<NucleotideCompound>(getReverse());
    }

    public SequenceView<NucleotideCompound> getReverse() {
        return new ReversedSequenceView<NucleotideCompound>(this);
    }

    public SequenceView<NucleotideCompound> getComplement() {
        return new ComplementSequenceView<NucleotideCompound>(this);
    }

    public RNASequence getRNASequence() {
      return getRNASequence(Frame.getDefaultFrame());
    }

    public RNASequence getRNASequence(TranscriptionEngine engine) {
      return getRNASequence(engine, Frame.getDefaultFrame());
    }

    public RNASequence getRNASequence(Frame frame) {
      return getRNASequence(TranscriptionEngine.getDefault(), Frame.getDefaultFrame());
    }

    public RNASequence getRNASequence(TranscriptionEngine engine, Frame frame) {
      return (RNASequence) engine.getDnaRnaTranslator().createSequence(this, frame);
    }

    public int getGCCount() {
        return SequenceMixin.countGC(this);
    }

    /**
     * @return the dnaType
     */
    public DNAType getDNAType() {
        return dnaType;
    }

    /**
     * @param dnaType the dnaType to set
     */
    public void setDNAType(DNAType dnaType) {
        this.dnaType = dnaType;
    }

    public static void main(String[] args) {
        DNASequence dnaSequence = new DNASequence("ATCG");
        System.out.println(dnaSequence.toString());

        StringProxySequenceReader<NucleotideCompound> sequenceStringProxyLoader =
                new StringProxySequenceReader<NucleotideCompound>("GCTA", DNACompoundSet.getDNACompoundSet());
        DNASequence dnaSequenceFromProxy = new DNASequence(sequenceStringProxyLoader);
        System.out.println(dnaSequenceFromProxy.toString());

    }
}
