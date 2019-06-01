package com.toko.twitchflix;

public class GridItem {
    private String id;
    private String image;

    public GridItem() {
        super();
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){ return id; }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}