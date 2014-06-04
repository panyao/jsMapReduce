/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jscc.app.client.biojava3.core.sequence.io.template;

import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.Sequence;

/**
 *
 * @author Scooter Willis <willishf at gmail dot com>
 */
public interface FastaHeaderFormatInterface<S extends Sequence<?>, C extends Compound> {
    public String getHeader(S sequence);
}
