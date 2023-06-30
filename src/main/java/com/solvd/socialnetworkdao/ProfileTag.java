package com.solvd.socialnetworkdao;

public class ProfileTag {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfileTag{" +
                "id=" + id +
                '}';
    }
}
