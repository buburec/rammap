package ru.infotecs.driver;

import ru.infotecs.driver.client.ClientService;
import ru.infotecs.driver.dto.RamMapDTO;
import ru.infotecs.driver.dto.RamObjectDTO;
import ru.infotecs.driver.dto.RamPairDTO;
import ru.infotecs.driver.url.UrlManager;

import java.io.File;

/**
 * The {@code Runner} class is the entry point of the application.
 * <p>
 * This class demonstrates how to use the {@link ClientService} to perform various operations
 * on the backend service. It sets values, retrieves values, deletes values, dumps data,
 * and loads data from the backend.
 * </p>
 * <p>
 * The main method of this class executes a series of operations to showcase how the client
 * interacts with the backend service. It performs the following actions:
 * </p>
 * <ol>
 *     <li>Sets three key-value pairs in the backend.</li>
 *     <li>Retrieves and prints a value associated with a specific key.</li>
 *     <li>Deletes a key-value pair from the backend.</li>
 *     <li>Attempts to retrieve and print the deleted key's value (which should no longer exist).</li>
 *     <li>Dumps the current state of the backend data to a file.</li>
 *     <li>Loads the dumped data back into the backend and prints the resulting state.</li>
 * </ol>
 *
 * @see ClientService
 * @see UrlManager
 * @see RamPairDTO
 * @see RamObjectDTO
 * @see RamMapDTO
 */
public class Runner {
    /**
     * The main method that serves as the entry point of the application.
     * <p>
     * This method demonstrates how to interact with the backend service using
     * the {@link ClientService} class. It performs a sequence of operations to
     * set values, retrieve values, delete values, dump data, and load data.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
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
