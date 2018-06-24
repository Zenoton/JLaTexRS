package com.zenoton.jlatexrs.settings;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Settings", catalog = "JLaTexRS",schema = "dbo")
@Component
public class Settings implements Serializable{
    private String reportPath;

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "reportPath='" + reportPath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        return Objects.equals(reportPath, settings.reportPath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reportPath);
    }
}
