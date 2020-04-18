package com.slawomirlasik.diet_plan_management.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DietUser extends Person {

    private List<DietStatistics> userStatistics;

    public DietUser(String name, String lastName, LocalDate birthDate) throws Exception {
        super(name, lastName, birthDate);
        this.userStatistics = new ArrayList<>();
    }

    public void addStatistic(DietStatistics dietStatistics) {
        this.userStatistics.add(dietStatistics);
    }

    public void addStatistic(List<DietStatistics> dietStatisticsList) {
        for (DietStatistics dietStatistics : dietStatisticsList) {
            addStatistic(dietStatistics);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " DietUser{" +
                "userStatistics=" + userStatistics.toString() +
                '}';
    }
}
