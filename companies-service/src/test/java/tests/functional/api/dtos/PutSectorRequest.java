package tests.functional.api.dtos;

public class PutSectorRequest {
    private String id;
    private String name;
    private boolean enabled;

    public PutSectorRequest(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public PutSectorRequest(String id, String name, boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }


    public static PutSectorRequest cloneFrom(PostSectorResponse postSectorResponse) {
        return new PutSectorRequest(postSectorResponse.getId(), postSectorResponse.getName(), postSectorResponse.isEnabled());
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

