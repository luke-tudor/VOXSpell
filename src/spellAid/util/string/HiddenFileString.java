package spellAid.util.string;

/**
 * This class turns a qualified file path into the same path with a '.' in front
 * of the unqualified file name. For example, foo/bar.txt -> foo/.bar.txt.
 * 
 * @author Luke Tudor
 */
public class HiddenFileString {
	
	private String hiddenFile;
	
	public HiddenFileString(String fileString) {
		String[] chunks = fileString.split("/");
		StringBuilder hiddenBuilder = new StringBuilder();
		for (int i = 0; i < chunks.length; i++) {
			hiddenBuilder.append(chunks[i]);
			if (i == chunks.length - 1) {
				break;
			}
			hiddenBuilder.append("/");
			if (i == chunks.length - 2) {
				hiddenBuilder.append(".");
			}
		}
		hiddenFile = hiddenBuilder.toString();
	}
	
	public String getHiddenFileString() {
		return hiddenFile;
	}
}
