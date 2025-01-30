package com.ecommerce.e_store.exception;

@SuppressWarnings("unused")
public class ResourceNotFoundException extends RuntimeException {
    
    private String resourceName;
    private String field;
    private String fieldName;
    private Long fieldId;

    public ResourceNotFoundException() {

    }
    
    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s non trovato con %s: %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s non trovato con %s: %s", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
    
}
