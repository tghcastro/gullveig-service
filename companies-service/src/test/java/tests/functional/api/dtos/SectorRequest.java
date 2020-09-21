package tests.functional.api.dtos;

public class SectorRequest {
    private String name;
    private boolean enabled;

    public SectorRequest(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public static SectorRequest CreateWithValidData() {
        String someName = "SEC " + System.currentTimeMillis();
        return new SectorRequest(someName, true);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

