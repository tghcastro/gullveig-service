package tests.functional.api.contracts;

import java.util.List;

public class PostCompanyResponse {
    private String id;
    private String name;
    private boolean enabled;
    private CompanySector sector;
    private List<CompanyStock> stocks;

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

    public void setSector(CompanySector sector) {
        this.sector = sector;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CompanyStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<CompanyStock> stocks) {
        this.stocks = stocks;
    }

    public static class CompanySector {
        private String id;
        private String name;
        private boolean enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

    public static class CompanyStock {
        private String id;
        private String ticker;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }
    }
}

