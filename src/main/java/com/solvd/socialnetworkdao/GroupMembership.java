package com.solvd.socialnetworkdao;

public class GroupMembership {
    private Long id;
    private Profile memberProfileId;
    private String role;
    private Group group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getMemberProfileId() {
        return memberProfileId;
    }

    public void setMemberProfileId(Profile memberProfileId) {
        this.memberProfileId = memberProfileId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
