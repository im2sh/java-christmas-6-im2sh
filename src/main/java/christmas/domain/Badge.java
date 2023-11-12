package christmas.domain;

public enum Badge {
    STAR("별",5000),
    TREE("트리",10000),
    SANTA("산타", 20000);
    private final String name;
    private final int requirement;

    Badge(String name, int requirement) {
        this.name = name;
        this.requirement = requirement;
    }

    public String getName() {
        return name;
    }

    public int getRequirement() {
        return requirement;
    }
}
