///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.jscc.app.client.biojava3.core.sequence.io;
//
//import java.io.File;
//import java.util.List;
//
//import org.jscc.app.client.biojava3.core.sequence.ProteinSequence;
//import org.jscc.app.client.biojava3.core.sequence.compound.AminoAcidCompound;
//import org.jscc.app.client.biojava3.core.sequence.io.template.SequenceCreatorInterface;
//import org.jscc.app.client.biojava3.core.sequence.loader.SequenceFileProxyLoader;
//import org.jscc.app.client.biojava3.core.sequence.template.AbstractSequence;
//import org.jscc.app.client.biojava3.core.sequence.template.CompoundSet;
//import org.jscc.app.client.biojava3.core.sequence.template.ProxySequenceReader;
//
///**
// *
// * @author Scooter Willis <willishf at gmail dot com>
// */
//public class FileProxyProteinSequenceCreator implements
//        SequenceCreatorInterface<AminoAcidCompound> {
//
//    CompoundSet<AminoAcidCompound> compoundSet = null;
//    File fastaFile = null;
//
//    public FileProxyProteinSequenceCreator(File fastaFile,
//            CompoundSet<AminoAcidCompound> compoundSet) {
//        this.compoundSet = compoundSet;
//        this.fastaFile = fastaFile;
//    }
//
//    public AbstractSequence<AminoAcidCompound> getSequence(String sequence,
//            long index) {
//        SequenceFileProxyLoader<AminoAcidCompound> sequenceFileProxyLoader = new SequenceFileProxyLoader<AminoAcidCompound>(
//                fastaFile, new FastaSequenceParser(), index, sequence.length(),
//                compoundSet);
//        return new ProteinSequence(sequenceFileProxyLoader, compoundSet);
//    }
//    
//    public AbstractSequence<AminoAcidCompound> getSequence(
//            ProxySequenceReader<AminoAcidCompound> proxyLoader, long index) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public AbstractSequence<AminoAcidCompound> getSequence(
//            List<AminoAcidCompound> list) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//}
