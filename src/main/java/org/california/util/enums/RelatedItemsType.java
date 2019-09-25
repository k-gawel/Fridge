package org.california.util.enums;

public enum RelatedItemsType {

    MOST_POPULAR("most_popular"),
    ALL("all");

    private final String typeString;

    RelatedItemsType(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }

    public static RelatedItemsType of(String value) {

        for(RelatedItemsType type: RelatedItemsType.values()) {
            if(type.getTypeString().equals(value))
                return type;
        }

        return ALL;
    }



}
