package tests.functional.api.contracts;

public class PutCompanyRequest {
    private String name;
    private boolean enabled;
    private CompanySector sector;

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

    public CompanySector getSector() {
        return sector;
    }

    public void setSector(String sectorId) {
        this.sector = new CompanySector();
        this.sector.setId(sectorId);
    }

    public static class CompanySector {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

