package com.epam.winter.java.lab.collections.map;

/**
 * this class only test collision and buckets
 */
class TestUser {
    private String name;
    private int testHash;

    public TestUser(String name, int testHash) {
        this.name = name;
        this.testHash = testHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTestHash() {
        return testHash;
    }

    public void setTestHash(int testHash) {
        this.testHash = testHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestUser testUser = (TestUser) o;
        return name.equals(testUser.name);
    }

    @Override
    public int hashCode() {
        return testHash;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "name='" + name + '\'' +
                ", testHash=" + testHash +
                '}';
    }
}
