package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.dao.IGroupDAO;
import java.util.List;

public class GroupDAO extends MyBatisDAO implements IGroupDAO {

    @Override
    public List<Group> getAll() {
        return executeWithSession(session -> {
            IGroupDAO groupMapper = session.getMapper(IGroupDAO.class);
            return groupMapper.getAll();
        });
    }

    @Override
    public Group getById(long id) {
        return executeWithSession(session -> {
            IGroupDAO groupMapper = session.getMapper(IGroupDAO.class);
            return groupMapper.getById(id);
        });
    }

    @Override
    public Group getByGroupMembershipId(long groupMembershipId) {
        return executeWithSession(session -> {
            IGroupDAO groupMapper = session.getMapper(IGroupDAO.class);
            return groupMapper.getByGroupMembershipId(groupMembershipId);
        });
    }

    @Override
    public void insert(Group group) {
        executeWithSession(session -> {
            IGroupDAO groupMapper = session.getMapper(IGroupDAO.class);
            groupMapper.insert(group);
            return null;
        });
    }

    @Override
    public void update(Group group) {
        executeWithSession(session -> {
            IGroupDAO groupMapper = session.getMapper(IGroupDAO.class);
            groupMapper.update(group);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IGroupDAO groupMapper = session.getMapper(IGroupDAO.class);
            groupMapper.delete(id);
            return null;
        });
    }
}
