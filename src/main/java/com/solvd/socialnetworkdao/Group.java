package com.solvd.socialnetworkdao;

import java.util.List;

public class Group {
    private Long id;
    private String groupName;
    private String description;
    private List<GroupMembership> groupMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GroupMembership> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMembership> groupMembers) {
        this.groupMembers = groupMembers;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", groupMembers=" + groupMembers +
                '}';
    }
}
