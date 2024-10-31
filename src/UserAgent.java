class UserAgent {
    private final String operatingSystem;
    private final String browser;

    public UserAgent(String userAgentStr) {
        this.operatingSystem = parseOperatingSystem(userAgentStr);
        this.browser = parseBrowser(userAgentStr);
    }

    private String parseOperatingSystem(String userAgentStr) {
        if (userAgentStr.contains("Windows")) {
            return "Windows";
        } else if (userAgentStr.contains("Mac OS")) {
            return "macOS";
        } else if (userAgentStr.contains("Linux")) {
            return "Linux";
        } else {
            return "Other";
        }
    }

    private String parseBrowser(String userAgentStr) {
        if (userAgentStr.contains("Edge")) {
            return "Edge";
        } else if (userAgentStr.contains("Firefox")) {
            return "Firefox";
        } else if (userAgentStr.contains("Chrome")) {
            return "Chrome";
        } else if (userAgentStr.contains("Opera")) {
            return "Opera";
        } else {
            return "Other";
        }
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }
}