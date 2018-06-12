package org.fabiano.sushiradar.api.config;

import org.fabiano.sushiradar.api.utils.IniHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.util.Map;

public final class SRConfiguration {
	
	@Value("${database.engine}")
	private static String engine;
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
				System.out.println("=======>MONGO CONFIG<=======");
				break;
			case "SQL":
				srConfiguration.configuration = IniHelper.load("sushiradar.ini");
				System.out.println("=======>SQL CONFIG<=======");
				break;
			default:
				srConfiguration.configuration = IniHelper.load("sushiradar.ini");
				System.out.println("=======>DEFAULT CONFIG<=======");
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
