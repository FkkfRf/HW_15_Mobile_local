package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE) // объединяем в один config  параметры из разных properties
@Config.Sources({"classpath:config/${deviceEmulate}.properties",
         "classpath:config/emulation.properties"
})

public interface MobileConfig extends Config {

    @Key("deviceEmulate")
    @DefaultValue("emulation")
    String deviceEmulate(); // доп. параметр, чтобы в BaseTest выбрать нужный драйвер

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    @DefaultValue("android")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("App")
    String App();
    @Key("loginBS")
    String loginBS();

    @Key("passwordBS")
    String passwordBS();

}
