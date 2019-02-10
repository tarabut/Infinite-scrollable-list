package com.test.codingchallenge.models.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* Data model for search response
*/
public class SearchListResponse {

    @SerializedName("total")
    private Integer total;
    @SerializedName("res")
    private List<SearchResource> searchResource = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SearchResource> getSearchResource() {
        return searchResource;
    }

    public void setSearchResource(List<SearchResource> searchResource) {
        this.searchResource = searchResource;
    }
}
