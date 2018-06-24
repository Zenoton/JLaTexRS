package com.zenoton.jlatexrs.settings;

import org.springframework.stereotype.Component;

/**
 * Class to fetch settings from the respective file (either DB or local file)
 */
@Component("settingsHandler")
public class SettingsHandler {
    private static Settings settings;
    public SettingsHandler(){
        if(settings == null) {
            settings = new Settings();
            settings.setReportPath("/Users/campbell/IdeaProjects/jlatexrs/reports");
        }
    }

    public String getReportPath(){
        return settings.getReportPath();
    }

}
