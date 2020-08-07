package com.epam.winter_java_lab.entities.wraper.json;

import java.time.LocalDate;
import java.util.List;

public class Setting {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String showFor;
    private List<String> showForList;
    private String sortBy;
    private List<String> useDepartments;

    //need builder++
    private Setting(LocalDate dateFrom, LocalDate dateTo, String showFor,
                    List<String> showForList, String sortBy,
                    List<String> useDepartments) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.showFor = showFor;
        this.showForList = showForList;
        this.sortBy = sortBy;
        this.useDepartments = useDepartments;
    }

    private Setting() {
        this(null, null, null,
                null, null, null);
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }


    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getShowFor() {
        return showFor;
    }

    public List<String> getShowForList() {
        return showForList;
    }

    public String getSortBy() {
        return sortBy;
    }

    public List<String> getUseDepartments() {
        return useDepartments;
    }


    public static SettingBuilder newBuilder() {
        return new Setting().new SettingBuilder();
    }

    public class SettingBuilder {
        private SettingBuilder() {
        }

        public SettingBuilder setDateFrom(LocalDate dateFrom) {
            Setting.this.dateFrom = dateFrom;
            return this;
        }

        public SettingBuilder setDateTo(LocalDate dateTo) {
            Setting.this.dateTo = dateTo;
            return this;
        }

        public SettingBuilder setShowFor(String showFor) {
            Setting.this.showFor = showFor;
            return this;
        }

        public SettingBuilder setShowForList(List<String> showForList) {
            Setting.this.showForList = showForList;
            return this;
        }

        public SettingBuilder setSortBy(String sortBy) {
            Setting.this.sortBy = sortBy;
            return this;
        }

        public SettingBuilder setUseDepartments(List<String> useDepartments) {
            Setting.this.useDepartments = useDepartments;
            return this;
        }

        public Setting build() {
            return Setting.this;
        }
    }

    @Override
    public String toString() {
        return "Setting{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", showFor='" + showFor + '\'' +
                ", showForList=" + showForList +
                ", sortBy='" + sortBy + '\'' +
                ", useDepartments=" + useDepartments +
                '}';
    }
}
