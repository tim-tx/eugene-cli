package edu.bu.tsj;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.FileUtils;
import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;

public class Main {
	public static void main (String[] args) {
		// args
		String filename = "";
		if (args.length < 1) {
			System.err.println("Missing required filename argument.");
			System.exit(1);
		}
		else {
			filename = args[0];
		}
		// file
		File file = new File(filename);
		String str = "";
		try {
			str = FileUtils.readFileToString(file,(String)null);
		} catch (IOException e) {
			System.err.println("Error with file.");
			System.exit(1);
		}
		// eugene
		try {
			Eugene eugene = new Eugene();
			File cruft = new File(System.getProperty("user.dir").toString() + FilenameUtils.separatorsToSystem("/exports"));
			(new File(cruft.getPath() + FilenameUtils.separatorsToSystem("/pigeon"))).delete();
			cruft.delete();
			EugeneCollection ec = eugene.executeScript(str);
			System.out.println(ec);
			// (EugeneArray) ec.get("allResults")
		} catch ( EugeneException e ) {
			e.printStackTrace();
		}
    }
}
