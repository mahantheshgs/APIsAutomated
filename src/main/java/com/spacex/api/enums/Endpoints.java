package com.spacex.api.enums;

public enum Endpoints {



    LATEST_LAUNCH_DETAILS("https://api.spacexdata.com/v4/launches/latest");


       String resourcePath;

    Endpoints(String resourcePath){
        this.resourcePath=resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

//    public void setResourcePath(String data) {
//        this.resourcePath = resourcePath+data;
//    }

}