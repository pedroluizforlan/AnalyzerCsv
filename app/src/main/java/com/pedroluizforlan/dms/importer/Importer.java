package com.pedroluizforlan.dms.importer;

import com.pedroluizforlan.dms.document.Document;

import java.io.File;
import java.io.IOException;

public interface Importer {
    Document importFile(File file) throws IOException;
}
