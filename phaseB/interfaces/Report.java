package plagiarismdetector;

import net.sf.json.JSONObject;

/**
 * The interface for storing all report related information
 * @author Team 106
 * @version 1.0
 */
public interface Report {

	/**
	 * Returns the JSON Object representing the report
	 * The JSON object is formatted to be parsed by the front end
	 * @return
	 */
    public JSONObject getReport();
}
