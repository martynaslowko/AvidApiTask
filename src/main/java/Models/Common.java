package Models;

import java.time.LocalDateTime;

public class Common {
    private LocalDateTime created;
    private LocalDateTime modified;
    private String name;

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public String getName() {
        return name;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public void setName(String name) {
        this.name = name;
    }
}
