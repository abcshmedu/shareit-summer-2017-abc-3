package edu.hm.swa.sh.abc3.rest.transformer;

import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.rest.types.DiscType;

/**
 * Transform Disc.
 */
public class DiscTransformer {
    /**
     * Transform a discType to a Disc dto.
     *
     * @param discType Disctype.
     * @return DiscDTO.
     */
    public Disc toDisc(final DiscType discType) {
        return new Disc(discType.getTitle(), discType.getBarcode(), discType.getDirector(), discType.getFsk());
    }

    /**
     * Transform a disc to DiscType.
     *
     * @param disc disc to transform.
     * @return DiscType of a disc.
     */
    public DiscType toDiscType(final Disc disc) {
        final DiscType result = new DiscType();
        if (disc != null) {
            result.setBarcode(disc.getBarcode());
            result.setDirector(disc.getDirector());
            result.setTitle(disc.getTitle());
            result.setFsk(disc.getFsk());
        }
        return result;
    }

    /**
     * Transform a disc array to a DiscType array.
     *
     * @param discs disc array.
     * @return DiscType of disc array.
     */
    public DiscType[] toDiscTypeArray(final Disc[] discs) {
        final DiscType[] result = new DiscType[discs.length];

        for (int index = 0; index < discs.length; index++) {
            result[index] = toDiscType(discs[index]);
        }

        return result;
    }
}
