package org.fabiano.sushiradar.api.config;

import org.fabiano.sushiradar.api.utils.IniHelper;

import java.util.Map;

public final class SRConfiguration {

    private static SRConfiguration srConfiguration;
    private Map<String,String> configuration;

    private SRConfiguration(){

    }

    public static SRConfiguration getConfiguration(){
        if (srConfiguration == null){
            srConfiguration = new SRConfiguration();
            srConfiguration.configuration = IniHelper.load("sushiradar.ini");
        }
        return  srConfiguration;

    }

    public String get(String key){
        if (configuration.containsKey(key)){
            return configuration.get(key);
        }else return null;
    }
}
