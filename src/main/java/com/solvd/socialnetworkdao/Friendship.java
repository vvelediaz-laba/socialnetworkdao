package com.solvd.socialnetworkdao;

public class Friendship {
    private Long id;
    private String status;
    private Profile requestedProfile;
    private Profile requesterProfile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Profile getRequestedProfile() {
        return requestedProfile;
    }

    public void setRequestedProfile(Profile requestedProfile) {
        this.requestedProfile = requestedProfile;
    }

    public Profile getRequesterProfile() {
        return requesterProfile;
    }

    public void setRequesterProfile(Profile requesterProfile) {
        this.requesterProfile = requesterProfile;
    }
}
