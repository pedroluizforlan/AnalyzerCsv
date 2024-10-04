package com.pedroluizforlan.dms.document;

import java.util.Map;

public class Document {
    private final Map<String,String> attributes;

    public Document(final Map<String, String> attributes){
        this.attributes = attributes;
    }

    public String getAttributes(final String attributeName) {
        return attributes.get(attributeName);
    }
}
