package com.pedroluizforlan.dms;

import com.pedroluizforlan.dms.document.Document;
import com.pedroluizforlan.dms.exception.UnknownFileTypeException;
import com.pedroluizforlan.dms.importer.ImageImporter;
import com.pedroluizforlan.dms.importer.Importer;
import com.pedroluizforlan.dms.importer.LetterImporter;
import com.pedroluizforlan.dms.importer.ReportImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {
    private final List<Document> documents = new ArrayList<>();
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }

    public void importFile(final String path) throws IOException {
        final File file = new File(path);

        if(!file.exists()){
            throw new FileNotFoundException(path);
        }

        final int separetorIndex = path.lastIndexOf(path);

        if(separetorIndex != -1){
            if(separetorIndex == path.length()){
                throw new UnknownFileTypeException("No extension found for file: " + path);
            }

            final String extension = path.substring(separetorIndex + 1);
            final Importer importer = extensionToImporter.get(extension);
            if(importer == null){
                throw new UnknownFileTypeException("For file: " + path);
            }

            final Document document = importer.importFile(file);
            documents.add(document);
        } else {
            throw new UnknownFileTypeException("No extension found for file: " + path);
        }

    }
}
