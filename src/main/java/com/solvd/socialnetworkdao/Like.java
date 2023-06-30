package com.solvd.socialnetworkdao;

public class Like {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                '}';
    }
}
