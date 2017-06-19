package edu.hm.swa.sh.abc3.mediaservice.persistence.simplepersistence.transformer;

import edu.hm.swa.sh.abc3.dto.Disc;
import edu.hm.swa.sh.abc3.mediaservice.persistence.entity.DiscEntity;

import java.util.List;

/**
 * Transform entity to dto.
 */
public class DiscTransformer {
    /**
     * Transform Disc dto to its entity.
     *
     * @param dto Disc.
     * @return discEntity.
     */
    public DiscEntity toEntity(final Disc dto) {
        if (dto == null) {
            return null;
        }
        final DiscEntity entity = new DiscEntity();
        entity.setDirector(dto.getDirector());
        entity.setTitle(dto.getTitle());
        entity.setBarcode(simplifyIdentifier(dto.getBarcode()));
        entity.setFsk(dto.getFsk());
        return entity;
    }

    /**
     * Transform entity of a disc to a dto.
     *
     * @param entity DiscEntity.
     * @return Disc dto.
     */
    public Disc toDisc(final DiscEntity entity) {
        if (entity == null) {
            return null;
        }
        final String director = entity.getDirector();
        final String title = entity.getTitle();
        final String barcode = entity.getBarcode();
        final int fsk = entity.getFsk();

        return new Disc(title, barcode, director, fsk);
    }

    /**
     * Transform list of entities to disc array.
     *
     * @param entities List of DiscEntity.
     * @return Disc array.
     */
    public Disc[] toDiscArray(final List<DiscEntity> entities) {
        final Disc[] discs = new Disc[entities.size()];

        for (int index = 0; index < entities.size(); index++) {
            discs[index] = toDisc(entities.get(index));
        }

        return discs;
    }

    /**
     * Remove unnessessary chars from identifier.
     *
     * @param identifier original identifier.
     * @return cleaned identifier.
     */
    private String simplifyIdentifier(final String identifier) {
        return identifier.replace("-", "").replace(" ", "");
    }
}
