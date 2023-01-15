package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config/${deviceHost}.properties",
        "classpath:config/credentials.properties"})
public interface MobileConfig extends Config {
    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    @DefaultValue("android")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

}

