package edu.hm.swa.sh.abc3.mediaservice.rest.transformer;

import edu.hm.swa.sh.abc3.mediaservice.common.dto.Disc;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.DiscType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiscTransformerTest {
    private static final String TITLE = "Das ist eine CD";
    private static final String TITLE_2 = "DAs ist iene DVD";
    private static final String DIRECTOR = "Senior Spielbergo";
    private static final String DIRECTOR_2 = "Steven Spielberg";
    private static final int FSK = 16;
    private static final int FSK_2 = 0;
    private static final String BARCODE = "9783888293436";
    private static final String BARCODE_2 = "9783888888885";

    @Test
    public void testToDisc() {
        final DiscType discType = new DiscType();
        discType.setTitle(TITLE);
        discType.setDirector(DIRECTOR);
        discType.setBarcode(BARCODE);
        discType.setFsk(FSK);

        final DiscTransformer underTest = new DiscTransformer();

        final Disc result = underTest.toDisc(discType);

        assertEquals("Should be " + TITLE, TITLE, result.getTitle());
        assertEquals("Should be " + DIRECTOR, DIRECTOR, result.getDirector());
        assertEquals("Should be " + BARCODE, BARCODE, result.getBarcode());
        assertTrue("Should be " + FSK, FSK == result.getFsk());
    }

    @Test
    public void testToDiscType() {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);

        final DiscTransformer underTest = new DiscTransformer();

        final DiscType result = underTest.toDiscType(disc);

        assertEquals("Should be " + TITLE, TITLE, result.getTitle());
        assertEquals("Should be " + DIRECTOR, DIRECTOR, result.getDirector());
        assertEquals("Should be " + BARCODE, BARCODE, result.getBarcode());
        assertTrue("Should be " + FSK, FSK == result.getFsk());
    }

    @Test
    public void testToBookTypeArray() {
        final Disc disc1 = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        final Disc disc2 = new Disc(TITLE_2, BARCODE_2, DIRECTOR_2, FSK_2);

        final Disc[] discArray = new Disc[2];
        discArray[0] = disc1;
        discArray[1] = disc2;

        final DiscTransformer underTest = new DiscTransformer();

        final DiscType[] result = underTest.toDiscTypeArray(discArray);

        assertTrue("Array should have length of 2", result.length == 2);

        final DiscType discType1 = result[0];
        assertEquals("Should be " + TITLE, TITLE, discType1.getTitle());
        assertEquals("Should be " + DIRECTOR, DIRECTOR, discType1.getDirector());
        assertEquals("Should be " + BARCODE, BARCODE, discType1.getBarcode());
        assertTrue("Should be " + FSK, FSK == discType1.getFsk());

        final DiscType discType2 = result[1];
        assertEquals("Should be " + TITLE_2, TITLE_2, discType2.getTitle());
        assertEquals("Should be " + DIRECTOR_2, DIRECTOR_2, discType2.getDirector());
        assertEquals("Should be " + BARCODE_2, BARCODE_2, discType2.getBarcode());
        assertTrue("Should be " + FSK_2, FSK_2 == discType2.getFsk());
    }
}
