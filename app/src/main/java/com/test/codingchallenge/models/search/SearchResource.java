package com.test.codingchallenge.models.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Data model for search response
 */
public class SearchResource {

    @SerializedName("id")
    private Integer id;
    @SerializedName("update")
    private Integer update;
    @SerializedName("category_id")
    private Integer categoryId;
    @SerializedName("title")
    private String title;
    @SerializedName("subject")
    private String subject;
    @SerializedName("type")
    private String type;
    @SerializedName("type_id")
    private Integer typeId;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("thumbnail_big")
    private String thumbnailBig;
    @SerializedName("image_count")
    private Integer imageCount;
    @SerializedName("price")
    private String price;
    @SerializedName("price_period")
    private String pricePeriod;
    @SerializedName("price_period_raw")
    private String pricePeriodRaw;
    @SerializedName("price_label")
    private String priceLabel;
    @SerializedName("price_value")
    private String priceValue;
    @SerializedName("price_value_raw")
    private Integer priceValueRaw;
    @SerializedName("currency")
    private String currency;
    @SerializedName("featured")
    private Boolean featured;
    @SerializedName("location")
    private String location;
    @SerializedName("area")
    private String area;
    @SerializedName("poa")
    private Boolean poa;
    @SerializedName("rera_permit")
    private String reraPermit;
    @SerializedName("bathrooms")
    private String bathrooms;
    @SerializedName("bedrooms")
    private String bedrooms;
    @SerializedName("date_insert")
    private String dateInsert;
    @SerializedName("date_update")
    private String dateUpdate;
    @SerializedName("agent_name")
    private String agentName;
    @SerializedName("broker_name")
    private String brokerName;
    @SerializedName("agent_license")
    private String agentLicense;
    @SerializedName("location_id")
    private Integer locationId;
    @SerializedName("hide_location")
    private Boolean hideLocation;
    @SerializedName("broker")
    private Broker broker;
    @SerializedName("amenities")
    private List<String> amenities = null;
    @SerializedName("amenities_keys")
    private List<String> amenitiesKeys = null;
    @SerializedName("lat")
    private Double lat;
    @SerializedName("long")
    private Double _long;
    @SerializedName("premium")
    private Boolean premium;
    @SerializedName("livingrooms")
    private String livingrooms;
    @SerializedName("verified")
    private Boolean verified;
    @SerializedName("gallery")
    private List<String> gallery = null;
    @SerializedName("phone")
    private String phone;
    @SerializedName("lead_email_receivers")
    private List<String> leadEmailReceivers = null;
    @SerializedName("reference")
    private String reference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUpdate() {
        return update;
    }

    public void setUpdate(Integer update) {
        this.update = update;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailBig() {
        return thumbnailBig;
    }

    public void setThumbnailBig(String thumbnailBig) {
        this.thumbnailBig = thumbnailBig;
    }

    public Integer getImageCount() {
        return imageCount;
    }

    public void setImageCount(Integer imageCount) {
        this.imageCount = imageCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public String getPricePeriodRaw() {
        return pricePeriodRaw;
    }

    public void setPricePeriodRaw(String pricePeriodRaw) {
        this.pricePeriodRaw = pricePeriodRaw;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public Integer getPriceValueRaw() {
        return priceValueRaw;
    }

    public void setPriceValueRaw(Integer priceValueRaw) {
        this.priceValueRaw = priceValueRaw;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getPoa() {
        return poa;
    }

    public void setPoa(Boolean poa) {
        this.poa = poa;
    }

    public String getReraPermit() {
        return reraPermit;
    }

    public void setReraPermit(String reraPermit) {
        this.reraPermit = reraPermit;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(String dateInsert) {
        this.dateInsert = dateInsert;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getAgentLicense() {
        return agentLicense;
    }

    public void setAgentLicense(String agentLicense) {
        this.agentLicense = agentLicense;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Boolean getHideLocation() {
        return hideLocation;
    }

    public void setHideLocation(Boolean hideLocation) {
        this.hideLocation = hideLocation;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<String> getAmenitiesKeys() {
        return amenitiesKeys;
    }

    public void setAmenitiesKeys(List<String> amenitiesKeys) {
        this.amenitiesKeys = amenitiesKeys;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public String getLivingrooms() {
        return livingrooms;
    }

    public void setLivingrooms(String livingrooms) {
        this.livingrooms = livingrooms;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getLeadEmailReceivers() {
        return leadEmailReceivers;
    }

    public void setLeadEmailReceivers(List<String> leadEmailReceivers) {
        this.leadEmailReceivers = leadEmailReceivers;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
