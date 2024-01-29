package startup.poc.saisiedetemps.securtiy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
    MANAGER("MANAGER");

    private final String role;

    public String toString() {
        return this.role;
    }
    public String getRole(){
        return this.role;
    }
}
