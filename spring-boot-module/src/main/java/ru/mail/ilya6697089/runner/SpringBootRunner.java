package ru.mail.ilya6697089.runner;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.mail.ilya6697089.ItemService;
import ru.mail.ilya6697089.model.ItemDTO;

@Component
public class SpringBootRunner implements ApplicationRunner {

    private ItemService itemService;

    public SpringBootRunner(ItemService itemService) {
        this.itemService = itemService;
    }

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Name1");
        itemDTO.setDescription("Name1_Description");

        itemService.add(itemDTO);

        List<ItemDTO> items = itemService.findAll();

        logger.info(items.toString());
    }

}
