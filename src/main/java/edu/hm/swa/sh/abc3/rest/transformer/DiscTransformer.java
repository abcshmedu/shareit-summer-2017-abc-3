package edu.hm.swa.sh.abc3.rest.transformer;

import edu.hm.swa.sh.abc3.common.dto.Disc;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.Stateless;

/**
 * Transform Disc to JSONObject.
 */
@Stateless
public class DiscTransformer {
    /**
     * Transform a disc to a JSONObject.
     *
     * @param disc disc to transform.
     * @return JSONObject of a disc.
     */
    public JSONObject toJSONObject(final Disc disc) {
        final JSONObject result = new JSONObject();
        if (disc != null) {
            result.put("title", disc.getTitle());
            result.put("director", disc.getDirector());
            result.put("barcode", disc.getBarcode());
        }
        return result;
    }

    /**
     * Transform a disc array to a JSONObject.
     *
     * @param disc disc array.
     * @return JSONObject of disc array.
     */
    public JSONObject toJSONObject(final Disc[] disc) {
        final JSONObject result = new JSONObject();
        final JSONArray resultArray = new JSONArray();
        if (disc != null && disc.length > 1) {

            for (final Disc singleDisc : disc) {
                resultArray.put(toJSONObject(singleDisc));
            }
        }
        result.put("disc", resultArray);
        return result;
    }
}
