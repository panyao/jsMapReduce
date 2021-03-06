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
 * Created on June 7, 2010
 * Author: Mark Chapman
 */

package org.jscc.app.client.biojava3.alignment.template;

import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.Sequence;

/**
 * Defines an algorithm which computes a new alignment {@link Profile} by rescoring all pairs in an alignment and
 * realigning.
 *
 * @author Mark Chapman
 * @param <S> each element of the alignment {@link Profile} is of type S
 * @param <C> each element of an {@link AlignedSequence} is a {@link Compound} of type C
 */
public interface RescoreRefiner<S extends Sequence<C>, C extends Compound> extends Aligner<S, C>,
        ProfileProfileScorer<S, C> {

    /**
     * Returns class type of pairwise scoring subroutine.
     *
     * @return class type of pairwise scoring subroutine
     */
    Class<? extends PairInProfileScorer<S, C>> getPairInProfileScorer();

    /**
     * Returns class type of profile-profile alignment subroutine.
     *
     * @return class type of profile-profile alignment subroutine
     */
    Class<? extends ProfileProfileAligner<S, C>> getProfileProfileAligner();

}
