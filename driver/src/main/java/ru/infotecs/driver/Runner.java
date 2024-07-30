package ru.infotecs.driver;


import ru.infotecs.driver.client.ClientService;
import ru.infotecs.driver.dto.RamMapDTO;
import ru.infotecs.driver.dto.RamObjectDTO;
import ru.infotecs.driver.dto.RamPairDTO;
import ru.infotecs.driver.url.UrlManager;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        clientService.setValue(UrlManager.SET_URL, new RamPairDTO("1", new RamObjectDTO("123", 80)));
        clientService.setValue(UrlManager.SET_URL, new RamPairDTO("2", new RamObjectDTO("12424", 780)));
        clientService.setValue(UrlManager.SET_URL, new RamPairDTO("3", new RamObjectDTO("name", 8000)));
        RamObjectDTO ramObjectDTO = clientService.getValue(UrlManager.GET_URL, "1");
        System.out.println(ramObjectDTO);
        clientService.deleteValue(UrlManager.DELETE_URL, "1");
        RamObjectDTO deletedRamObjectDTO = clientService.getValue(UrlManager.GET_URL, "1");
        System.out.println(deletedRamObjectDTO);
        File file = clientService.dumpData(UrlManager.DUMP_URL);
        RamMapDTO ramMapDTO = clientService.loadData(UrlManager.LOAD_URL, file);
        System.out.println(ramMapDTO);
    }
}