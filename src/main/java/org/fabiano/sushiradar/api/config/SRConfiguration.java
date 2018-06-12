package org.fabiano.sushiradar.api.config;

import java.util.Map;

import org.fabiano.sushiradar.api.utils.IniHelper;

public final class SRConfiguration {
	
    private static SRConfiguration srConfiguration;
    private Map<String,String> configuration;

    private SRConfiguration(){
    }

    public static SRConfiguration getConfiguration(){
        if (srConfiguration == null){
            srConfiguration = new SRConfiguration();
            switch (System.getenv("database.engine")) {
			case "OO":
				srConfiguration.configuration = IniHelper.load("sushiradarOO.ini");
				System.out.println("------>MONGO CONFIG<------");
				break;
			case "SQL":
				srConfiguration.configuration = IniHelper.load("sushiradar.ini");
				System.out.println("------>SQL CONFIG<------");
				break;
			default:
				srConfiguration.configuration = IniHelper.load("sushiradar.ini");
				System.out.println("------>DEFAULT CONFIG<-------");
				break;
			}
        }
        return  srConfiguration;

    }

    public String get(String key){
        if (configuration.containsKey(key)){
            return configuration.get(key);
        }else return null;
    }
}
