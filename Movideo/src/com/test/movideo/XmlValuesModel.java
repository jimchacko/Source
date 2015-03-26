package com.test.movideo;

import java.io.Serializable;


public class XmlValuesModel  implements Serializable{

	
	private static final long serialVersionUID = 7801880324749491877L;
	
	private int id;
	private String filename;
	private String imageFilename = "";
	private String mediaType = "";
	private String title = "";
	private String creator = "";
	private String description = "";
	private String copyright = "";
	private String status = "";
	private String ratio = "";
	private boolean syndicated = false;
	private boolean cuePointsExist = false;
	private String tags = "";
	private boolean mediaFileExists = false;
	private String duration = "";
	private boolean isAdvertisement = false;

	private String payWalls = "";
	private boolean externalAuthentication = false;
	private String creationDate = "";
	private String lastModifiedDate = "";

	private int clientid;
	private String clientalias = "";

	private String imagePath = "";
	private String defaultImageurl = "";
	private int defaultImageheight;
	private int defaultImagewidth;
	private int mediaPlaysday;
	private int mediaPlaysmonth;
	private int mediaPlaysweek;
	private int mediaPlaystotal;
	private String additionalImages = "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getImageFilename() {
		return imageFilename;
	}
	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public boolean isSyndicated() {
		return syndicated;
	}
	public void setSyndicated(boolean syndicated) {
		this.syndicated = syndicated;
	}
	public boolean isCuePointsExist() {
		return cuePointsExist;
	}
	public void setCuePointsExist(boolean cuePointsExist) {
		this.cuePointsExist = cuePointsExist;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public boolean isMediaFileExists() {
		return mediaFileExists;
	}
	public void setMediaFileExists(boolean mediaFileExists) {
		this.mediaFileExists = mediaFileExists;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public boolean isAdvertisement() {
		return isAdvertisement;
	}
	public void setAdvertisement(boolean isAdvertisement) {
		this.isAdvertisement = isAdvertisement;
	}
	public String getPayWalls() {
		return payWalls;
	}
	public void setPayWalls(String payWalls) {
		this.payWalls = payWalls;
	}
	public boolean isExternalAuthentication() {
		return externalAuthentication;
	}
	public void setExternalAuthentication(boolean externalAuthentication) {
		this.externalAuthentication = externalAuthentication;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public String getClientalias() {
		return clientalias;
	}
	public void setClientalias(String clientalias) {
		this.clientalias = clientalias;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getDefaultImageurl() {
		return defaultImageurl;
	}
	public void setDefaultImageurl(String defaultImageurl) {
		this.defaultImageurl = defaultImageurl;
	}
	public int getDefaultImageheight() {
		return defaultImageheight;
	}
	public void setDefaultImageheight(int defaultImageheight) {
		this.defaultImageheight = defaultImageheight;
	}
	public int getDefaultImagewidth() {
		return defaultImagewidth;
	}
	public void setDefaultImagewidth(int defaultImagewidth) {
		this.defaultImagewidth = defaultImagewidth;
	}
	public int getMediaPlaysday() {
		return mediaPlaysday;
	}
	public void setMediaPlaysday(int mediaPlaysday) {
		this.mediaPlaysday = mediaPlaysday;
	}
	public int getMediaPlaysmonth() {
		return mediaPlaysmonth;
	}
	public void setMediaPlaysmonth(int mediaPlaysmonth) {
		this.mediaPlaysmonth = mediaPlaysmonth;
	}
	public int getMediaPlaysweek() {
		return mediaPlaysweek;
	}
	public void setMediaPlaysweek(int mediaPlaysweek) {
		this.mediaPlaysweek = mediaPlaysweek;
	}
	public int getMediaPlaystotal() {
		return mediaPlaystotal;
	}
	public void setMediaPlaystotal(int mediaPlaystotal) {
		this.mediaPlaystotal = mediaPlaystotal;
	}
	public String getAdditionalImages() {
		return additionalImages;
	}
	public void setAdditionalImages(String additionalImages) {
		this.additionalImages = additionalImages;
	}
	

}