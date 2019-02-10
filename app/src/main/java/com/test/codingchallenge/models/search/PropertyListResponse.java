package com.test.codingchallenge.models.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* Data model for search response
*/
public class PropertyListResponse {

    @SerializedName("total")
    private Integer total;
    @SerializedName("res")
    private List<PropertyResource> propertyResource = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PropertyResource> getPropertyResource() {
        return propertyResource;
    }

    public void setPropertyResource(List<PropertyResource> propertyResource) {
        this.propertyResource = propertyResource;
    }
}
