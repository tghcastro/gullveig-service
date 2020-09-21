package tests.functional.api.dtos;

public class PostSectorRequest {
    private String name;
    private boolean enabled;

    public PostSectorRequest(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public static PostSectorRequest CreateWithValidData() {
        String someName = "SEC " + System.currentTimeMillis();
        return new PostSectorRequest(someName, true);

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

