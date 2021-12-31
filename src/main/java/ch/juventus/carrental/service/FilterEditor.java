package ch.juventus.carrental.service;

import ch.juventus.carrental.model.Filter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.PropertyEditorSupport;

public class FilterEditor extends PropertyEditorSupport {
    private final ObjectMapper objectMapper;
    public FilterEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text.isEmpty()) {
            setValue(null);
        } else {
            Filter filter;
            try {
                filter = objectMapper.readValue(text, Filter.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(filter);
        }
    }
}