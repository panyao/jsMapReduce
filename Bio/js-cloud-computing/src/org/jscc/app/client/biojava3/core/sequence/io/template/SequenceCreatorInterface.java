/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jscc.app.client.biojava3.core.sequence.io.template;

import java.util.List;

import org.jscc.app.client.biojava3.core.sequence.template.AbstractSequence;
import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.ProxySequenceReader;

/**
 *
 * @author Scooter Willis <willishf at gmail dot com>
 */
public interface SequenceCreatorInterface<C extends Compound> {

    public AbstractSequence<C> getSequence(String sequence, long index);
    public AbstractSequence<C> getSequence(ProxySequenceReader<C> proxyLoader, long index);
    public AbstractSequence<C> getSequence(List<C> list);

}
