package com.davydov.vedmaster.models;

public class Parameters {
    private static int weeksNum = 52;
    private static double expectedSalesGrowthRatio = 1.0;
    private static double serviceRatio = 1.0;

    public static int getWeeksNum() {
        return weeksNum;
    }

    public static void setWeeks(int weeksNum) {
        Parameters.weeksNum = weeksNum;
    }

    public static double getExpectedSalesGrowthRatio() {
        return expectedSalesGrowthRatio;
    }

    public static void setExpectedSalesGrowthRatio(double expectedSalesGrowthRatio) {
        Parameters.expectedSalesGrowthRatio = expectedSalesGrowthRatio;
    }

    public static double getServiceRatio() {
        return serviceRatio;
    }

    public static void setServiceRatio(double serviceRatio) {
        Parameters.serviceRatio = serviceRatio;
    }
}
