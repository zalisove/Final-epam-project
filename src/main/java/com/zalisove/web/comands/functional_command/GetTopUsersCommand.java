package com.zalisove.web.comands.functional_command;

import com.zalisove.Path;
import com.zalisove.db.entity.UserTest;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.UserManager;
import com.zalisove.db.managers.UserTestManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class GetTopUsersCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<UserTest> userTestList = UserTestManager.getInstance()
                    .getAllUserTestByDateFromTo(
                            Date.valueOf(request.getParameter("from")),
                            Date.valueOf(request.getParameter("to")));

            Map<Long, Entity> meanArithmetic = new HashMap<>();

            for (UserTest userTest : userTestList) {
                if (meanArithmetic.containsKey(userTest.getUserId())) {
                    Entity entity = meanArithmetic.get(userTest.getUserId());
                    entity.incrementTestCount();
                    entity.getAddMack(userTest.getMark());
                } else {
                    meanArithmetic.put(userTest.getUserId(), new Entity(1, userTest.getMark()));
                }
            }

            List<Map.Entry<Long, Entity>> topUsers = new ArrayList(meanArithmetic.entrySet());

            topUsers.sort(Comparator.comparingDouble(x -> -x.getValue().meanArithmetic()));

            UserManager userManager = UserManager.getInstance();


            request.setAttribute("top_user", topUsers.stream().limit(3)
                    .collect(Collectors.toMap(x -> {
                        try {
                            return userManager.read(x.getKey()).orElseThrow(() -> new ManagerException("No read user"));
                        } catch (ManagerException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }, x -> x.getValue().meanArithmetic()))
            );
        } catch (ManagerException e) {
            return Path.PAGE__TOP_USER;
        }
        return Path.PAGE__TOP_USER;
    }

    class Entity {
        public int testCount;
        public double markSum;

        public Entity(int testCount, double markSum) {
            this.testCount = testCount;
            this.markSum = markSum;
        }

        public int getTestCount() {
            return testCount;
        }

        public void getAddMack(double mark) {
            markSum += mark;
        }

        public void incrementTestCount() {
            testCount++;
        }

        public double meanArithmetic() {
            return markSum / testCount;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "testCount=" + testCount +
                    ", markSum=" + markSum +
                    ", meanArithmetic=" + this.meanArithmetic() +
                    '}';
        }
    }

}
