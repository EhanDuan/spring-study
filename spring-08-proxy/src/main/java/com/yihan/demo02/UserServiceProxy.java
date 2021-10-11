package com.yihan.demo02;

public class UserServiceProxy implements UserService{
    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void query() {
        log("query");
        userService.query();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    // log
    public void log(String msg){
        System.out.println("使用了" + msg +"方法");
    }

}
