package com.cflint.plugins.core;

import ro.fortsoft.pf4j.Extension;
import net.htmlparser.jericho.Element;
import cfml.parsing.cfscript.CFExpression;
import cfml.parsing.cfscript.script.CFScriptStatement;

import com.cflint.BugInfo;
import com.cflint.BugList;
import com.cflint.plugins.CFLintScannerAdapter;

@Extension
public class FileCaseChecker extends CFLintScannerAdapter {
	final String severity = "INFO";
		
	public void startFile(String fileName, BugList bugs) {
		String actualFileName = fileName;
		String separator = System.getProperty("file.separator");
    	int seperatorPosition = fileName.lastIndexOf(separator);

    	if (seperatorPosition >= 0) {
    		actualFileName = fileName.substring(seperatorPosition + 1);
    	}

		if (actualFileName.contains(".cfm")) {
			if (Character.isUpperCase(actualFileName.charAt(0))) {
				bugs.add(new BugInfo.BugInfoBuilder().setLine(1).setMessageCode("FILE_SHOULD_START_WITH_LOWERCASE")
						.setSeverity(severity).setFilename(fileName)
						.setMessage("File " + actualFileName + " starts with a upper case letter. Only components (.cfc files) should start with an upper case letter.")
						.build());
			}
		}
	}
}