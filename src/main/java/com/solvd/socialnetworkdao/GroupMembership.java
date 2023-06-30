package com.solvd.socialnetworkdao;

public class GroupMembership {
    private Long id;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "GroupMembership{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
