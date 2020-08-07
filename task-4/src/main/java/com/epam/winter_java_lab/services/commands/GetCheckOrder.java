package com.epam.winter_java_lab.services.commands;

import com.epam.winter_java_lab.entiities.Cafe;
import com.epam.winter_java_lab.entiities.User;
import com.epam.winter_java_lab.entiities.order.interfaces.Order;
import com.epam.winter_java_lab.services.FileService;

import java.util.Map;

public class GetCheckOrder extends Command {

    public GetCheckOrder(Cafe cafe) {
        super(cafe);
    }

    @Override
    public void execute() {
        Cafe cafe = getCafe();
        User user = cafe.selectUser();
        System.out.println(user);

        OrderCommandHistory history = cafe.getHistory();
        Map<String, Command> commands = history.get(user);
        commands.forEach((k, v) -> v.execute());
        Order order = user.getOrder();
        StringBuilder builder = new StringBuilder();
        builder.append("Order : ")
                .append(order.getDescription())
                .append(" ")
                .append("Cost :")
                .append(order.getCost());
        System.out.println(builder.toString());
        FileService.saveFile(user);

        history.removeCommands(user);
        cafe.getUsers().remove(user);
    }
}
