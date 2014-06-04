/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jscc.app.client.biojava3.core.sequence.io;

import java.io.OutputStream;
import java.util.Collection;

import org.jscc.app.client.biojava3.core.sequence.io.template.FastaHeaderFormatInterface;
import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.Sequence;

/**
 *
 * @author Scooter Willis <willishf at gmail dot com>
 */
public class FastaWriter<S extends Sequence<?>, C extends Compound> {

    OutputStream os;
    Collection<S> sequences;
    FastaHeaderFormatInterface<S,C> headerFormat;
    private int lineLength = 60;

    public FastaWriter(OutputStream os, Collection<S> sequences, FastaHeaderFormatInterface<S,C> headerFormat) {
        this.os = os;
        this.sequences = sequences;
        this.headerFormat = headerFormat;
    }

    public FastaWriter(OutputStream os, Collection<S> sequences, FastaHeaderFormatInterface<S,C> headerFormat, int lineLength) {
      this.os = os;
      this.sequences = sequences;
      this.headerFormat = headerFormat;
      this.lineLength = lineLength;
  }

    public void process() throws Exception {
//      int lineLength = getLineLength();
//      byte[] lineSep = System.getProperty("line.separator").getBytes();
//
//      for (S sequence : sequences) {
//          String header = headerFormat.getHeader(sequence);
//          os.write('>');
//          os.write(header.getBytes());
//          os.write(lineSep);
//
//          int compoundCount = 1;
//
//          for(Compound c: sequence) {
//            os.write(c.getShortName().getBytes());
//            if(compoundCount == lineLength) {
//              os.write(lineSep);
//              compoundCount = 1;
//            }
//            compoundCount++;
//          }
//
//          //If we had sequence which was a reciprocal of line length
//          //then don't write the line terminator as this has already written
//          //it
//          if( (sequence.getLength() % getLineLength()) != 0) {
//            os.write(lineSep);
//          }
//        }
    }

    public static void main(String[] args) {
//        try {
//            FileInputStream is = new FileInputStream("/Users/Scooter/mutualinformation/project/nuclear_receptor/PF00104_small.fasta");
//
//
//            FastaReader<ProteinSequence,AminoAcidCompound> fastaReader = new FastaReader<ProteinSequence,AminoAcidCompound>(is, new GenericFastaHeaderParser<ProteinSequence,AminoAcidCompound>(), new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet()));
//            LinkedHashMap<String,ProteinSequence> proteinSequences = fastaReader.process();
//            is.close();
//
//
//            System.out.println(proteinSequences);
//
//            FileOutputStream fileOutputStream = new FileOutputStream("/Users/Scooter/mutualinformation/project/nuclear_receptor/PF00104_small_test.fasta");
//
//            FastaWriter<ProteinSequence, AminoAcidCompound> fastaWriter = new FastaWriter<ProteinSequence, AminoAcidCompound>(fileOutputStream,proteinSequences.values(),new GenericFastaHeaderFormat<ProteinSequence, AminoAcidCompound>());
//            fastaWriter.process();
//            fileOutputStream.close();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @return the lineLength
     */
    public int getLineLength() {
        return lineLength;
    }

    /**
     * @param lineLength the lineLength to set
     */
    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }
}
